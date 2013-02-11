package uk.ac.glasgow.internman.impl;

import java.util.Date;
import java.util.Map;

import uk.ac.glasgow.clui.SystemCommand;
import uk.ac.glasgow.clui.SystemDialogue;
import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.CourseCoordinator;
import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.InternMan;
import uk.ac.glasgow.internman.Role;
import uk.ac.glasgow.internman.Student;
import uk.ac.glasgow.internman.UoGGrade;
import uk.ac.glasgow.internman.users.User;
import uk.ac.glasgow.internman.ui.*;
import uk.ac.glasgow.internman.impl.adManager.AdManager;
import uk.ac.glasgow.internman.impl.admin.Admin;
import uk.ac.glasgow.internman.impl.login.LoginImpl;;


/**
 * Essentially Session(?)
 * 
 * @author 1002536r
 *
 */
public class Session implements InternMan {
	private LoginImpl login;
	private User currentUser;
	private AdManager adManager;
	private Admin admin;
	
	public Session() {
		login = new LoginImpl();
		adManager = new AdManager();
		admin = Admin.getInstance();
	}
	
	public static void main(String[] args){
		InternMan facade = new Session();
		
		SystemDialogue dialogue = new SystemDialogue(System.in, System.out, System.err);

		InternManCLUI userInterface = new InternManCLUI(facade,dialogue);
		addCommands(facade, dialogue, userInterface);
		userInterface.run();
	}
	
	private static void addCommands(InternMan facade, SystemDialogue dialogue, InternManCLUI userInterface)
	{
		SystemCommand<InternMan> showAdvertisementSummary
		=  new ViewAdvertisementSummaryCommand(facade,dialogue);
	
		userInterface.addSystemCommand(
			"ShowAdvertisementSummary", showAdvertisementSummary);

		userInterface.addSystemCommand(
	    "Login", new LoginCommand(facade,dialogue));

		userInterface.addSystemCommand(
	    "RegisterEmployer", new RegisterEmployerCommand(facade,dialogue));
	
	 	userInterface.addSystemCommand(
	 		"SubmitAdvertisement", new SubmitAdvertisementCommand(facade,dialogue));

	 	userInterface.addSystemCommand(
	 		"PublishAdvertisement", new PublishAdvertisementCommand(facade,dialogue));

	
	 	userInterface.addSystemCommand(
	 		"ViewAdvertisementDetail", new ViewAdvertisementDetailCommand(facade,dialogue));

	 	userInterface.addSystemCommand(
	 		"ViewStudentDetail", new ViewStudentDetailCommand(facade,dialogue));
	
	 	userInterface.addSystemCommand(
	 		"AcceptOffer", new AcceptOfferCommand(facade,dialogue));
	
	 	userInterface.addSystemCommand(
	 		"ApproveOffer", new ApproveOfferCommand(facade,dialogue));
	
	 	userInterface.addSystemCommand(
	 		"AssignVisitor", new AssignAcademicVisitorCommand(facade,dialogue));
	
	 	userInterface.addSystemCommand(
	 		"RecordVisitAssessment", new RecordVisitAssessmentCommand(facade,dialogue));
	}

	@Override
	public boolean login(String userName, String password) {
		//if the user is already logged in just return true
		currentUser = login.verifyUser(userName, password);
		if(currentUser == null)
			return false;
		return true;
	}

	@Override
	public User getCurrentUser() {
		//what if user null/not instantiated?
		return currentUser;
	}

	@Override
	public Map<Integer, Advertisement> getAdvertisements() {
		return adManager.getAdvertisements();
	}

	@Override
	public Map<String, Student> getStudents() {
		//no method for this yet?
		if(currentUser instanceof CourseCoordinator)
			return admin.getStudents();
		return null;
	}

	@Override
	public Employer registerNewEmployer(String name, String emailAddress) {
		//DOES NOT CHECK THAT EMPLOYER ALREADY REGISTERED
		if(currentUser instanceof CourseCoordinator || 
		   currentUser instanceof Employer)
			return admin.registerNewEmployer(name, emailAddress);
		return null;
	}

	@Override
	public Advertisement createNewAdvertisement(String applicationDetails) {
		return adManager.createNewAdvertisement((Employer) currentUser, applicationDetails);
	}

	@Override
	public Advertisement selectAdvertisement(Integer index) {
		return adManager.selectAdvertisement(index);
	}

	@Override
	public Role selectRole(Integer advertisementIndex, Integer roleIndex) {
		//no idea
		return null;
	}

	@Override
	public Student selectStudent(String matriculation) {
		return admin.selectStudent(matriculation);
	}

	@Override
	public void publishAdvertisement(Integer advertisementIndex, String comment) {
		adManager.publishAdvertisement(advertisementIndex, comment);
	}

	@Override
	public void notifyAcceptedOffer(Role role, String managerName,
			String managerEmail) {
		//needs to be implemented in one of the interfaces.
	}

	@Override
	public void approveAcceptedOffer(String matriculation) {
		admin.approveOffer(matriculation);
	}

	@Override
	public void assignAcademicVisitor(String matriculation, String visitorName) {
		admin.assignAcademicVisitor(matriculation, visitorName);
	}

	@Override
	public void recordVisitAssessment(String matriculation, UoGGrade grade,
			String description) {
		admin.recordVisitAssessment(matriculation, grade, description);
	}

	@Override
	public Employer getCurrentEmployer() {
		//Method makes no sense, remnant of earlier code? Ignored.
		return null;
	}

	@Override
	public Role createNewSelfSourcedRole(String title, String location,
			Date start, Date end, String description, Double salary) {
		return null;
	}
}
