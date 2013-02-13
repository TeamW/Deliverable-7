package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.*;
import uk.ac.glasgow.internman.Student.Programme;
import uk.ac.glasgow.internman.impl.*;
import uk.ac.glasgow.internman.impl.databases.UserDatabase;

public class LoginTest {

	InternManTeamW s;
	UserDatabase ub;
	Student student;
	Employer e;

	@Before
	public void setUp() throws Exception {
		s = new InternManTeamW();
		ub = UserDatabase.getInstance();
		ub.changeCourseCoordinator("TestCC", "letmein");
		student = new StudentImpl("Gordon", "Reid", "example@example.com",
				"1002536r", Programme.SE);
		ub.updateStudent(student);
		e = new EmployerImpl("someEmployer", "example@example.com", "letmein");
		ub.addEmployer(e);
	}

	
	/**
	**Test case for Correct student Login - should Return true 
	*	*/
	
	@Test
	public void studentLoginCorrect() {
		assertEquals(s.login("1002536r", "letmein"), true);
	}

	/**
	**Test case 1 for InCorrect student Login -
	*Password is Incorrect
	* should Return false 
	*	*/
	
	@Test
	public void studentLoginIncorrectPassword() {
		assertEquals(s.login("1002536r", "notmypassword"), false);
	}

	/**
	**Test case 2 for InCorrect student Login
	*Username is incorrect
	* - should Return false 
	*	*/
	
	@Test
	public void studentLoginIncorrectUsername() {
		assertEquals(s.login("000000a", "notmypassword"), false);
	}
	
	
	/**
	**Test case for Correct employer Login -
	** should Return true
	*	*/

	@Test
	public void employerLoginCorrect() {
		assertEquals(s.login("someEmployer", "letmein"), true);
	}

	/**
	**Test case 1 for InCorrect employer Login -
	*Password is Incorrect
	* should Return false 
	*	*/
	
	@Test
	public void employerLoginIncorrectPassword() {
		assertEquals(s.login("someEmployer", "notmypassword"), false);
	}
	
	/**
	**Test case 2 for InCorrect employer Login -
	*User is Incorrect
	* should Return false 
	*	*/

	@Test
	public void employerLoginIncorrectUsername() {
		assertEquals(s.login("notAnEmployer", "notmypassword"), false);
	}

	/**
	**Test case for Correct Course Coordinator Login -
	** should Return true
	*	*/
	
	@Test
	public void ccLoginCorrect() {
		assertEquals(s.login("TestCC", "letmein"), true);
	}

	/**
	**Test case for 1 InCorrect Course Coordinator Login -
	*password is incorrect
	** should Return false
	*	*/
	
	@Test
	public void ccLoginIncorrectPassword() {
		assertEquals(s.login("TestCC", "notmypassword"), false);
	}

	/**
	**Test case for 2 InCorrect Course Coordinator Login -
	*Username is incorrect
	** should Return false
	*	*/
	
	@Test
	public void ccLoginIncorrectUsername() {
		assertEquals(s.login("NotTheCC", "notmypassword"), false);
	}
}
