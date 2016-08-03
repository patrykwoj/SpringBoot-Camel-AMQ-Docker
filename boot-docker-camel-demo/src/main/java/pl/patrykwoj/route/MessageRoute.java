package pl.patrykwoj.route;
import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import pl.patrykwoj.service.MessageService;

@Component
public class MessageRoute extends RouteBuilder {
	
	@BeanInject
	MessageService messageService;
	
	@Override
	public void configure() throws Exception {
	
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json).dataFormatProperty("prettyPrint", "true");
		
		rest("/messages/get").produces("application/json")
			.get("/last")
				.outType(pl.patrykwoj.dto.Message.class)
				.param().name("limit").type(RestParamType.query).defaultValue("1")
				.endParam().to("direct:outMessage")
			.get("/last/{limit}")
				.outType(pl.patrykwoj.dto.Message.class)
				.to("direct:outMessage");
		
		rest("/messages/add").produces("application/json").consumes("application/json")
			.post()
				.type(pl.patrykwoj.dto.Message[].class)
				.outType(pl.patrykwoj.dto.Message.class)
				.to("direct:inMessage");
	
		
		from("direct:outMessage")
			.choice()
				.when(header("limit").isEqualTo("0"))
					.transform(body().append("No output logs for limit 0.")).stop()
				.when(header(Exchange.HTTP_METHOD).isEqualTo(HttpMethod.POST))
					.bean(messageService,"getLastMessages").process("messageEntityToDtoProcessor")
				.otherwise()
					.bean(messageService,"getLastMessages(${header.limit})").process("messageEntityToDtoProcessor").end();
		
		from("direct:inMessage")
			.multicast()
				.to("direct:amq", "direct:service").end();
		
		from("direct:service")
			.process("messageDtoToEntityProcessor").bean(messageService,"addMessages").process("messageEntityToDtoProcessor").end();
	
		
		from("direct:amq").setExchangePattern(ExchangePattern.InOnly).to("activemq:queue:logs").end();
	}
}