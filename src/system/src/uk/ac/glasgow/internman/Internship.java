package uk.ac.glasgow.internman;

/**
 * Internships are central to the system. These can be associated with students
 * once they've applied, and received, an internship obtained eitiher through
 * the system or self-sourced.
 * 
 * @author TeamW
 * 
 */
public interface Internship {

	/**
	 * An internship can have many statuses: 1. Pending approval 2. Withdrawn 3.
	 * Accepted by student 4. Approved by course coordinator
	 * 
	 * @author TeamW
	 * 
	 */
	public enum InternshipStatus {
		PENDING, WITHDRAWN, ACCEPTED, APPROVED
	}

	/**
	 * Internships have a job manager
	 * 
	 * @return String representing manger name
	 */
	String getManager();

	/**
	 * Internships have a job manager
	 * 
	 * @return String representing manger emal
	 */
	String getManagerEmail();

	/**
	 * Internship performance is graded by a visitor. This method returns
	 * whether or not a visit has been completed.
	 * 
	 * @return Boolean true if visit completed, otherwise false.
	 */
	boolean isVisitComplete();

	/**
	 * Internships were submitted by an employer, this returns that employer.
	 * 
	 * @return Employer object of the employer who submitted the advertisement.
	 */
	Employer getEmployer();

	/**
	 * Internships have a one-to-one relationship with an associated role. This
	 * method returns that role.
	 * 
	 * @return Role associated with this internship.
	 */
	Role getRole();

	/**
	 * Internships have a one-to-one relationship with an associated role. This
	 * method returns that role.
	 * 
	 * @param temp
	 *            Role associated with this internship.
	 */
	void setRole(Role temp);

	/**
	 * An internship can have many statuses: 1. Pending approval 2. Withdrawn 3.
	 * Accepted by student 4. Approved by course coordinator
	 * 
	 * @return Internship status
	 */
	InternshipStatus getStatus();

	/**
	 * Each internship has a visit associatedw ith it where the student's
	 * performance is graded.
	 * 
	 * @return Visit object if visit completed, null otherwise.
	 */
	Visit getVisit();

	/**
	 * A course coordinator needs to approve an internship accepted by a
	 * student. This method accepts it.
	 */
	void approve();

	/**
	 * Once a visit has been completed. The information recorded has to be
	 * stored with the internship
	 * 
	 * @param v
	 *            Visit object with information in regards to the student's
	 *            performance.
	 */
	void setVisit(Visit v);

	/**
	 * A student needs to accept an internship. The internship is then sent to
	 * the course coordinator for approval.
	 */
	void accept();

}
