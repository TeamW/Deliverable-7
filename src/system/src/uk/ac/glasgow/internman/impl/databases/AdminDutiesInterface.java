package uk.ac.glasgow.internman.impl.databases;

import java.util.Map;

import uk.ac.glasgow.internman.*;

public interface AdminDutiesInterface {

	/**
	 * addEmployer takes an Employer object and, if the Employer isn't already
	 * present, adds it to the database.
	 * 
	 * @param e
	 *            The new employer
	 * @return True if new employer added, false if employer already present.
	 */
	public boolean addEmployer(Employer e);

	/**
	 * getEmployer takes a String representing the username of an employer and
	 * returns the Employer object with that username, or null if the employer
	 * isn't present.
	 * 
	 * @param username
	 *            The username of the employer
	 * @return The employer object, if username matches, or null
	 */
	public Employer getEmployer(String username);

	public Student getStudent(String username);
	
	public Map<String, Student> getStudents();

	public CourseCoordinator getCourseCoordinator();

	public void updateStudent(Student student);

	public void changeCourseCoordinator(String username, String password);

}
