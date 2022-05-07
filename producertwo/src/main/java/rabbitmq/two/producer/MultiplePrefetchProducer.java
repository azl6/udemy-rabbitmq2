package rabbitmq.two.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rabbitmq.two.entity.DummyMessage;

import java.time.LocalTime;

@Service
public class MultiplePrefetchProducer {

    @Autowired //will use Jackson2JsonMessageConverter, created on config class
    private RabbitTemplate rabbitTemplate;

    public void simulateTransaction(){
        for(int i=0; i<20000; i++){
            var message = new DummyMessage("Transaction " + LocalTime.now(), i);
            rabbitTemplate.convertAndSend("x.transaction", "", message);
        }
    }

    public void simulateScheduler(){
        for(int i=0; i<20000; i++){
            var message = new DummyMessage("Scheduler " + LocalTime.now(), i);
            rabbitTemplate.convertAndSend("x.scheduler", "", message);
        }
    }
}
