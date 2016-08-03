package pl.patrykwoj.dao;

import org.springframework.data.repository.CrudRepository;

import pl.patrykwoj.entity.MessageType;
import pl.patrykwoj.util.MessageTypeEnum;

public interface MessageTypeRepository extends CrudRepository<MessageType, Long> {
	MessageType findOneByName(MessageTypeEnum name);
}
