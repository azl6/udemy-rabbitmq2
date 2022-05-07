package rabbitmq.two.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rabbitmq.two.entity.DummyMessage;

@Service
public class DummyProducer {

    @Autowired //will use Jackson2JsonMessageConverter, created on config class
    private RabbitTemplate rabbitTemplate;

    public void sendDummy(DummyMessage message){
        rabbitTemplate.convertAndSend("x.dummy", "", message);

    }
}