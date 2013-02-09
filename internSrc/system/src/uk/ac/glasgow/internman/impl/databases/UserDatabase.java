package uk.ac.glasgow.internman.impl.databases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import uk.ac.glasgow.internman.*;
import uk.ac.glasgow.internman.impl.*;

public class UserDatabase implements AdminDutiesInterface,
		AuthenticateInterface {

	private boolean studentDatabaseLoaded;
	private static final String studentDatabaseLocation = System
			.getProperty("user.dir") + "/student.database";
	private File studentDatabase;
	private HashMap<String, Student> students;

	private boolean employerDatabaseLoaded;
	private static final String employerDatabaseLocation = System
			.getProperty("user.dir") + "/employer.database";
	private File employerDatabase;
	private HashMap<String, Employer> employers;

	public UserDatabase() {
		studentDatabaseLoaded = false;
		employerDatabaseLoaded = false;
		loadStudentDatabase();
		loadEmployerDatabase();
	}

	@Override
	public boolean loginStudent(String guid, String password) {
		if (!studentDatabaseLoaded) {
			studentDatabaseLoaded = loadStudentDatabase();
		}
		Student student = students.get(guid);
		return (student == null) ? false : student.authenticate(password);
	}

	@Override
	public boolean loginEmployer(String name, String password) {
		if (!employerDatabaseLoaded) {
			employerDatabaseLoaded = loadEmployerDatabase();
		}
		Employer employer = employers.get(name);
		return (employer == null) ? false : employer.authenticate(password);
	}

	@Override
	public boolean addEmployer(String employerName, String employerEmail,
			String password) {
		if (!employerDatabaseLoaded) {
			employerDatabaseLoaded = loadEmployerDatabase();
		}
		return false;
	}

	@Override
	public Employer getEmployer(String employerName) {
		if (!employerDatabaseLoaded) {
			employerDatabaseLoaded = loadEmployerDatabase();
		}
		return null;
	}

	@Override
	public Student getStudent(String guid) {
		if (!studentDatabaseLoaded) {
			studentDatabaseLoaded = loadStudentDatabase();
		}
		return null;
	}

	@Override
	public void updateStudent(Student student) {
		if (!studentDatabaseLoaded) {
			studentDatabaseLoaded = loadStudentDatabase();
		}
	}

	@SuppressWarnings("unchecked")
	private boolean loadStudentDatabase() {
		if (studentDatabaseLoaded) {
			return true;
		}
		ObjectInputStream input = null;
		studentDatabase = new File(studentDatabaseLocation);
		if (studentDatabase.exists()) {
			try {
				input = new ObjectInputStream(new FileInputStream(
						studentDatabase));
				students = (HashMap<String, Student>) (input.readObject());
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			System.out.println("Having to create database, okay on first run.");
			students = new HashMap<String, Student>();
			ObjectOutputStream oos = null;
			try {
				studentDatabase = new File(studentDatabaseLocation);
				FileOutputStream fos = new FileOutputStream(studentDatabase);
				oos = new ObjectOutputStream(fos);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			try {
				oos.writeObject(students);
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

	@SuppressWarnings("unchecked")
	private boolean loadEmployerDatabase() {
		if (employerDatabaseLoaded) {
			return true;
		}
		ObjectInputStream input = null;
		employerDatabase = new File(employerDatabaseLocation);
		if (employerDatabase.exists()) {
			try {
				input = new ObjectInputStream(new FileInputStream(
						employerDatabase));
				employers = (HashMap<String, Employer>) (input.readObject());
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			System.out.println("Having to create database, okay on first run.");
			employers = new HashMap<String, Employer>();
			ObjectOutputStream oos = null;
			try {
				employerDatabase = new File(employerDatabaseLocation);
				FileOutputStream fos = new FileOutputStream(employerDatabase);
				oos = new ObjectOutputStream(fos);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			try {
				oos.writeObject(employers);
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

	public static final void main(String[] args) {
		UserDatabase db = new UserDatabase();
	}
}
