package uk.ac.glasgow.internman.impl.databases;

public interface AuthenticateInterface {

	public boolean loginStudent(String guid, String password);

	public boolean loginEmployer(String name, String password);
	
	public boolean loginCourseCoordinator(String name, String password);

	public UserDatabase getInstance();

}
