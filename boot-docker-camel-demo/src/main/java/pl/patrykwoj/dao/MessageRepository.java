package pl.patrykwoj.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import pl.patrykwoj.entity.Message;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

}
