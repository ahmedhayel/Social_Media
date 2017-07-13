package org.social.DTO;

import java.io.Serializable;

import org.social.entities.Message;

public class MessageReceiver implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String to;
	private Message message;

	public MessageReceiver() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
