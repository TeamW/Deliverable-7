package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.Student;
import uk.ac.glasgow.internman.Student.Programme;
import uk.ac.glasgow.internman.impl.EmployerImpl;
import uk.ac.glasgow.internman.impl.InternManTeamW;
import uk.ac.glasgow.internman.impl.StudentImpl;
import uk.ac.glasgow.internman.impl.adManager.AdManager;
import uk.ac.glasgow.internman.impl.databases.UserDatabase;

public class ViewAdvertisementSummary {

	InternManTeamW s;
	Advertisement a;
	Student student;
	Employer e;
	AdManager adman;
	UserDatabase ub;

	@Before
	public void setUp() throws Exception {
		s = new InternManTeamW();
		adman = AdManager.getInstance();
		ub = UserDatabase.getInstance();
		student = new StudentImpl("Ryan", "Wells", "other@email.com",
				"1002253w", Programme.SE);
		ub.updateStudent(student);
		ub.changeCourseCoordinator("CC", "letmein");
		e = new EmployerImpl("c","big@company.com", "letmein");
		ub.addEmployer(e);
		adman.createNewAdvertisement(e, "APPLY FOR ME");
	}

	@After
	public void tearDown() throws Exception {
		adman.removeAllAdverts();
	}

	@Test
	public void ViewApprovedAdAsStudent() {
		s.login("CC", "letmein");
		s.publishAdvertisement(1, "bloody good, ol' chap");
		s.login("1002253w", "letmein");
		a = s.selectAdvertisement(1);
		assertEquals(a.getApplicationDetails(), "APPLY FOR ME");
	}
	
	@Test
	public void ViewApprovedAdAsCC(){
		s.login("CC","letmein");
		s.publishAdvertisement(1,"bloody good, ol' chap");
		a = s.selectAdvertisement(1);
		assertEquals(a.getApplicationDetails(),"APPLY FOR ME");
	}
	
	@Test
	public void ViewApprovedAdAsOwnerCompany(){
		s.login("CC", "letmein");
		s.publishAdvertisement(1,"bloody good, ol' chap");
		s.login("c","letmein");
		a = s.selectAdvertisement(1);
		assertEquals(a.getApplicationDetails(),"APPLY FOR ME");
	}

}
