package com.cirtual.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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
public class ChatController {
	
	@Autowired
	private ChatMessageService chatMessageService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/chat/getMessage/{recipientId}", method = RequestMethod.GET, produces="application/json")
	public List<ChatMessage> getMessages(@PathVariable Integer recipientId) {		
		System.out.println("getMessage for user in controller");
		List<ChatMessage> messageList = new ArrayList<>();
		System.out.println("recipientUserEmail is: " + recipientId);
		User user = userService.findById(recipientId);
		if(user == null) {
			return messageList;
		}
		
		messageList = chatMessageService.findMessageByAuthorUser(user);
		return messageList;		
	}
	
	@RequestMapping(value = "/chat/sendMessage", method = RequestMethod.POST)
	public String createNewUser(@RequestBody Integer authorUserId,  Integer recipientUserId, String message, BindingResult bindingResult) {
		User authorUser = userService.findById(authorUserId);
		if(authorUser == null){
			return "Invalid Author";
		}
		User recipientUser = userService.findById(recipientUserId);
		if(recipientUser == null){
			return "Invalid Recipient User";
		}
		if (bindingResult.hasErrors()) {
			return "There is some error while registration of the user.";
		} else {
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setAuthorUser(authorUser);
			chatMessage.setRecipientUser(recipientUser);
			chatMessage.setMessage(message);
			chatMessage.setTimestamp(new Date());
			chatMessageService.saveChatMessage(chatMessage);
			String res = "The new chat id is: " + chatMessage.getId();
			return res;
		}
	}
	
}
