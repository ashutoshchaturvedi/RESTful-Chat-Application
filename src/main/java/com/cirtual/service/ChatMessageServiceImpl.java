package com.cirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cirtual.entity.ChatMessage;
import com.cirtual.entity.User;
import com.cirtual.repository.ChatMessageRepository;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

	@Autowired
	ChatMessageRepository chatMessageRepository;
	
	@Override
	public List<ChatMessage> findMessageByAuthorUser(User authorUser) {
		return chatMessageRepository.findByrecipientUser(authorUser);
	}

	@Override
	public void saveChatMessage(ChatMessage chatMessage) {
		chatMessageRepository.save(chatMessage);
	}

}
