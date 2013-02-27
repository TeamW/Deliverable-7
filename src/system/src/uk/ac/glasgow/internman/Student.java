package uk.ac.glasgow.internman;

import java.util.ArrayList;
import java.util.HashMap;

import uk.ac.glasgow.internman.Internship;

/**
 * A student is a member of the Unversity of Glasgow, Department of Computing
 * Science. They use this system to apply for advertisements for a summer
 * internship.
 * 
 * @author TeamW
 * 
 */
public interface Student {

	/**
	 * A student can be in many different degree programs: 1. Electronic
	 * Software Engineering 2. Software Engineering 3. Computing Science Honours
	 * 4. Computing Science Designated
	 * 
	 * @author TeamW
	 * 
	 */
	public enum Programme {
		ESE, SE, CS3H, CS3
	}

	/**
	 * A student will have internships associated with them.
	 * 
	 * @return A list of internship objects if the student has any, null otherwise.
	 */
	HashMap<Integer, Internship> getInternships();

	/**
	 * Sets an internship the student has.
	 * 
	 * @param i
	 *            Internship object that a student will have.
	 */
	void setInternship(Internship i);

	/**
	 * Student has a surname.
	 * 
	 * @return String representing student's surname.
	 */
	String getSurname();

	/**
	 * Student has a matriculation number.
	 * 
	 * @return String representing student's matriculation number.
	 */
	String getMatriculation();

	/**
	 * Student has a forename.
	 * 
	 * @return String representing student's forename.
	 */
	String getForename();

	/**
	 * Student has an email.
	 * 
	 * @return String representing student's email.
	 */
	String getEmail();

	/**
	 * Student has a username.
	 * 
	 * @return String representing student's username.
	 */
	String getUsername();

	/**
	 * A student has an associated degree programme.
	 * 
	 * @return The programme the student is enrolled in.
	 */
	Programme getProgramme();
	
	/**
	 * Student has a counter for internships added.
	 * 
	 * @return Integer identifying most recent internship accepted.
	 */
	Integer getMaxInternshipId();
	
	/**
	 * Student must have enough internships to cover 12 weeks.
	 * 
	 * @return A boolean indicating if the student has enough internships.
	 */
	Boolean getEnoughInternships();
}
