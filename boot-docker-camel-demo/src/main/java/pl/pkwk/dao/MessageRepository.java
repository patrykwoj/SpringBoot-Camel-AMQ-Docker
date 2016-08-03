package pl.pkwk.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import pl.pkwk.entity.Message;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

}
