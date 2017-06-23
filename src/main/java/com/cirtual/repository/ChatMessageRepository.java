package com.cirtual.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cirtual.entity.ChatMessage;
import com.cirtual.entity.User;

@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage, Integer> {
	List<ChatMessage> findByrecipientUser(User authorUser);
}
