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
import uk.ac.glasgow.internman.impl.Session;
import uk.ac.glasgow.internman.impl.StudentImpl;
import uk.ac.glasgow.internman.impl.databases.UserDatabase;

public class ViewStudentDetail {

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
		student.setInternship(new InternshipImpl("john",
				"john@someEmployer.com", e, r));
		student.getInternship().accept();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Checks that the Course Coordinator can view an existing students details.
	 */
	@Test
	public void ViewStudentDetailAsCC() {
		s.login("TestCC", "letmein");
		Student stud = s.selectStudent("1002536r");
		assertEquals(stud.getEmail() + stud.getForename() + stud.getSurname()
				+ stud.getUsername() + stud.getMatriculation(),
				"example@example.comGordonReid1002536r1002536r");
	}

	/**
	 * Test for checking that a Course Coordinator cannont view a student that
	 * does not exist
	 */
	@Test
	public void ViewNonExistentStudentDetailAsCC() {
		s.login("TestCC", "letmein");
		Student stud = s.selectStudent("1002253w");
		assertEquals(stud, null);
	}

	/**
	 * Checks that a company cannot view a student.
	 */
	@Test
	public void ViewStudentDetailAsEmployer() {
		s.login("someEmployer", "letmein");
		Student stud = s.selectStudent("1002536r");
		assertEquals(stud, null);
	}

}
