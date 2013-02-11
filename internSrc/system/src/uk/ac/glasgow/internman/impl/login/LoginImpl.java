package uk.ac.glasgow.internman.impl.login;

import uk.ac.glasgow.internman.impl.databases.UserDatabase;
import uk.ac.glasgow.internman.users.User;

public class LoginImpl implements Login {

	private static UserDatabase UD = UserDatabase.getInstance();

	public LoginImpl() {// TODO Auto-generated constructor stub

	}

	@Override
	public User verifyUser(String id, String password) {
		if (UD.loginStudent(id, password))
			return (User) UD.getStudent(id);
		if (UD.loginEmployer(id, password))
			return (User) UD.getEmployer(id);
		if (UD.loginCourseCoordinator(id, password))
			return (User) UD.getCourseCoordinator();
		return null;

	}

}
