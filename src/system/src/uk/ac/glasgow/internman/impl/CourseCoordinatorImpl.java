package uk.ac.glasgow.internman.impl;

import uk.ac.glasgow.internman.CourseCoordinator;
import uk.ac.glasgow.internman.users.User;

/**
 * {@link uk.ac.glasgow.internman.CourseCoordinator}
 * 
 * @author TeamW
 * 
 */
public class CourseCoordinatorImpl extends User implements CourseCoordinator {

	private static final long serialVersionUID = 1L;

	public CourseCoordinatorImpl(String username, String password) {
		super(username, password);
	}

}
