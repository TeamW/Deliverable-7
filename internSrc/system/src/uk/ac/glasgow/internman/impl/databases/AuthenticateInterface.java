package uk.ac.glasgow.internman.impl.databases;

public interface AuthenticateInterface {

	/**
	 * loginStudent takes two parameters representing the username and password
	 * of a student. The method will return true if the student with the
	 * specified guid exists and the given password is correct.
	 * 
	 * @param guid
	 *            String representing GUID of user
	 * @param password
	 *            String representing password of same user
	 * @return True if guid exists and password matches, false otherwise.
	 */
	public boolean loginStudent(String guid, String password);

	/**
	 * loginEmployer takes two parameters representing the username and password
	 * of an employer. The method will return true if the employer with the
	 * specified username exists and the given password is correct.
	 * 
	 * @param name
	 *            String representing employer username
	 * @param password
	 *            String representing password of same user
	 * @return True if employer exists and password matches, false otherwise.
	 */
	public boolean loginEmployer(String name, String password);

	/**
	 * loginCourseCoordinator takes two parameters representing the username and
	 * password of the course coordinator. This method will return true if the
	 * course coordinator with the specified username exists and the given
	 * password is correct.
	 * 
	 * @param name
	 *            String representing course coordinator username
	 * @param password
	 *            String representing password of same user
	 * @return True if course coordinator exists and password matches, false
	 *         otherwise.
	 */
	public boolean loginCourseCoordinator(String name, String password);

}
