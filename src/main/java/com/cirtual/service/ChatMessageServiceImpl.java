package com.cirtual.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cirtual.entity.ChatMessage;
import com.cirtual.entity.User;
import com.cirtual.repository.ChatMessageRepository;
import com.cirtual.repository.UserRepository;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

	@Autowired
	ChatMessageRepository chatMessageRepository;
	
	@Autowired
	UserRepository userRepository;
	
	/* (non-Javadoc)
	 * @see com.cirtual.service.ChatMessageService#findMessageByAuthorUser(com.cirtual.entity.User)
	 */
	@Override
	public List<ChatMessage> findMessageByAuthorUser(User authorUser) {
		return chatMessageRepository.findByrecipientUser(authorUser);
	}

	/* (non-Javadoc)
	 * @see com.cirtual.service.ChatMessageService#saveChatMessage(com.cirtual.entity.ChatMessage)
	 */
	@Override
	public void saveChatMessage(ChatMessage chatMessage) {
		chatMessageRepository.save(chatMessage);
	}

	
	public String validateAndsaveChatMessage(Map<String, Object> requestMessage, User sender){
		User recipientUser = userRepository.findById((Integer) requestMessage.get("recipientUserId"));
		if (recipientUser == null) {
			return "Invalid Recipient User";
		}
		
		if (requestMessage.get("message") == null) {
			return "Message text is blank.";
		}
		
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setAuthorUser(sender);
		chatMessage.setRecipientUser(recipientUser);
		chatMessage.setMessage((String) requestMessage.get("message"));
		chatMessage.setTimestamp(new Date());
		saveChatMessage(chatMessage);
		return "Message Posted";
	}
}
