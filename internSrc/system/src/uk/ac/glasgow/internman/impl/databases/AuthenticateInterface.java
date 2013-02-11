package uk.ac.glasgow.internman.impl.databases;

public interface AuthenticateInterface {

	/**
	 * loginStudent takes two parameters representing the username and password
	 * of a student. The method will return true if the student with the
	 * specified username exists and the given password is correct.
	 * 
	 * @param username
	 *            String representing username of user
	 * @param password
	 *            String representing password of same user
	 * @return True if username exists and password matches, false otherwise.
	 */
	public boolean loginStudent(String username, String password);

	/**
	 * loginEmployer takes two parameters representing the username and password
	 * of an employer. The method will return true if the employer with the
	 * specified username exists and the given password is correct.
	 * 
	 * @param username
	 *            String representing employer username
	 * @param password
	 *            String representing password of same user
	 * @return True if employer exists and password matches, false otherwise.
	 */
	public boolean loginEmployer(String username, String password);

	/**
	 * loginCourseCoordinator takes two parameters representing the username and
	 * password of the course coordinator. This method will return true if the
	 * course coordinator with the specified username exists and the given
	 * password is correct.
	 * 
	 * @param username
	 *            String representing course coordinator username
	 * @param password
	 *            String representing password of same user
	 * @return True if course coordinator exists and password matches, false
	 *         otherwise.
	 */
	public boolean loginCourseCoordinator(String username, String password);

}
