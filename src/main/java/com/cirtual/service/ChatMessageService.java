package com.cirtual.service;

import java.util.List;

import com.cirtual.entity.ChatMessage;
import com.cirtual.entity.User;

public interface ChatMessageService {
	public void saveChatMessage(ChatMessage chatMessage);
	public List<ChatMessage> findMessageByAuthorUser(User user);
}
