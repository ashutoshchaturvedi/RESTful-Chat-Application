package com.cirtual.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cirtual.entity.ChatMessage;
import com.cirtual.entity.User;
import com.cirtual.service.ChatMessageService;
import com.cirtual.service.UserService;

@RestController
@RequestMapping("/chat")
public class ChatController {
	
	@Autowired
	private ChatMessageService chatMessageService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getMessage/{recipientId}", method = RequestMethod.GET, produces="application/json")
	public List<ChatMessage> getMessages(@PathVariable Integer recipientId) {
		List<ChatMessage> messageList = new ArrayList<>();
		User user = userService.findById(recipientId);
		if(user == null) {
			return messageList;
		}
		
		messageList = chatMessageService.findMessageByAuthorUser(user);
		return messageList;		
	}
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public String sendMessage(@RequestBody Map<String, Object> msg, BindingResult bindingResult) {
		if(msg == null){
			return "Message text is blank.";
		}
		User authorUser = userService.findById((Integer)msg.get("authorUserId"));
		if(authorUser == null){
			return "Invalid message author";
		}
		User recipientUser = userService.findById((Integer)msg.get("recipientUserId"));
		if(recipientUser == null){
			return "Invalid Recipient User";
		}
		if (bindingResult.hasErrors()) {
			return "There is some error sending the message.";
		} else {
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setAuthorUser(authorUser);
			chatMessage.setRecipientUser(recipientUser);
			chatMessage.setMessage((String)msg.get("message"));			
			chatMessage.setTimestamp(new Date());
			chatMessageService.saveChatMessage(chatMessage);
			String res = "The new chat id is: " + chatMessage.getId();
			return res;
		}
	}	
}
