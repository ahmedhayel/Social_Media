package org.social.DTO;

import java.io.Serializable;

public class UserDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idUser;
	private String username;
	private String picture_URL;
	private String firstName;
	private String lastName;
	private String adresse;

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(long idUser, String username, String picture_URL, String firstName, String lastName,
			String adresse) {
		super();
		this.idUser = idUser;
		this.username = username;
		this.picture_URL = picture_URL;
		this.firstName = firstName;
		this.lastName = lastName;
		this.adresse = adresse;
	}

	public String getPicture_URL() {
		return picture_URL;
	}

	public void setPicture_URL(String picture_URL) {
		this.picture_URL = picture_URL;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
