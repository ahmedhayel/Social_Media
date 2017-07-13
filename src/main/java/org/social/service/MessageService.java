package org.social.service;

import java.util.List;

import org.social.DTO.NotificationMessage;
import org.social.entities.Message;

public interface MessageService {

	public Message addMessage(Message message);

	public List<Message> getAllMessageByUserIdAndFriendId(long userId, long friendId);

	public List<NotificationMessage> notification(long idUser);
	
	public Message getLastMessage(long userId, long friendId);

}
