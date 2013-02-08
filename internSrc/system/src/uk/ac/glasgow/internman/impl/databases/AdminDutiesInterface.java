package uk.ac.glasgow.internman.impl.databases;

import uk.ac.glasgow.internman.*;

public interface AdminDutiesInterface {

	/* Renamed from addCompany */
	public boolean addEmployer(String employerName, String employerEmail, String password);

	/* Renamed from getCompany */
	public Employer getEmployer(String employerName);

	public Student getStudent(String guid);

	public void updateStudent(Student student);
}
