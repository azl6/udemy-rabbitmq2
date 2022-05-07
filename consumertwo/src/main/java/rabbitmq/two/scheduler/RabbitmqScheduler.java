package rabbitmq.two.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rabbitmq.two.consumer.DummyConsumer;

@Service
@EnableScheduling
public class RabbitmqScheduler {

    @Autowired
    private RabbitListenerEndpointRegistry registry;

    private static final Logger LOG = LoggerFactory.getLogger(DummyConsumer.class);

    //stops all listeners at 11PM
    //@Scheduled(cron = "0 0 23 * * *")
    private void stopAll(){
        registry.getListenerContainers().forEach(listener -> {
            LOG.info("Stopping {}...", listener);
            listener.stop();
        });
    }

    //starts all listeners 1s after midnight
    //@Scheduled(cron = "1 0 0 * * *")
    private void startAll(){
        registry.getListenerContainers().forEach(listener -> {
            LOG.info("Starting {}...", listener);
            listener.stop();
        });
    }

}
