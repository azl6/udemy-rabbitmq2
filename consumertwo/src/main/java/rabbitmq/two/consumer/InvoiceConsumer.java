package rabbitmq.two.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import rabbitmq.two.entity.InvoiceCancelledMessage;
import rabbitmq.two.entity.InvoiceCreatedMessage;
import rabbitmq.two.entity.InvoicePaidMessage;
import rabbitmq.two.entity.PaymentCancelStatus;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

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

    @RabbitHandler(isDefault = true)
    public void handleDefault(Object message){
        LOG.warn("Handling default: {}", message);
    }

    @RabbitHandler
    @SendTo("x.invoice.cancel/")
    public PaymentCancelStatus handleInvoiceCancelled(InvoiceCancelledMessage message){
        var randomStatus = ThreadLocalRandom.current().nextBoolean();

        return new PaymentCancelStatus(randomStatus, LocalDate.now(), message.getInvoiceNumber());
    }
}
