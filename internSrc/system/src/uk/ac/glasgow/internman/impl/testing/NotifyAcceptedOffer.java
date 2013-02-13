package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.Role;
import uk.ac.glasgow.internman.Student;
import uk.ac.glasgow.internman.Student.Programme;
import uk.ac.glasgow.internman.impl.EmployerImpl;
import uk.ac.glasgow.internman.impl.InternshipImpl;
import uk.ac.glasgow.internman.impl.RoleImpl;
import uk.ac.glasgow.internman.impl.InternManTeamW;
import uk.ac.glasgow.internman.impl.StudentImpl;
import uk.ac.glasgow.internman.impl.databases.UserDatabase;

public class NotifyAcceptedOffer {

	UserDatabase ub;
	InternManTeamW s;
	Student student;
	Employer e;
	Role r;

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
		r = new RoleImpl("TeaMaker", "MyHouse", new Date(), new Date(),
				"awesome job", 123912.12);
		student.setInternship(new InternshipImpl("john",
				"john@someEmployer.com", e, r));
		student.getInternship().accept();
	}

	@After
	public void tearDown() throws Exception {
		s.login("TestCC", "letmein");
		Student temp = s.selectStudent("1002536r");
		temp.getInternship().accept();
	}

	@Test
	public void studentCurrentRole() {
		s.login("1002536r", "letmein");
		s.notifyAcceptedOffer(r, e.getName(), e.getEmailAddress());
		// No crash test
		assertEquals(true, true);
	}

	@Test
	public void employerNotifyFail() {
		s.login("someEmployer", "letmein");
		s.notifyAcceptedOffer(r, e.getName(), e.getEmailAddress());
		// No crash test
		assertEquals(true, true);
	}

	@Test
	public void ccNotifyFail() {
		s.login("TestCC", "letmein");
		s.notifyAcceptedOffer(r, e.getName(), e.getEmailAddress());
		// No crash test
		assertEquals(true, true);
	}

}
