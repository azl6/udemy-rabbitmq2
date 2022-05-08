package rabbitmq.two;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rabbitmq.two.entity.DummyMessage;
import rabbitmq.two.entity.InvoiceCancelledMessage;
import rabbitmq.two.entity.InvoiceCreatedMessage;
import rabbitmq.two.entity.InvoicePaidMessage;
import rabbitmq.two.producer.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ProducertwoApplication implements CommandLineRunner {

	@Autowired
	private ReliableProducer producer;

	public static void main(String[] args) {
		SpringApplication.run(ProducertwoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var msg1 = new DummyMessage("Invalid test", 10);
		var msg2 = new DummyMessage("Invalid test", 20);
		producer.sendDummyWithInvalidRoutingKey(msg1);
		producer.sendDummyToInvalidExchange(msg2);
	}
}