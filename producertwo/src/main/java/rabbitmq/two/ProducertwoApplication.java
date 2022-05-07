package rabbitmq.two;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rabbitmq.two.entity.DummyMessage;
import rabbitmq.two.producer.DummyProducer;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ProducertwoApplication implements CommandLineRunner {

	@Autowired
	private DummyProducer dummyProducer;

	public static void main(String[] args) {
		SpringApplication.run(ProducertwoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for(int i=0; i<500; i++){
			var dummyMessage = new DummyMessage("Now is " + LocalTime.now(), i);
			dummyProducer.sendDummy(dummyMessage);
		}
		System.out.println("All messages were sent.");
	}
}