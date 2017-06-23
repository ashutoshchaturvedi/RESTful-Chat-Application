package com.cirtual.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cirtual.entity.ChatMessage;
import com.cirtual.entity.User;
import com.cirtual.service.ChatMessageService;
import com.cirtual.service.UserService;

/**
 * The controller class for all the requests coming on /chat/ path.
 * The class makes use of ChatMessageService and UserService by auto wiring them.
 * @author ashutosh
 *
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {
	
	@Autowired
	private ChatMessageService chatMessageService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getMessage", method = RequestMethod.GET, produces="application/json")
	public List<ChatMessage> getMessages() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(authentication.getName());
		List<ChatMessage> messageList = new ArrayList<>();
		if(user == null) {
			throw new Exception("Invalid user, please provide correct user credentials.");
		}		
		messageList = chatMessageService.findMessageByAuthorUser(user);
		return messageList;		
	}
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public String sendMessage(@RequestBody Map<String, Object> msg, BindingResult bindingResult) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(authentication.getName());
		if(user == null) {
			throw new Exception("Invalid user, please provide correct user credentials.");
		}
		if(msg == null){
			return "Message text is blank.";
		}

		User recipientUser = userService.findById((Integer)msg.get("recipientUserId"));
		if(recipientUser == null){
			return "Invalid Recipient User";
		}
		if (bindingResult.hasErrors()) {
			return "There is some error sending the message.";
		}
		else {
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setAuthorUser(user);
			chatMessage.setRecipientUser(recipientUser);
			chatMessage.setMessage((String)msg.get("message"));			
			chatMessage.setTimestamp(new Date());
			chatMessageService.saveChatMessage(chatMessage);
			String res = "Message Posted";
			return res;
		}

	}	
}
