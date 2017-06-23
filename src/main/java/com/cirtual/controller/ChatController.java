package com.cirtual.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cirtual.entity.ChatMessage;
import com.cirtual.entity.User;
import com.cirtual.service.ChatMessageService;
import com.cirtual.service.UserService;

/**
 * The controller class for all the requests coming on /chat/ path. The class
 * makes use of ChatMessageService and UserService by auto wiring them. Only
 * valid User can make requests to these routes.
 * 
 * @author ashutosh
 *
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {
	
	private final Logger logger = LoggerFactory.getLogger(ChatController.class);

	@Autowired
	private ChatMessageService chatMessageService;

	@Autowired
	private UserService userService;

	/**
	 * The getMessages function takes GET request to fetch all the chat messages sent to the
	 * request making user. The user is determined from the SecurityContextHolder and only valid 
	 * User can make request to fetch their messages.
	 * 
	 * @return List of all the chat messages received for the request sender user.
	 * @throws Exception if the authenticated user is null
	 */
	@RequestMapping(value = "/getMessage", method = RequestMethod.GET, produces = "application/json")
	public List<ChatMessage> getMessages() throws Exception {
		logger.info("Executing UserServiceImpl.getMessages()");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = getAuthenticatedUserFromRequest(authentication);

		List<ChatMessage> messageList = new ArrayList<>();
		messageList = chatMessageService.findMessageByAuthorUser(user);
		return messageList;
	}

	/**
	 * The sendMessage takes POST request from the sender user to save chat messages sent for the
	 * recipient user. Only valid user can send messages to another valid user.
	 * 
	 * @param The Map<String, Object> requestMessage which contains the @RequestBody sent by the
	 * 			User in JSON format.
	 * @return Appropriate Error message if chat message is not saved, success message otherwise.
	 * @throws Exception if the authenticated user is null
	 */
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public String sendMessage(@RequestBody Map<String, Object> requestMessage) throws Exception {
		logger.info("Executing UserServiceImpl.sendMessage()");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = getAuthenticatedUserFromRequest(authentication);
		return chatMessageService.validateAndsaveChatMessage(requestMessage, user);
	}
	
	/**
	 * This method fetches the User from Authentication of the SecurityContextHolder.
	 * It makes use of UserService findByEmail to get the Principal User.
	 * 
	 * @param authentication for getting logged in user
	 * @return The authenticated user who has sent the request
	 * @throws Exception if the authenticated user is null
	 */
	private User getAuthenticatedUserFromRequest(Authentication authentication) throws Exception{
		User user = userService.findUserByEmail(authentication.getName());
		if (user == null) {
			throw new Exception("Invalid user, please provide correct user credentials.");
		}
		return user;
	}
}
