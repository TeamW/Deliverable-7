package uk.ac.glasgow.internman.impl;

import java.util.Date;

import uk.ac.glasgow.internman.Role;

public class RoleImpl implements Role {

	private String title;
	private String location;
	private Date start;
	private Date end;
	private String description;
	private Double salary;
	private boolean approved;

	public RoleImpl(String title, String location, Date start, Date end,
			String description, Double salary) {
		this.title = title;
		this.location = location;
		this.start = start;
		this.end = end;
		this.description = description;
		this.salary = salary;
		approved = false;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Date getStart() {
		return start;
	}

	@Override
	public Date getEnd() {
		return end;
	}

	@Override
	public Double getSalary() {
		return salary;
	}

	@Override
	public boolean isApproved() {
		return approved;
	}

	@Override
	public String getLocation() {
		return location;
	}

}
