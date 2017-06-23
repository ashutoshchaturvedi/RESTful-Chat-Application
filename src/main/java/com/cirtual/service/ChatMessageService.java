package com.cirtual.service;

import java.util.List;
import java.util.Map;

import com.cirtual.entity.ChatMessage;
import com.cirtual.entity.User;

/**
* Interface to encapsulate and provide common functions related to Chat
* @author ashutosh
*
*/
public interface ChatMessageService {
	
	/**
	 * The function to persist the Chat Message in data store using CrudRepository.
	 * 
	 * @param chatMessage object with details for storage.
	 */
	public void saveChatMessage(ChatMessage chatMessage);
	
	/**
	 * The method validateAndsaveChatMessage, validates the details like sender user, recipient user,
	 * message text before saving it in the data store.
	 * 
	 * @param requestMessage which is a Map containing @RequestBody data
	 * @param sender user who has sent the chat message.
	 * @return Response based on success/failure of the validation and save methods.
	 */
	public String validateAndsaveChatMessage(Map<String, Object> requestMessage, User sender);
	
	/**
	 * This method fetches all the chat messages present in data store which were sent
	 * to the request maker user.
	 * 
	 * @param user who makes the request and is the receiver of all the messages.
	 * @return list of all the chat messages sent to the user.
	 */
	public List<ChatMessage> findMessageByAuthorUser(User user);
}
