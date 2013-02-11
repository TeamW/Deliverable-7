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

	private boolean ccDatabaseLoaded;
	private static final String ccDatabaseLocation = System
			.getProperty("user.dir") + "/cc.database";
	private File ccDatabase;
	private CourseCoordinator cc;

	private static final UserDatabase userDatabase = new UserDatabase();

	public static void main(String[] args) {
		getInstance();
	}

	private UserDatabase() {
		studentDatabaseLoaded = loadStudentDatabase();
		employerDatabaseLoaded = loadEmployerDatabase();
		ccDatabaseLoaded = loadCCDatabase();
	}

	public static UserDatabase getInstance() {
		return userDatabase;
	}

	@Override
	public boolean loginStudent(String username, String password) {
		if (!studentDatabaseLoaded) {
			studentDatabaseLoaded = loadStudentDatabase();
		}
		Student student = students.get(username);
		return (student == null) ? false : student.authenticate(password);
	}

	@Override
	public boolean loginEmployer(String username, String password) {
		if (!employerDatabaseLoaded) {
			employerDatabaseLoaded = loadEmployerDatabase();
		}
		Employer employer = employers.get(username);
		return (employer == null) ? false : employer.authenticate(password);
	}

	@Override
	public boolean loginCourseCoordinator(String username, String password) {
		if (!ccDatabaseLoaded) {
			ccDatabaseLoaded = loadCCDatabase();
		}
		return (cc == null) ? false : cc.getUsername().equals(username)
				&& cc.authenticate(password);
	}

	@Override
	public boolean addEmployer(Employer e) {
		if (!employerDatabaseLoaded) {
			employerDatabaseLoaded = loadEmployerDatabase();
		}
		if (employers.get(e.getUsername()) != null) {
			System.out.println("Employer already exists.");
			return false;
		}
		employers.put(e.getUsername(), e);
		updateEmployerDatabase();
		return true;
	}

	@Override
	public Employer getEmployer(String username) {
		if (!employerDatabaseLoaded) {
			employerDatabaseLoaded = loadEmployerDatabase();
		}
		return employers.get(username);
	}

	@Override
	public Student getStudent(String username) {
		if (!studentDatabaseLoaded) {
			studentDatabaseLoaded = loadStudentDatabase();
		}
		return students.get(username);
	}

	public CourseCoordinator getCourseCoordinator() {
		return cc;
	}

	@Override
	public void updateStudent(Student student) {
		if (!studentDatabaseLoaded) {
			studentDatabaseLoaded = loadStudentDatabase();
		}
		Student temp = students.get(student.getMatriculation());
		if (temp == null) {
			System.out.println("Student didn't exist in database, adding now.");
			students.put(student.getUsername(), student);
		} else {
			temp = student;
		}
		updateStudentDatabase();
	}

	@Override
	public void changeCourseCoordinator(String username, String password) {
		cc = new CourseCoordinatorImpl(username, password);
		updateCCDatabase();
	}

	private boolean loadCCDatabase() {
		if (ccDatabaseLoaded) {
			return true;
		}
		ObjectInputStream input = null;
		ccDatabase = new File(ccDatabaseLocation);
		if (ccDatabase.exists()) {
			try {
				input = new ObjectInputStream(new FileInputStream(ccDatabase));
				cc = (CourseCoordinator) (input.readObject());
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			System.out.println("Having to create database, okay on first run.");
			cc = new CourseCoordinatorImpl("test", "letmein");
			ObjectOutputStream oos = null;
			try {
				ccDatabase = new File(ccDatabaseLocation);
				FileOutputStream fos = new FileOutputStream(ccDatabase);
				oos = new ObjectOutputStream(fos);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			try {
				oos.writeObject(cc);
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

	private boolean updateStudentDatabase() {
		if (!studentDatabaseLoaded) {
			return false;
		}
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
		return true;
	}

	private boolean updateCCDatabase() {
		if (!ccDatabaseLoaded) {
			return false;
		}
		ObjectOutputStream oos = null;
		try {
			ccDatabase = new File(ccDatabaseLocation);
			FileOutputStream fos = new FileOutputStream(ccDatabase);
			oos = new ObjectOutputStream(fos);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		try {
			oos.writeObject(cc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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

	private boolean updateEmployerDatabase() {
		if (!employerDatabaseLoaded) {
			return false;
		}
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
		return true;
	}

}
