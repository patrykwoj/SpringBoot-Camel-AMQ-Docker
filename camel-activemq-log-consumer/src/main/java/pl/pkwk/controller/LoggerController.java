package pl.pkwk.controller;

import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import pl.pkwk.dto.Message;

@Controller
public class LoggerController {

	private static final Logger LOGGER = Logger.getLogger(LoggerController.class); 
	
	@MessageMapping("/logger")
	@SendTo("/logger/all")
	public Message getLog(Message message) throws Exception {
		Thread.sleep(3000); // simulated delay
		LOGGER.info("New message consumed from JMS Queue, pending STOMP sending: " + message);
		return message;
	}
}
