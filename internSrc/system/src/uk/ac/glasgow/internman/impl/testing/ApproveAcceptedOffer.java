package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.*;
import uk.ac.glasgow.internman.Internship.InternshipStatus;
import uk.ac.glasgow.internman.Student.Programme;
import uk.ac.glasgow.internman.impl.*;
import uk.ac.glasgow.internman.impl.databases.UserDatabase;

public class ApproveAcceptedOffer {

	UserDatabase ub;
	Session s;
	Student student;
	Employer e;
	Role r;

	@Before
	public void setUp() throws Exception {
		s = new Session();
		ub = UserDatabase.getInstance();
		ub.changeCourseCoordinator("TestCC", "letmein");
		student = new StudentImpl("Gordon", "Reid", "example@example.com",
				"1002536r", Programme.SE);
		ub.updateStudent(student);
		e = new EmployerImpl("someEmployer", "example@example.com", "letmein");
		ub.addEmployer(e);
		r = new RoleImpl("TeaMaker", "MyHouse", new Date(), new Date(),
				"awesome job", 123912.12);
		/*
		 * String title, String location, Date start, Date end, String
		 * description, Double salary
		 */
		student.setInternship(new InternshipImpl("john",
				"john@someEmployer.com", e, r));
		student.getInternship().accept();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * User: TestCC with password: letmein is logged in. TestCC then approves an
	 * offer that student 1002536r has accepted. Student is not persistently
	 * updated to allow accurate test reruns.
	 */
	@Test
	public void test() {
		s.login("TestCC", "letmein");
		Student temp = s.selectStudent("1002536r");
		s.approveAcceptedOffer("1002536r");
		InternshipStatus status = temp.getInternship().getStatus();
		assertEquals(status, InternshipStatus.APPROVED);
	}

}
