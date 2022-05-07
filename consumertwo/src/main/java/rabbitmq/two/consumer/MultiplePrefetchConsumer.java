package rabbitmq.two.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import rabbitmq.two.entity.DummyMessage;

import java.util.concurrent.TimeUnit;

@Service
public class MultiplePrefetchConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(DummyConsumer.class);

    @RabbitListener(queues = "q.transaction", concurrency = "2")
    public void listenTransactional(DummyMessage msg) throws InterruptedException {
        LOG.info("Taking transaction: {}", msg);
        TimeUnit.MILLISECONDS.sleep(100);
    }

    @RabbitListener(queues = "q.scheduler", concurrency = "2", containerFactory = "prefetchOneContainerFactory")
    public void listenScheduler(DummyMessage msg) throws InterruptedException {
        LOG.info("Taking scheduler: {}", msg);
        TimeUnit.MILLISECONDS.MINUTES.sleep(1);
    }
}
