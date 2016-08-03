package pl.pkwk.route.processor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageDtoToEntityProcessor implements Processor {

	@Autowired
	ModelMapper modelMapper;

	@Override
	public void process(Exchange exchange) throws Exception {
		
		@SuppressWarnings("unchecked")
		Iterable<pl.pkwk.dto.Message> body = (Iterable<pl.pkwk.dto.Message>) exchange.getIn().getBody();
		List<pl.pkwk.entity.Message> output = StreamSupport.stream(body.spliterator(), false)
				.map((pl.pkwk.dto.Message m) -> modelMapper.map(m, pl.pkwk.entity.Message.class))
				.collect(Collectors.toList());
		exchange.getIn().setBody(output);
	}

}
