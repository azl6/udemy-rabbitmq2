package rabbitmq.two.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqSchemaConfig {

	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange("x.another-dummy", true, false);
	}

	@Bean
	public Queue queueAnotherDummy() {
		return new Queue("q.another-dummy");
	}

	@Bean
	public Binding bindingAnotherDummy() {
	    //1st way
		//return new Binding("q.another-dummy", DestinationType.QUEUE, "x.another-dummy", "", null);

        //2nd way
		return BindingBuilder.bind(queueAnotherDummy()).to(fanoutExchange());
	}

    @Bean
    public Declarables rabbitmqSchema() {
        return new Declarables(new FanoutExchange("x.another-dummy", true, false), new Queue("q.another-dummy"),
                new Binding("q.another-dummy", DestinationType.QUEUE, "x.another-dummy", "", null));
    }
}