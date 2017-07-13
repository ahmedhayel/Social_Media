package org.social.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INVITATION")
public class Invitation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INVITATION_ID")
	private long idInvitation;

	@Column(name = "USER", nullable = false)
	private long idUser;

	@Column(name = "FRIEND_PREPOSE", nullable = false)
	private long idMembre;

	public Invitation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invitation(long idUser, long idMembre) {
		super();
		this.idUser = idUser;
		this.idMembre = idMembre;
	}

	public long getIdInvitation() {
		return idInvitation;
	}

	public void setIdInvitation(long idInvitation) {
		this.idInvitation = idInvitation;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdMembre() {
		return idMembre;
	}

	public void setIdMembre(long idMembre) {
		this.idMembre = idMembre;
	}

}
