package rabbitmq.two.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import rabbitmq.two.entity.DummyMessage;

import java.util.concurrent.TimeUnit;

@Service
public class SingleActiveConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(DummyConsumer.class);

    @RabbitListener(queues = "q.single", concurrency = "5")
    public void listenDummy(DummyMessage msg) throws InterruptedException {
        LOG.info("Message: {}", msg);
        TimeUnit.SECONDS.sleep(1);
    }
}
