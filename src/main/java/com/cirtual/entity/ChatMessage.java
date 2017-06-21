package com.cirtual.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@Entity
@Table(name="chat")
public class ChatMessage {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@OneToOne
    @JoinColumn(name = "authorUserId")
	@JsonProperty("authorUserId")
	private User authorUser;
	
	@OneToOne
    @JoinColumn(name = "recipientUserId")
	@JsonProperty("recipientUserId")
	private User recipientUser;
	
	private String message;
	
	@Temporal(TemporalType.DATE)
	private Date timestamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getAuthorUser() {
		return authorUser;
	}

	public void setAuthorUser(User authorUser) {
		this.authorUser = authorUser;
	}

	public User getRecipientUser() {
		return recipientUser;
	}

	public void setRecipientUser(User recipientUser) {
		this.recipientUser = recipientUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


}
