package uk.ac.glasgow.internman.impl;

import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.users.User;

public class EmployerImpl extends User implements Employer {

	private static final long serialVersionUID = 1L;
	private String name;
	private String emailAddress;

	public EmployerImpl(String name, String emailAddress) {
		super(name, "letmein");
		this.name = name;
		this.emailAddress = emailAddress;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getEmailAddress() {
		return emailAddress;
	}
}
