package uk.ac.glasgow.internman;

public interface Internship {

	public enum InternshipStatus {
		PENDING, WITHDRAWN, ACCEPTED, APPROVED
	}
	
	String getManager();

	String getManagerEmail();

	boolean isVisitComplete();

	Employer getEmployer();

	Role getRole();

	InternshipStatus getStatus();

	Visit getVisit();
	
	void approve();
	
	void setVisit(Visit v);

	void accept();

	void setRole(Role temp);

}
