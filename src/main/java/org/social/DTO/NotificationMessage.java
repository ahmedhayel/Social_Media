package org.social.DTO;

import java.io.Serializable;
import java.util.Date;

import org.social.entities.User;

public class NotificationMessage  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lastmessageContent;
	private Date date;
	private User user;

	public NotificationMessage(String lastmessageContent, Date date, User user) {
		super();
		this.lastmessageContent = lastmessageContent;
		this.date = date;
		this.user = user;
	}

	public NotificationMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLastmessageContent() {
		return lastmessageContent;
	}

	public void setLastmessageContent(String lastmessageContent) {
		this.lastmessageContent = lastmessageContent;
	}

}
