package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.Student;
import uk.ac.glasgow.internman.Student.Programme;
import uk.ac.glasgow.internman.impl.AdvertisementImpl;
import uk.ac.glasgow.internman.impl.EmployerImpl;
import uk.ac.glasgow.internman.impl.Session;
import uk.ac.glasgow.internman.impl.StudentImpl;
import uk.ac.glasgow.internman.impl.adManager.AdManager;
import uk.ac.glasgow.internman.impl.databases.UserDatabase;

public class PublishAdvertisementTest {
	
	Session s;
	AdManager a;
	Employer e;
	Student student;
	UserDatabase ub;
	
	@Before
	public void setUp() throws Exception {
		s = new Session();
		ub = UserDatabase.getInstance();
		ub.changeCourseCoordinator("TestCC", "letmein");
		e = new EmployerImpl("someEmployer", "example@example.com", "letmein");
		ub.addEmployer(e);
		student = new StudentImpl("Gordon", "Reid", "example@example.com",
				"1002536r", Programme.SE);
		ub.updateStudent(student);
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	/*
	 * Tests - submit as employer,
	 * submit as CC
	 * submit ad as student
	 */
	
	/**
	 * Submit a valid advert as a Course Coordinator.
	 */
	@Test
	public void publishValidAdAsCC() {
		String adString = "This is a test advertisement";
		// login as CC
		s.login("TestCC", "letmein");
		s.createNewAdvertisement(adString);
		AdvertisementImpl testAd = (AdvertisementImpl) s.createNewAdvertisement(adString);
		System.out.println(testAd.getApplicationDetails());
		assertEquals(adString, testAd.getApplicationDetails());
	}
	


}
