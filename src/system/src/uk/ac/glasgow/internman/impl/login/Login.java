package uk.ac.glasgow.internman.impl.login;

import uk.ac.glasgow.internman.users.User;

public interface Login {

	//return user type - cast to user type dont return boolean
	public User verifyUser(String id, String password);
	

}
 	