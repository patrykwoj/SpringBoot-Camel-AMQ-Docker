package pl.pkwk.route;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class MessageRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
	
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json).dataFormatProperty("prettyPrint", "true");
		
		from("activemq:queue:logs").to("ahc-ws://localhost:80/logger").end();
	}
}