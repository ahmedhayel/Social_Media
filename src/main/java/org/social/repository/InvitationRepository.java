package org.social.repository;

import java.util.List;

import org.social.entities.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

	public Invitation findInvitationByIdUserAndIdMembre(long idUser, long idMembre);

	@Modifying
	@Transactional
	@Query("delete from Invitation i where i.idUser=:idUser and i.idMembre=:idMembre")
	public void deleteInvitationByIdUserAndIdMembre(@Param("idUser") long idUser, @Param("idMembre") long idMembre);

	public List<Invitation> findInvitationByIdUser(long idUser);

}
