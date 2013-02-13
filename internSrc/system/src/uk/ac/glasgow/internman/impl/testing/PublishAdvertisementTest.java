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
import uk.ac.glasgow.internman.impl.adManager.AdManager;
import uk.ac.glasgow.internman.impl.databases.UserDatabase;

public class PublishAdvertisementTest {

	/*
	 * ISSUE - facade asks for an integer key to retrieve an advert from the
	 * database. There doesn't seem to be any way to get this key for any
	 * specific advert, so publishing an advert isn't possible in the current
	 * version of the system.
	 */

	InternManTeamW s;
	AdManager a;
	Employer e;
	Student student;
	UserDatabase ub;

	@Before
	public void setUp() throws Exception {
		s = new InternManTeamW();
		ub = UserDatabase.getInstance();
		ub.changeCourseCoordinator("TestCC", "letmein");
		e = new EmployerImpl("someEmployer", "example@example.com", "letmein");
		ub.addEmployer(e);
		student = new StudentImpl("Gordon", "Reid", "example@example.com",
				"1002536r", Programme.SE);
		ub.updateStudent(student);
		a = AdManager.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		a.removeAllAdverts();
	}

	/**
	 * Submit a valid advert as a Course Coordinator.
	 */
	@Test
	public void publishValidAdAsCC() {
		String adString = "This is a test advertisement";
		// login as CC
		s.login("TestCC", "letmein");
		s.createNewAdvertisement(adString);
		/*
		 * AdvertisementImpl testAd = (AdvertisementImpl) s
		 * .createNewAdvertisement(adString);
		 */
		// This method (commented out) is the source of the problem.
		// s.publishAdvertisement(advertisementIndex,
		// "Can't submit this without index");
		fail();
	}

}
