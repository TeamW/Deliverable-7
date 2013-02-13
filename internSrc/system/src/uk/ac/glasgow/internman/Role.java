package uk.ac.glasgow.internman;

import java.util.Date;

/**
 * An advertisement can be for many different roles. This class represents one
 * of those roles.
 * 
 * @author TeamW
 * 
 */
public interface Role {

	/**
	 * Each role has a job title. This method returns it.
	 * 
	 * @return String representing job title.
	 */
	String getTitle();

	/**
	 * Each role has a start date. This method returns it.
	 * 
	 * @return Date representing start date.
	 */
	Date getStart();

	/**
	 * Each role has a end date. This method returns it.
	 * 
	 * @return Date representing end date.
	 */
	Date getEnd();

	/**
	 * Each role has an annual salary. This method returns it.
	 * 
	 * @return Double representing salary.
	 */
	Double getSalary();

	/**
	 * Each role has to be approved by the course coordinator. This method
	 * approves it.
	 * 
	 * @return boolean true if once approved.
	 */
	boolean isApproved();

	/**
	 * Each role has a location where the successful application will be based.
	 * This method returns it.
	 * 
	 * @return String representing job location.
	 */
	String getLocation();
}
