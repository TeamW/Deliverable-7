package uk.ac.glasgow.internman.impl.login;

public interface Login {

	public boolean verifyUser(String id, String password);
	
	public boolean userLoggedIn(String id);
	
}
