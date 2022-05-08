package rabbitmq.two;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rabbitmq.two.entity.DummyMessage;
import rabbitmq.two.entity.InvoiceCreatedMessage;
import rabbitmq.two.entity.InvoicePaidMessage;
import rabbitmq.two.producer.DummyProducer;
import rabbitmq.two.producer.InvoiceProducer;
import rabbitmq.two.producer.MultiplePrefetchProducer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ProducertwoApplication implements CommandLineRunner {

	@Autowired
	private InvoiceProducer producer;

	public static void main(String[] args) {
		SpringApplication.run(ProducertwoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(100, 200);
		var invoiceCreatedMessage = new InvoiceCreatedMessage(
				127.0, LocalDate.now().minusDays(2), "USD", randomInvoiceNumber);
		producer.sendInvoiceCreated(invoiceCreatedMessage);

		randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(200, 300);
		var randomPaymentNumber = "PAY-" + ThreadLocalRandom.current().nextInt(800, 1000);

		var invoicePaidMessage = new InvoicePaidMessage(randomInvoiceNumber, LocalDate.now(), randomPaymentNumber);
		producer.sendInvoicePaid(invoicePaidMessage);
	}
}