package org.social.service;

import java.util.List;

import org.social.entities.Invitation;
import org.social.entities.User;

public interface InvitationService {

	public Invitation addInvitation(Invitation invitation);

	public User acceptInvitation(long idUser, long idMembre);

	public void refuseInvitation(long idUser, long idMembre);

	public List<User> getInvitationsOfUser(long idUser);

}
