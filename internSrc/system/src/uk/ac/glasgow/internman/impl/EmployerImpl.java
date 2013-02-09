package uk.ac.glasgow.internman.impl;

import uk.ac.glasgow.internman.Employer;

public class EmployerImpl implements Employer {

	private String name;
	private String emailAddress;
	private String password;

	public EmployerImpl(String name, String emailAddress) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.password = "letmein";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getEmailAddress() {
		return emailAddress;
	}

	@Override
	public boolean authenticate(String password) {
		return this.password.equals(password);
	}

}
