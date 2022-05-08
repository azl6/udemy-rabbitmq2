package rabbitmq.two.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rabbitmq.two.entity.DummyMessage;

import javax.annotation.PostConstruct;

@Service
public class ReliableProducer {

    private static final Logger LOG = LoggerFactory.getLogger(ReliableProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void registerCallback(){
        rabbitTemplate.setConfirmCallback(((correlation, ack, cause) -> {
            if(correlation == null){
                return;
            }

            if(ack)
                LOG.info("Message with correlation {} published", correlation.getId());
            else
                LOG.info("Invalid exchange for message with correlation {}", correlation.getId());


        }));

        rabbitTemplate.setReturnsCallback(returned -> {
         LOG.info("Return callback");

         if(returned.getReplyText() != null && returned.getReplyText().equalsIgnoreCase("NO_ROUTE")){
             var id = returned.getMessage().getMessageProperties().getHeader("spring_returned_message_correlation")
                     .toString();

             LOG.warn("Invalid routing key for message {}", id);
         }
        });

    }

    public void sendDummyWithInvalidRoutingKey(DummyMessage message){
        var correlationData = new CorrelationData(Integer.toString(message.getPublishOrder()));
        rabbitTemplate.convertAndSend("x.dummy2", "invalid-routing-key", message, correlationData);
    }

    public void sendDummyToInvalidExchange(DummyMessage message){
        var correlationData = new CorrelationData(Integer.toString(message.getPublishOrder()));
        rabbitTemplate.convertAndSend("x.non-exists-exchange", "", message, correlationData);
    }
}
