package uk.ac.glasgow.internman.impl.admin;

import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.Student;
import uk.ac.glasgow.internman.UoGGrade;

public class Admin implements AdminInterface{
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void publishAdvertisement(int advertisementIndex, String comment){
		
	}
	
	@Override
	public boolean registerNewEmployer(String name, String email){
		return false;
	}
	
	@Override
	public void approveOffer(String matriculation){
		
	}
	
	@Override
	public void assignAcademicVisitor(String matriculation, String visitorName){
		
	}
	
	@Override
	public Advertisement createNewAdvertisement(String applicationDetails){
		return null;
	}
	
	@Override
	public Student selectStudent(String matriculation){
		return null;
	}
	
	@Override
	public void recordVisitAssessment(String matriculation, UoGGrade grade, String description){
		
	}

}
