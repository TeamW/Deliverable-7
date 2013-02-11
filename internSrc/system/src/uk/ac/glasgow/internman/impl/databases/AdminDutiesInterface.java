package uk.ac.glasgow.internman.impl.databases;

import uk.ac.glasgow.internman.*;

public interface AdminDutiesInterface {

	/* Renamed from addCompany */
	public boolean addEmployer(Employer e);

	/* Renamed from getCompany */
	public Employer getEmployer(String employerName);

	public Student getStudent(String guid);
	
	public CourseCoordinator getCourseCoordinator();

	public void updateStudent(Student student);
	
	public void changeCourseCoordinator(String username, String password);

}
