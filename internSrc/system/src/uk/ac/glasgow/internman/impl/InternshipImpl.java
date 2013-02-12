package uk.ac.glasgow.internman.impl;

import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.Internship;
import uk.ac.glasgow.internman.Role;
import uk.ac.glasgow.internman.Visit;

public class InternshipImpl implements Internship {

	private String manager;
	private String managerEmail;
	private boolean visitComplete;
	private Employer employer;
	private Role role;
	private InternshipStatus status;
	private Visit visit;

	public InternshipImpl() {

	}

	public InternshipImpl(String manager, String managerEmail,
			Employer employer, Role role) {
		this.manager = manager;
		this.managerEmail = managerEmail;
		this.visitComplete = false;
		this.employer = employer;
		this.role = role;
		this.status = InternshipStatus.PENDING;
		this.visit = null;
	}

	@Override
	public String getManager() {
		return manager;
	}

	@Override
	public String getManagerEmail() {
		return managerEmail;
	}

	@Override
	public boolean isVisitComplete() {
		return visitComplete;
	}

	@Override
	public Employer getEmployer() {
		return employer;
	}

	@Override
	public Role getRole() {
		return role;
	}

	@Override
	public InternshipStatus getStatus() {
		return status;
	}

	@Override
	public Visit getVisit() {
		return visit;
	}
	
	@Override
	public void approve(){
		this.status = InternshipStatus.APPROVED;
	}
	
	@Override
	public void setVisit(Visit v){
		this.visit = v;
	}

}
