package com.cirtual.service;

import java.util.List;

import com.cirtual.entity.ChatMessage;
import com.cirtual.entity.User;

public interface ChatMessageService {
	public List<ChatMessage> findMessageByAuthorUser(User user);
	public void saveChatMessage(ChatMessage chatMessage);
}
