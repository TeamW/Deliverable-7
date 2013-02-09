package uk.ac.glasgow.internman.impl.databases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import uk.ac.glasgow.internman.*;
import uk.ac.glasgow.internman.users.User;

public class UserDatabase implements AdminDutiesInterface,
		AuthenticateInterface {

	private boolean databaseLoaded;
	private static final String databaseLocation = System
			.getProperty("user.dir") + "/users.database";
	private File userDatabase;
	private HashMap<String, User> users;

	public UserDatabase() {
		databaseLoaded = false;
	}

	@Override
	public boolean login(String username, String password) {
		if (!databaseLoaded) {
			databaseLoaded = loadUserDatabase();
		}
		User user = users.get(username);
		return (user == null) ? false : user.authenticate(password);
	}

	@Override
	public boolean addEmployer(String employerName, String employerEmail,
			String password) {
		if (!databaseLoaded) {
			databaseLoaded = loadUserDatabase();
		}
		return false;
	}

	@Override
	public Employer getEmployer(String employerName) {
		if (!databaseLoaded) {
			databaseLoaded = loadUserDatabase();
		}
		return null;
	}

	@Override
	public Student getStudent(String guid) {
		if (!databaseLoaded) {
			databaseLoaded = loadUserDatabase();
		}
		return null;
	}

	@Override
	public void updateStudent(Student student) {
		if (!databaseLoaded) {
			databaseLoaded = loadUserDatabase();
		}
	}

	@SuppressWarnings("unchecked")
	private boolean loadUserDatabase() {
		if (databaseLoaded) {
			return true;
		}
		ObjectInputStream input = null;
		userDatabase = new File(databaseLocation);
		if (userDatabase.exists()) {
			try {
				input = new ObjectInputStream(new FileInputStream(userDatabase));
				users = (HashMap<String, User>) (input.readObject());
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			System.out.println("Having to create database, okay on first run.");
			users = new HashMap<String, User>();
			users.put("test", new User("", "", "test", "letmein"));
			ObjectOutputStream oos = null;
			try {
				userDatabase = new File(databaseLocation);
				FileOutputStream fos = new FileOutputStream(userDatabase);
				oos = new ObjectOutputStream(fos);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			try {
				oos.writeObject(users);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private void saveToDatabase() {
		if (!databaseLoaded) {
			System.out.println("Cannot save to database. No database loaded.");
			return;
		}
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(databaseLocation));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		try {
			oos.writeObject(users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public static final void main(String[] args) {
		UserDatabase db = new UserDatabase();
		System.out.println("Logged in: " + db.login("test", "letmein"));
		System.out.println("Logged in: " + db.login("test", "don'tletmein"));
		System.out.println("Logged in: " + db.login("idon'texist", "letmein"));

	}

}
