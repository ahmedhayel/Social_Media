package org.social.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.social.DTO.FriendsDTO;
import org.social.entities.Message;
import org.social.entities.User;
import org.social.repository.MessageRepository;
import org.social.repository.UserRepository;
import org.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public User addUser(User user) {

		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {

		return userRepository.findAll();
	}

	@Override
	public List<FriendsDTO> getFriends(long idUser) {

		List<FriendsDTO> friendsDTO = new ArrayList<FriendsDTO>();
		List<User> friends = new ArrayList<User>(userRepository.getUserFriends(idUser));

		for (User f : friends) {
			int counMessage = messageRepository.countNotifications(idUser, f.getIdUser());
			Message message = messageRepository.getLastMessageNotification(idUser, f.getIdUser());
			if (message != null) {
				friendsDTO.add(new FriendsDTO(message.getMessageContent(), counMessage, message.getDate(), f));
			} else {
				friendsDTO.add(new FriendsDTO("No message available", counMessage, new Date(), f));
			}

		}

		return friendsDTO;
	}

	@Override
	public User getUserById(long userId) {

		return userRepository.findUserById(userId);
	}

	@Override
	public User AddFriendToUser(long userId, long friendId) {

		User user = userRepository.findOne(userId);
		User friend = userRepository.findOne(friendId);

		if (friend == null || user == null) {
			new NotFoundException("user not found ! ");
		} else {
			user.addFriend(friend);
			friend.addFriend(user);
		}
		User userf = userRepository.saveAndFlush(friend);
		userRepository.saveAndFlush(user);
		return userf;
	}

	@Override
	public User getUserByEmail(String email) {

		return userRepository.findUserByEmail(email);

	}

	@Override
	public User getUserByEmailAndPassword(String email, String pass) {
		try {
			return userRepository.findUserByEmailAndPassword(email, pass);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public long checkUserExistance(String username, String email, String password) {

		return userRepository.countByUsernameAndEmailAndPassword(username, email, password);
	}

	@Override
	public List<User> searchFriends(String string, long idUser) {

		return userRepository.findUserByUsernameContaining(string, idUser);
	}

}
