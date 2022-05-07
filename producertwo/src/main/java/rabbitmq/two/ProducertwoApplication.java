package rabbitmq.two;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rabbitmq.two.entity.DummyMessage;
import rabbitmq.two.producer.DummyProducer;

import java.time.LocalTime;

@SpringBootApplication
public class ProducertwoApplication implements CommandLineRunner {

	@Autowired
	private DummyProducer dummyProducer;

	public static void main(String[] args) {
		SpringApplication.run(ProducertwoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var dummyMessage = new DummyMessage("Now is " + LocalTime.now(), 1);
		dummyProducer.sendDummy(dummyMessage);
	}
}