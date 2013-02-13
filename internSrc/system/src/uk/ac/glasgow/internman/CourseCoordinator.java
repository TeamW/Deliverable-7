package uk.ac.glasgow.internman;

/**
 * The Internship Management System has a single Course Coordinator associated
 * with it. The Course Coordinator is normally a member of academic staff who
 * acts as the administrator. They are able to publish advertisements, manage
 * employer accounts and many other things.
 * 
 * @author TeamW
 * 
 */
public interface CourseCoordinator {

	/**
	 * The CourseCoordinator has a username that is used during login.
	 * 
	 * @return String representing the surname of the CourseCoordinator.
	 */
	String getUsername();
}
