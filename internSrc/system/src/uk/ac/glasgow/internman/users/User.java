package uk.ac.glasgow.internman.users;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public boolean authenticate(String password) {
		return this.password.equals(password);
	}
}
