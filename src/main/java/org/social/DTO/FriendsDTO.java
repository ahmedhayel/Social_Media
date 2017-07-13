package org.social.DTO;

import java.io.Serializable;
import java.util.Date;

import org.social.entities.User;

public class FriendsDTO  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lastMessageContent;
	private int countMessages;
	private Date date;
	//user dto later
	private User friend;

	public FriendsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FriendsDTO(String lastMessageContent, int countMessages, Date date, User friend) {
		super();
		this.lastMessageContent = lastMessageContent;
		this.countMessages = countMessages;
		this.date = date;
		this.friend = friend;
	}

	public String getLastMessageContent() {
		return lastMessageContent;
	}

	public void setLastMessageContent(String lastMessageContent) {
		this.lastMessageContent = lastMessageContent;
	}

	public int getCountMessages() {
		return countMessages;
	}

	public void setCountMessages(int countMessages) {
		this.countMessages = countMessages;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

}
