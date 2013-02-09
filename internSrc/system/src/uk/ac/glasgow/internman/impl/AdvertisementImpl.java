package uk.ac.glasgow.internman.impl;

import java.util.*;

import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.Role;

public class AdvertisementImpl implements Advertisement {

	private String title;
	private String location;
	private String comments;
	private Employer employer;
	private Map<Integer, Role> roles;
	private int numberOfRoles;
	private AdvertisementStatus status;

	public AdvertisementImpl(String title, String location, Employer employer) {
		this.title = title;
		this.location = location;
		this.employer = employer;
		roles = new HashMap<Integer, Role>();
		numberOfRoles = 0;
		status = AdvertisementStatus.PENDING;
	}

	@Override
	public Map<Integer, Role> getRoles() {
		return roles;
	}

	@Override
	public Employer getEmployer() {
		return employer;
	}

	@Override
	public String getApplicationDetails() {
		return "Title: " + title + "\n" + "Location: " + location + "\n"
				+ "Employer: " + employer.getName();
	}

	@Override
	public String getComments() {
		return comments;
	}

	@Override
	public AdvertisementStatus getStatus() {
		return status;
	}

	@Override
	public Role addNewRole(String title, String location, Date start, Date end,
			String description, Double salary) {
		Role newRole = new RoleImpl(title, location, start, end, description,
				salary);
		numberOfRoles++;
		roles.put(new Integer(numberOfRoles), newRole);
		return newRole;
	}

}
