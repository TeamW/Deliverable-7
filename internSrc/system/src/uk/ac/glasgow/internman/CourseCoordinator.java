package uk.ac.glasgow.internman;

public interface CourseCoordinator {

	String getUsername();
	
	boolean authenticate(String password);

}
