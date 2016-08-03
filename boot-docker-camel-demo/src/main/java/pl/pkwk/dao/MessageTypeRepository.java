package pl.pkwk.dao;

import org.springframework.data.repository.CrudRepository;

import pl.pkwk.entity.MessageType;
import pl.pkwk.util.MessageTypeEnum;

public interface MessageTypeRepository extends CrudRepository<MessageType, Long> {
	MessageType findOneByName(MessageTypeEnum name);
}
