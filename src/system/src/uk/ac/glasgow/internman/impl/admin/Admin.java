package uk.ac.glasgow.internman.impl.admin;

import java.util.Map;
import java.util.Map.Entry;

import uk.ac.glasgow.internman.*;
import uk.ac.glasgow.internman.impl.*;
import uk.ac.glasgow.internman.impl.adManager.AdManager;
import uk.ac.glasgow.internman.impl.databases.UserDatabase;

public class Admin implements AdminInterface {

	private static UserDatabase UD = UserDatabase.getInstance();
	private static AdManager AD = AdManager.getInstance();

	private static final Admin admin = new Admin();

	private Admin() {
	}

	public static Admin getInstance() {
		return admin;
	}

	@Override
	public void publishAdvertisement(int advertisementIndex, String comment) {
		Advertisement ad = AD.selectAdvertisement(advertisementIndex);
		if(ad!=null)
			AD.publishAdvertisement(advertisementIndex, comment);
	}

	@Override
	public Employer registerNewEmployer(String name, String email) {
		Employer temp = UD.getEmployer(name);
		if(temp!=null){
			return temp;
		}
		Employer Emp = new EmployerImpl(name, email, "letmein");
		return UD.addEmployer(Emp) ? Emp : null;
	}

	@Override
	public void approveOffer(String matriculation, int id) {
		Student stud = UD.getStudent(matriculation);
		if (stud == null) {
			return;
		}
		Internship intern = stud.getInternships().get(id);
		intern.approve();
	}

	@Override
	public void assignAcademicVisitor(String matriculation, String visitorName) {
		Student stud = UD.getStudent(matriculation);
		Visit v = new VisitImpl(new VisitorImpl(visitorName), null, null);
		for( Entry<Integer, Internship> internship : stud.getInternships().entrySet()){
			internship.getValue().setVisit(v);
		}
	}

	@Override
	public Advertisement createNewAdvertisement(Employer e,
			String applicationDetails) {
		return AD.createNewAdvertisement(e, applicationDetails);
	}

	@Override
	public Student selectStudent(String matriculation) {
		return UD.getStudent(matriculation);
	}

	@Override
	public void recordVisitAssessment(String matriculation, UoGGrade grade,
			String description, int id) {
		Student stud = UD.getStudent(matriculation);
		Visit v = stud.getInternships().get(id).getVisit();
		if (!(v == null)) {
			v.setUoGGrade(grade);
			v.setDescription(description);
		}
	}

	@Override
	public Employer getCurrentEmployer(String username) {
		return UD.getEmployer(username);
	}

	@Override
	public CourseCoordinator getCourseCoordinator() {
		return UD.getCourseCoordinator();
	}

	@Override
	public Map<String, Student> getStudents() {
		return UD.getStudents();
	}

}
