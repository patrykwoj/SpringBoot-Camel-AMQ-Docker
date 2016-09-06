package pl.patrykwoj;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import pl.patrykwoj.dto.Message;
import pl.patrykwoj.util.MessageTypeEnum;

@Component
public class LoggerProducer {

	private static final Logger LOGGER = Logger.getLogger(LoggerProducer.class);

	@Autowired
	SimpMessagingTemplate template;

	private void push(Iterable<Message> messages, String topic) throws Exception {
		template.convertAndSend("/messages/"+topic, messages);
	}
	
	public void splitAndPush(Iterable<Message> messages) {
		Map<MessageTypeEnum, List<Message>> groupByMessageType = StreamSupport.stream(messages.spliterator(), true)
				.collect(Collectors.groupingBy(Message::getType));
		
		new CommandPushToBrowser(groupByMessageType.get(MessageTypeEnum.INFO),
				MessageTypeEnum.INFO.toString().toLowerCase()).observe();
		new CommandPushToBrowser(groupByMessageType.get(MessageTypeEnum.WARN),
				MessageTypeEnum.WARN.toString().toLowerCase()).observe();
		new CommandPushToBrowser(groupByMessageType.get(MessageTypeEnum.ERROR),
				MessageTypeEnum.ERROR.toString().toLowerCase()).observe();

	}

	class CommandPushToBrowser extends HystrixCommand<Void> /*HystrixObservableCommand<Void>*/ {

		private Iterable<Message> messages;
		private String messageTypeName;

		public CommandPushToBrowser(Iterable<Message> messages, String messageTypeName) {
			super(HystrixCommandGroupKey.Factory.asKey("Messages"));
			this.messageTypeName = messageTypeName;
			this.messages = messages;
		}

		@Override
		protected Void run() throws Exception {
			if (null != messages) {
				push(messages, messageTypeName);
				LOGGER.info("Message type: " + messageTypeName + " pushed: " + messages);
			}
			return null;
		}

		/*@Override
		protected Observable<Void> construct() {
			return Observable.create(new Observable.OnSubscribe<Void>() {

				@Override
				public void call(Subscriber<? super Void> observer) {
					try {
						for (int i = 0 ; i < 50 ; i ++ ) {
							LOGGER.info("Count: " + i + " messageType " + messageTypeName);
						}
						if (null != messages) {
							push(messages, messageTypeName);
							LOGGER.info("Message type: " + messageTypeName + " pushed: " + messages);
						}
						if (!observer.isUnsubscribed()) {
							observer.onCompleted();
						}
					} catch (Exception e) {
						e.printStackTrace();
						observer.onError(e);
					}
				}
			});
		}*/
	}
}
