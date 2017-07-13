package org.social.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.social.entities.Invitation;
import org.social.entities.User;
import org.social.repository.InvitationRepository;
import org.social.service.InvitationService;
import org.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvitationServiceImpl implements InvitationService {

	@Autowired
	private UserService userService;

	@Autowired
	private InvitationRepository invitationRepository;

	@Override
	public User acceptInvitation(long idUser, long idMembre) {

		User user=userService.AddFriendToUser(idUser, idMembre);

		invitationRepository.deleteInvitationByIdUserAndIdMembre(idUser, idMembre);
		
		return user;

	}

	@Override
	public void refuseInvitation(long idUser, long idMembre) {

		invitationRepository.deleteInvitationByIdUserAndIdMembre(idUser, idMembre);
	}

	@Override
	public Invitation addInvitation(Invitation invitation) {

		return invitationRepository.save(invitation);
	}

	@Override
	public List<User> getInvitationsOfUser(long idUser) {
		List<Invitation> invitations = invitationRepository.findInvitationByIdUser(idUser);
		List<User> users = new ArrayList<User>();
		for (Invitation i : invitations) {
			users.add(userService.getUserById(i.getIdMembre()));
		}
		return users;
	}

}
