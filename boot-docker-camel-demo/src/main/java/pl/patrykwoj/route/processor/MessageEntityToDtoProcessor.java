package pl.patrykwoj.route.processor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageEntityToDtoProcessor implements Processor {

	@Autowired
	ModelMapper modelMapper;

	@Override
	public void process(Exchange exchange) throws Exception {

		@SuppressWarnings("unchecked")
		Iterable<pl.patrykwoj.entity.Message> body = (Iterable<pl.patrykwoj.entity.Message>) exchange.getIn().getBody();
		List<pl.patrykwoj.dto.Message> output = StreamSupport.stream(body.spliterator(), false)
				.map((pl.patrykwoj.entity.Message m) -> modelMapper.map(m, pl.patrykwoj.dto.Message.class))
				.collect(Collectors.toList());
		Collections.reverse(output);
		exchange.getIn().setBody(output); // to use getOut, I would have to copy
											// headers etc.
	}

}
