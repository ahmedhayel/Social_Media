package org.social.DTO;

import java.util.Date;

public class ResultMessage {
	private String messageContent;
	private Date date;

	public ResultMessage() {
		super();
	}

	public ResultMessage(String messageContent, Date date) {
		super();
		this.messageContent = messageContent;
		this.date = date;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
