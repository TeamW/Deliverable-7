package uk.ac.glasgow.internman.impl.databases;

import uk.ac.glasgow.internman.*;

public interface AdminDutiesInterface {
	
	/* Renamed from addCompany */
	public boolean addEmployer(String employerName, String employerEmail);
	
	/* Renamed from getCompany */
	public Employer getEmployer();
	
	public Student getStudent(String matric);
	
	/* What inputs do we want? */
	public void updateStudent();
}
