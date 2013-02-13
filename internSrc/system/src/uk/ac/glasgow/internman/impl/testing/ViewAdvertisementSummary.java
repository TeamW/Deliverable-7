package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.Advertisement.AdvertisementStatus;
import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.Student;
import uk.ac.glasgow.internman.Student.Programme;
import uk.ac.glasgow.internman.impl.AdvertisementImpl;
import uk.ac.glasgow.internman.impl.EmployerImpl;
import uk.ac.glasgow.internman.impl.Session;
import uk.ac.glasgow.internman.impl.StudentImpl;
import uk.ac.glasgow.internman.impl.adManager.AdManager;
import uk.ac.glasgow.internman.impl.databases.UserDatabase;

public class ViewAdvertisementSummary {
	
	Session s;
	Advertisement a;
	Student student;
	Employer e;
	AdManager adman;
	UserDatabase ub;

	@Before
	public void setUp() throws Exception {
		s = new Session();
		adman = AdManager.getInstance();
		ub = UserDatabase.getInstance();
		student = new StudentImpl("Ryan","Wells", "other@email.com","1002253w",Programme.SE);
		ub.updateStudent(student);
		ub.changeCourseCoordinator("CC", "letmein");
		e = new EmployerImpl("BigCompany","big@company.com","letmein");
		ub.addEmployer(e);
		adman.createNewAdvertisement(e, "ryan");
}

	@After
	public void tearDown() throws Exception {
		adman.removeAllAdverts();
	}

	@Test
	public void ViewApprovedAdAsStudent() {
		s.login("CC", "letmein");
		s.publishAdvertisement(1, "blood good, ol' chap");
		s.login("1002253w", "letmein");
		a=s.selectAdvertisement(1);
		assertEquals(a.getApplicationDetails(),"ryan");
		//assertEquals(a.getApplicationDetails(),"APPLY FOR ME PLEASE");
	}

}
