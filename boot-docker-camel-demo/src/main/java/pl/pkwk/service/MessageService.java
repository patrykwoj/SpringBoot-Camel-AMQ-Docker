package pl.pkwk.service;

import java.util.List;

public interface MessageService {
	public List<pl.pkwk.entity.Message> getLastMessages(String limit);
	public Iterable<pl.pkwk.entity.Message> addMessages(List<pl.pkwk.entity.Message> input);
}
