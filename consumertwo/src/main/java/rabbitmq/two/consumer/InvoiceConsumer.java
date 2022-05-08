package rabbitmq.two.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import rabbitmq.two.entity.InvoiceCreatedMessage;
import rabbitmq.two.entity.InvoicePaidMessage;

@Service
@RabbitListener(queues = "q.invoice")
public class InvoiceConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(DummyConsumer.class);

    @RabbitHandler
    public void handleInvoiceCreated(InvoiceCreatedMessage message){
        LOG.info("CREATED: {}", message);
    }

    @RabbitHandler
    public void handleInvoicePaid(InvoicePaidMessage message){
        LOG.info("PAID: {}", message);
    }
}
