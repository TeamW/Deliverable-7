package uk.ac.glasgow.internman;

/**
 * An Employer is allowed to publish advertisements for jobs it has available.
 * Employers are registered by the course coordinator. The course coordinator
 * also has to approve advertisements posted by an employer.
 * 
 * @author TeamW
 * 
 */
public interface Employer {

	/**
	 * Each employer has a name, typically either the company name or hiring
	 * manager.
	 * 
	 * @return String representing employer name
	 */
	String getName();

	/**
	 * Each employer has an email that serves as the primary contact method.
	 * 
	 * @return String representing the email address of the employer.
	 */
	String getEmailAddress();

	/**
	 * The employer has a username that is used during login.
	 * 
	 * @return String representing the surname of the employer.
	 */
	String getUsername();

}
