package org.social.controller;

import java.util.List;

import org.social.DTO.FriendsDTO;
import org.social.DTO.MemberIdsDTO;
import org.social.DTO.NotificationMessage;
import org.social.entities.Invitation;
import org.social.entities.Message;
import org.social.entities.User;
import org.social.service.InvitationService;
import org.social.service.MessageService;
import org.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ChatController {

	@Autowired
	private UserService userService;

	@Autowired
	private InvitationService invitationService;

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/user/{idUser}/friends", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FriendsDTO> getListOfFriends(@PathVariable long idUser) {
		return userService.getFriends(idUser);
	}

	@RequestMapping(value = "/user/{idUser}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable long idUser) {
		return userService.getUserById(idUser);
	}

	@RequestMapping(value = "/user/{idUser}/invitations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getInvitationsOfUser(@PathVariable long idUser) {
		return invitationService.getInvitationsOfUser(idUser);
	}

	@RequestMapping(value = "/user/{idReciver}/notifications", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<NotificationMessage> getAllNotification(@PathVariable long idReciver) {
		return messageService.notification(idReciver);
	}

	@RequestMapping(value = "/user/{idUser}/friends/{idFriend}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Message> getConversation(@PathVariable long idUser, @PathVariable long idFriend) {
		return messageService.getAllMessageByUserIdAndFriendId(idUser, idFriend);
	}

	// besoin de thread
	
	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Message addMessage(@RequestBody Message message) {
		return messageService.addMessage(message);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> searchFriends(@RequestParam String string, @RequestParam long idUser) {
		return userService.searchFriends(string, idUser);
	}

	@RequestMapping(value = "/user/lastMessage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Message lastMessage(@RequestBody MemberIdsDTO ids) {
		return messageService.getLastMessage(ids.getIdUser(), ids.getIdFriend());
	}

	@RequestMapping(value = "/user/friends/invitation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Invitation inviteFriend(@RequestBody Invitation invitation) {
		return invitationService.addInvitation(invitation);
	}

	@RequestMapping(value = "/user/friends/invitation/decision", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public User userDecision(@RequestBody MemberIdsDTO invitation, @RequestParam String decision) {

		if (decision.equals("accept")) {

			return invitationService.acceptInvitation(invitation.getIdUser(), invitation.getIdFriend());
		} else {
			if (decision.equals("refuse")) {
				invitationService.refuseInvitation(invitation.getIdUser(), invitation.getIdFriend());
				return null;
			}
		}
		return null;
	}

}
