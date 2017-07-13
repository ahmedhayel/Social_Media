package org.social.DTO;

import java.io.Serializable;

public class MemberIdsDTO  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idUser;
	private long idFriend;

	public MemberIdsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberIdsDTO(long idUser, long idFriend) {
		super();
		this.idUser = idUser;
		this.idFriend = idFriend;
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

}
