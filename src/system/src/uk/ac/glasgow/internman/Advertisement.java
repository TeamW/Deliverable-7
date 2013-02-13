package uk.ac.glasgow.internman;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * This class represents an advertisement within the internship management
 * system. An advertisement can be either pending, where only the course
 * coordinator and submitting employer can view it, or published, where any
 * student can see it as well.
 * 
 * Each advertisement has a number of roles, an employer, and a status (as
 * described above) associated with it.
 * 
 * @author TeamW
 * 
 */
public interface Advertisement extends Serializable {

	/**
	 * An advertisement status is initially pending (just after a company
	 * creates it). The course coordinator can then change the status to
	 * published, allowing any student to view it.
	 * 
	 * @author TeamW
	 * 
	 */
	public enum AdvertisementStatus {
		PENDING, PUBLISHED
	}

	/**
	 * An advertisement can have a number of job roles associated with it. This
	 * method returns every role and it's identifying integer in a map data
	 * structure for fast access.
	 * 
	 * @return A Map with Integer/Role Key.Value pairs.
	 */
	Map<Integer, Role> getRoles();

	/**
	 * An advertisement has one employer associated with it. This method just
	 * returns the object representing the employer who submitted the
	 * advertisement.
	 * 
	 * @return The Employer object that represents the company who submitted the
	 *         advertisement.
	 */
	Employer getEmployer();

	/**
	 * Each advertisement has a variable amount of employer-supplied details
	 * associated with it. This method just returns all of those details.
	 * 
	 * @return String representing the details associated with the
	 *         advertisement.
	 */
	String getApplicationDetails();

	/**
	 * A course coordinator may, at their choosing, add comments to an
	 * advertisement when reviewing it. If so, these will be made available with
	 * this method.
	 * 
	 * @return String of comments by course coordinator or null if no comments
	 *         available.
	 */
	String getComments();

	/**
	 * An advertisement status is initially pending (just after a company
	 * creates it). The course coordinator can then change the status to
	 * published, allowing any student to view it.
	 * 
	 * @return The application's status.
	 */
	AdvertisementStatus getStatus();

	/**
	 * A course coordinator will review the advertisement and, if suitable, will
	 * publish the advertisement. This method changes the status to published.
	 */
	void PublishAd();

	/**
	 * Each advertisement can have many roles (at least one) associated with it.
	 * This method will add a role to the advertisement.
	 * 
	 * @param title
	 *            The String representing the job title of the role.
	 * @param location
	 *            The String representing where the job will be based.
	 * @param start
	 *            The Date which the job will begin.
	 * @param end
	 *            The Date which the job will finish.
	 * @param description
	 *            The String representing the description of the role.
	 * @param salary
	 *            A Double representing the annual pay for the job.
	 * @return The Role object added if successful, null otherwise.
	 */
	Role addNewRole(String title, String location, Date start, Date end,
			String description, Double salary);

}
