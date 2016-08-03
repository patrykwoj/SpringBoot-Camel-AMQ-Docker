package pl.patrykwoj.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import pl.patrykwoj.dao.MessageRepository;
import pl.patrykwoj.dao.MessageTypeRepository;
import pl.patrykwoj.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	private static final String SORT_BY_PROPERTY = "id";

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	MessageTypeRepository messageTypeRepository;

	public List<pl.patrykwoj.entity.Message> getLastMessages(String limit) {
		Page<pl.patrykwoj.entity.Message> messages = messageRepository
				.findAll(new PageRequest(0, Integer.parseInt(limit), Direction.DESC, SORT_BY_PROPERTY));
		return messages.getContent();
	}

	@Transactional
	public Iterable<pl.patrykwoj.entity.Message> addMessages(List<pl.patrykwoj.entity.Message> input) {
		input.forEach(item -> item.setType(messageTypeRepository.findOneByName(item.getType().getName())));
		Iterable<pl.patrykwoj.entity.Message> result = messageRepository.save(input);
		return result;
	}

	// Lambda Comparator for sorting based on string length
	/*
	 * .sorted((pl.pkwk.dto.Message m1, pl.pkwk.dto.Message m2) -> { if
	 * (m1.getText().length() > m2.getText().length()) return 1; else if
	 * (m1.getText().length() < m2.getText().length()) return -1; else return 0;
	 * })
	 */
}
