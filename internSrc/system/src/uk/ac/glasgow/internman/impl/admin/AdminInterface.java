package uk.ac.glasgow.internman.impl.admin;

import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.Student;
import uk.ac.glasgow.internman.UoGGrade;

/**
 * Called "Blank" in current System Architecture interface. Will be changed to
 * generic "Admin Interface"
 * 
 * @author 1002536r
 * 
 */
public interface AdminInterface {
	
	void publishAdvertisement(int advertisementIndex, String comment);
	
	boolean registerNewEmployer(String name, String email);
	
	void approveOffer(String matriculation);
	
	void assignAcademicVisitor(String matriculation, String visitorName);
	
	Advertisement createNewAdvertisement(String applicationDetails);
	
	Student selectStudent(String matriculation);
	
	void recordVisitAssessment(String matriculation, UoGGrade grade, String description);
	

}
