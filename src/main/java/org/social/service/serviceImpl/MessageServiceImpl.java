package org.social.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.social.DTO.NotificationMessage;
import org.social.entities.Message;
import org.social.repository.MessageRepository;
import org.social.repository.UserRepository;
import org.social.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Message addMessage(Message message) {

		return messageRepository.save(message);
	}

	@Override
	public List<Message> getAllMessageByUserIdAndFriendId(long userId, long friendId) {

		return messageRepository.findMessageTop50ByIdUserAndIdFriendOrderByDateDesc(userId, friendId);
	}

	@Override
	public List<NotificationMessage> notification(long idUser) {

		List<Message> messages = messageRepository.getAllMessageNotification(idUser);

		List<NotificationMessage> notifications = new ArrayList<NotificationMessage>();

		for (Message message : messages) {
			notifications.add(new NotificationMessage(message.getMessageContent(), message.getDate(),
					userRepository.findOne(message.getIdUser())));

		}
		return notifications;
	}

	@Override
	public Message getLastMessage(long userId, long friendId) {

		return messageRepository.getLastMessageNotification(userId, friendId);
	}

}
