package org.social.DTO;

public class StandardSocket {

	private String type;
	private String to;
	private String message;

	public StandardSocket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StandardSocket(String type, String to, String message) {
		super();
		this.type = type;
		this.to = to;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
