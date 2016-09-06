package pl.patrykwoj.route;
import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import pl.patrykwoj.LoggerProducer;

@Component
public class MessageRoute extends RouteBuilder {
	
	@BeanInject
	LoggerProducer loggerProducer;
	
	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json).dataFormatProperty("prettyPrint", "true");
		from("activemq:queue:logs").bean(loggerProducer, "splitAndPush").end(); //tu jest error
	}
}