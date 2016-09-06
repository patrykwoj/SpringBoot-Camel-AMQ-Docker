package pl.patrykwoj.configuration;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.TypeConversionException;
import org.apache.camel.impl.BreakpointSupport;
import org.apache.camel.impl.DefaultDebugger;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.support.TypeConverterSupport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.patrykwoj.dto.Message;

class MyCustomTypeConverter extends TypeConverterSupport {

	@Override
	@SuppressWarnings("unchecked")
	public <T> T convertTo(Class<T> type, Exchange exchange, Object value) throws TypeConversionException {
		// converter from value to the MyOrder bean
		String messages = new String(value.toString());
		return (T) messages;
	}
}

@Configuration
public class CamelConfiguration {

	private static final Logger LOGGER = Logger.getLogger(CamelConfiguration.class);
	// private static final String CAMEL_SERVLET_NAME = "CamelServlet";

	@Autowired
	CamelContext camelContext;

	/*
	 * @Bean public ServletRegistrationBean servletRegistrationBean() {
	 * ServletRegistrationBean registration = new ServletRegistrationBean(new
	 * CamelHttpTransportServlet()); registration.setName(CAMEL_SERVLET_NAME);
	 * return registration; }
	 */

	@Bean
	public DefaultDebugger defaultDebugger() {
		DefaultDebugger debugger = new DefaultDebugger();
		debugger.addSingleStepBreakpoint(new BreakpointSupport() {

			@Override
			public void beforeProcess(Exchange exchange, Processor processor, ProcessorDefinition<?> definition) {
				LOGGER.info("Before process: " + definition.toString());
			}

			@Override
			public void afterProcess(Exchange exchange, Processor processor, ProcessorDefinition<?> definition,
					long timeTaken) {
				LOGGER.info("After process: " + definition.toString());
			}
		});

		return debugger;
	}

	@PostConstruct
	void init() {
		//camelContext.setDebugger(defaultDebugger());
	}
}
