package org.social.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MESSAGE")
public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MESSAGE_ID")
	private long idMessage;

	@Column(name = "USER_TRANSMITTER", nullable = false)
	private long idUser;

	@Column(name = "USER_RECEIVER", nullable = false)
	private long idFriend;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE")
	private Date date;

	@Column(name = "MESSAGE", nullable = false, length = 512)
	private String messageContent;
	
	@Column(name="REEDED", nullable = false)
	private boolean status;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(long idUser, long idFriend, Date date, String messageContent) {
		super();
		this.idUser = idUser;
		this.idFriend = idFriend;
		this.date = date;
		this.messageContent = messageContent;
	}

	public long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(long idMessage) {
		this.idMessage = idMessage;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdFriend() {
		return idFriend;
	}

	public void setIdFriend(long idFriend) {
		this.idFriend = idFriend;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

}
