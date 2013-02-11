package uk.ac.glasgow.internman.impl.admin;

import uk.ac.glasgow.internman.*;
import uk.ac.glasgow.internman.impl.*;
import uk.ac.glasgow.internman.impl.adManager.AdManager;
import uk.ac.glasgow.internman.impl.databases.UserDatabase;

public class Admin implements AdminInterface{
	
	private static UserDatabase UD = UserDatabase.getInstance();
	private static AdManager AD = AdManager.getInstance();
	
	private static final Admin admin = new Admin();
	
	private Admin() {
	}
	
	public static Admin getInstance(){
		return admin;
	}
	
	@Override
	public void publishAdvertisement(int advertisementIndex, String comment){
		AD.publishAdvertisement(advertisementIndex, comment);
	}
	
	@Override
	public boolean registerNewEmployer(String name, String email){
		Employer Emp = new EmployerImpl(name, email , name);
		return UD.addEmployer(Emp);
	}
	
	@Override
	public void approveOffer(String matriculation){
		Student stud = UD.getStudent(matriculation);
		Internship intern = stud.getInternship();
		intern.approve();
	}
	
	@Override
	public void assignAcademicVisitor(String matriculation, String visitorName){
		Student stud = UD.getStudent(matriculation);
		Visit v = new VisitImpl(new VisitorImpl(visitorName),null,null);
		stud.getInternship().setVisit(v);	
	}
	
	@Override
	public Advertisement createNewAdvertisement(Employer e,String applicationDetails){
		return AD.createNewAdvertisement(e,applicationDetails);
	}
	
	@Override
	public Student selectStudent(String matriculation){
		return UD.getStudent(matriculation);
	}
	
	@Override
	public void recordVisitAssessment(String matriculation, UoGGrade grade, String description){
		Student stud = UD.getStudent(matriculation);
		Visit v = stud.getInternship().getVisit();
		if (!(v==null)){
			v.setUoGGrade(grade);
			v.setDescription(description);
		}
	}
	
	@Override
	public Employer getCurrentEmployer(String username){
		return UD.getEmployer(username);
	}
	
	@Override
	public CourseCoordinator getCourseCoordinator(){
		return UD.getCourseCoordinator();
	}

}
