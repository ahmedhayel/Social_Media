package org.social.service;

import java.util.List;

import org.social.DTO.FriendsDTO;
import org.social.entities.User;

public interface UserService {

	public User addUser(User user);

	public List<User> getAllUser();

	public List<FriendsDTO> getFriends(long userId);

	public User getUserById(long userId);

	public User getUserByEmail(String email);

	public User AddFriendToUser(long userId, long friendId);

	public User getUserByEmailAndPassword(String email, String pass);

	public long checkUserExistance(String username, String email, String password);

	public List<User> searchFriends(String string, long idUser);
}
