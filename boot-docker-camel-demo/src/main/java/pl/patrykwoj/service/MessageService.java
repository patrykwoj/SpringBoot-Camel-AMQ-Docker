package pl.patrykwoj.service;

import java.util.List;

public interface MessageService {
	public List<pl.patrykwoj.entity.Message> getLastMessages(String limit);
	public Iterable<pl.patrykwoj.entity.Message> addMessages(List<pl.patrykwoj.entity.Message> input);
}
