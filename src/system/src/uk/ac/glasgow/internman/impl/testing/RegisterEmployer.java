package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.Student;
import uk.ac.glasgow.internman.Student.Programme;
import uk.ac.glasgow.internman.impl.EmployerImpl;
import uk.ac.glasgow.internman.impl.InternManTeamW;
import uk.ac.glasgow.internman.impl.StudentImpl;
import uk.ac.glasgow.internman.impl.databases.UserDatabase;

public class RegisterEmployer {

	InternManTeamW s;
	Student student;
	Employer e;
	UserDatabase ub;	
	
	@Before
	public void setUp() throws Exception {
		s = new InternManTeamW();
		ub = UserDatabase.getInstance();
		ub.changeCourseCoordinator("CC", "letmein");
		student = new StudentImpl("Ryan","Wells","ryan@wells.com","1002253w",Programme.SE);
		ub.updateStudent(student);
		ub.addEmployer(new EmployerImpl("BigEmployer","Big@Employer.com","letmein"));
	}

	@After
	public void tearDown() throws Exception {
		ub.changeCourseCoordinator("", "");
	}

	@Test
	public void AddEmployerAsCC() {
		s.login("CC", "letmein");
		e = s.registerNewEmployer("Employer", "Employer@employer.com");
		assertNotNull(e);
	}
	
	@Test
	public void AddEmployerAsStudent(){
		s.login("1002253w", "letmein");
		e = s.registerNewEmployer("Employer", "Employer@employer.com");
		assertNull(e);
	}
	
	@Test
	public void AddEmployerAsEmployer(){
		s.login("BigEmployer", "letmein");
		e = s.registerNewEmployer("Employer", "Employer@employer.com");
		assertNull(e);
	}

}
