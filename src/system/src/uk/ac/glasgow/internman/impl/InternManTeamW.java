package uk.ac.glasgow.internman.impl;

import java.util.*;
import java.util.Map.Entry;

import uk.ac.glasgow.clui.*;
import uk.ac.glasgow.internman.*;
import uk.ac.glasgow.internman.users.User;
import uk.ac.glasgow.internman.ui.*;
import uk.ac.glasgow.internman.impl.adManager.AdManager;
import uk.ac.glasgow.internman.impl.admin.Admin;
import uk.ac.glasgow.internman.impl.login.LoginImpl;

/**
 * {@link uk.ac.glasgow.internman.InternMan}
 * 
 * This is our implementation of the intern management system facade.
 * All commands are run through the UI and directed here. 
 * 
 * @author TeamW
 * 
 */
public class InternManTeamW implements InternMan {
	private LoginImpl login;
	private User currentUser = null;
	private AdManager adManager;
	private Admin admin;

	/**
	 * Create an instance of the class with appropriate component objects.
	 */
	public InternManTeamW() {
		login = new LoginImpl();
		adManager = AdManager.getInstance();
		admin = Admin.getInstance();
	}

	/**
	 * Main method for the internship management system. Creates and spins off
	 * a thread for the UI.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		InternMan facade = new InternManTeamW();
		SystemDialogue dialogue = new SystemDialogue(System.in, System.out,
				System.err);
		InternManCLUI userInterface = new InternManCLUI(facade, dialogue);
		addCommands(facade, dialogue, userInterface);
		userInterface.run();
	}

	/**
	 * Add individual commands to the UI, linking it to the facade implementation.
	 * 
	 * @param facade
	 * 				Intern management system facade.
	 * @param dialogue
	 * 				Collection of system input, output and error streams.
	 * @param userInterface
	 * 				User Interface to add the commands to.
	 */
	private static void addCommands(InternMan facade, SystemDialogue dialogue,
			InternManCLUI userInterface) {
		SystemCommand<InternMan> showAdvertisementSummary = new ViewAdvertisementSummaryCommand(
				facade, dialogue);

		userInterface.addSystemCommand("ShowAdvertisementSummary",
				showAdvertisementSummary);

		userInterface.addSystemCommand("Login", new LoginCommand(facade,
				dialogue));

		userInterface.addSystemCommand("RegisterEmployer",
				new RegisterEmployerCommand(facade, dialogue));

		userInterface.addSystemCommand("SubmitAdvertisement",
				new SubmitAdvertisementCommand(facade, dialogue));

		userInterface.addSystemCommand("PublishAdvertisement",
				new PublishAdvertisementCommand(facade, dialogue));

		userInterface.addSystemCommand("ViewAdvertisementDetail",
				new ViewAdvertisementDetailCommand(facade, dialogue));

		userInterface.addSystemCommand("ViewStudentDetail",
				new ViewStudentDetailCommand(facade, dialogue));

		userInterface.addSystemCommand("AcceptOffer", new AcceptOfferCommand(
				facade, dialogue));

		userInterface.addSystemCommand("ApproveOffer", new ApproveOfferCommand(
				facade, dialogue));

		userInterface.addSystemCommand("AssignVisitor",
				new AssignAcademicVisitorCommand(facade, dialogue));

		userInterface.addSystemCommand("RecordVisitAssessment",
				new RecordVisitAssessmentCommand(facade, dialogue));
	}

	/**
	 * Login to the system as a Student, Course Coordinator or Employer
	 * 
	 * @param userName
	 * 				Name of user.
	 * @param password
	 * 				User password.
	 */
	@Override
	public boolean login(String userName, String password) {
		currentUser = login.verifyUser(userName, password);
		if (currentUser == null)
			return false;
		return true;
	}

	/**
	 * Get the current system user.
	 * 
	 * @return Current user object. If no user logged in return null.
	 */
	@Override
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Get a map of advertisements appropriate to the user access level. If
	 * user is a Course Coordinator this is all the adverts, published or unpublished.
	 * If user is an Employer get only advertisements submitted by this Employer.
	 * If user is a Student the map will contain only published advertisements.
	 * 
	 * @return A map of advertisements.
	 */
	@Override
	public Map<Integer, Advertisement> getAdvertisements() {
		if (currentUser instanceof CourseCoordinator) {
			return adManager.getAdvertisements();
		}
		if (currentUser instanceof Employer) {
			Map<Integer, Advertisement> returnMap = new HashMap<Integer, Advertisement>();
			for (Entry<Integer, Advertisement> a : returnMap.entrySet()) {
				if (currentUser == a.getValue().getEmployer()) {
					returnMap.put((Integer) a.getKey(),
							(Advertisement) a.getValue());
				}
			}
			return returnMap;
		}
		if (currentUser instanceof Student) {
			Map<Integer, Advertisement> returnMap = new HashMap<Integer, Advertisement>();
			for (Entry<Integer, Advertisement> a : returnMap.entrySet()) {
				if (a.getValue().getStatus() == Advertisement.AdvertisementStatus.PUBLISHED) {
					returnMap.put((Integer) a.getKey(),
							(Advertisement) a.getValue());
				}
			}
			return returnMap;
		}
		return null;
	}

	/**
	 * If current User is Course Coordinator get a map of all Students stored in the 
	 * system, otherwise return null.
	 * 
	 * @return Map of Students in the system, or null.
	 */
	@Override
	public Map<String, Student> getStudents() {
		if (currentUser instanceof CourseCoordinator)
			return admin.getStudents();
		return null;
	}

	/**
	 * Register a new employer in the system.
	 * 
	 * @param name
	 * 			Name of employer.
	 * @param emailAddress
	 * 			Email address of employer.
	 * 
	 * @return New Employer object that was created.
	 */
	@Override
	public Employer registerNewEmployer(String name, String emailAddress) {
		if (currentUser.getUsername().equals(admin.getCourseCoordinator().getUsername())){
			return admin.registerNewEmployer(name, emailAddress);
		}
		return null;
	}

	/**
	 * Add a new advertisement into the system.
	 * 
	 * @param applicationDetails
	 * 			Details of the advertisement.
	 * 
	 * @return The new Advertisement object.
	 */
	@Override
	public Advertisement createNewAdvertisement(String applicationDetails) {
		if (currentUser instanceof CourseCoordinator)
			return adManager.createNewAdvertisement(null, applicationDetails);
		else if (currentUser instanceof Employer)
			return adManager.createNewAdvertisement((Employer) currentUser,
					applicationDetails);
		return null;
	}

	/**
	 * Selects a specific advertisement from the system, based on user access
	 * level. Course Coordinators can see any advert, Employers can only get 
	 * advertisements they submitted and Students can only get published
	 * advertisements.
	 * 
	 * @param index
	 * 			Integer key to identify advertisement.
	 * 
	 * @return The desired Advertisement, or null if Advertisement doesn't exit
	 * 		   or user has wrong permissions.
	 */
	@Override
	public Advertisement selectAdvertisement(Integer index) {
		Advertisement ad = adManager.selectAdvertisement(index);
		if (ad != null) {
			if (currentUser instanceof CourseCoordinator)
				return ad;
			if (currentUser instanceof Student
					&& ad.getStatus() == Advertisement.AdvertisementStatus.PUBLISHED)
				return ad;
			else if (currentUser instanceof Employer
					&& (currentUser.getUsername().equals(ad.getEmployer()
							.getUsername())))
				return ad;
		}
		return null;
	}

	/**
	 * Gets a role from the system.
	 * 
	 * @param advertisementIndex
	 * 			Index of desired advertisement.
	 * @param roleIndex
	 * 			Index of role.
	 */
	@Override
	public Role selectRole(Integer advertisementIndex, Integer roleIndex) {
		Advertisement ad = adManager.selectAdvertisement(advertisementIndex);
		if (ad != null) {
			if (currentUser instanceof CourseCoordinator)
				return ad.getRoles().get(roleIndex);
			if (currentUser instanceof Student
					&& ad.getStatus() == Advertisement.AdvertisementStatus.PUBLISHED)
				return ad.getRoles().get(roleIndex);
			else if (currentUser instanceof Employer
					&& currentUser == ad.getEmployer())
				return ad.getRoles().get(roleIndex);
		}
		return null;
	}

	/**
	 * Get a specific Student from the system. Only usable by Course Coordinator.
	 * 
	 * @param matriculation
	 * 			Matriculation number of desired Student as a String.
	 * 
	 * @return Student object, or null if current user not a Course Coordinator.
	 */
	@Override
	public Student selectStudent(String matriculation) {
		if (currentUser instanceof CourseCoordinator)
			return admin.selectStudent(matriculation);
		return null;
	}

	/**
	 * Allows Course Coordinator to set a specific Advertisement's status to published.
	 * 
	 * @param advertisementIndex
	 * 			Integer identifier of the advertisement.
	 * @param comment
	 * 			Any comments the coordinator wishes to make.
	 * 
	 */
	@Override
	public void publishAdvertisement(Integer advertisementIndex, String comment) {
		if (currentUser instanceof CourseCoordinator)
			admin.publishAdvertisement(advertisementIndex, comment);
	}

	/**
	 * Notify Course Coordinator if a role has been accepted.
	 * 
	 * @param role
	 * 			Role user was accepted in.
	 * @param managerName
	 * 			Manager offering role.
	 * @param managerEmail
	 * 			Email of manager
	 */
	@Override
	public void notifyAcceptedOffer(Role role, String managerName,
			String managerEmail) {
		if (currentUser instanceof Student)
			System.out
					.println("Notified course coordinator of acceptance of role: "
							+ role.getTitle()
							+ " offered by "
							+ managerName
							+ " " + managerEmail);
	}

	/**
	 * Allows the Course Coordinator to approve an offer most
	 * recently accepted by the student.
	 * 
	 * @param matriculation
	 * 			Matriculation number of Student as a String.
	 */
	@Override
	public void approveAcceptedOffer(String matriculation) {
		if (currentUser instanceof CourseCoordinator){
			admin.approveOffer(matriculation, 
					admin.selectStudent(matriculation).getMaxInternshipId());
		}
	}

	/**
	 * Allows Course Coordinator to assign an academic visitor.
	 * 
	 * @param matriculation
	 * 			Matriculation number of Student as a String.
	 * @param visitorName
	 * 			Name of visitor.
	 */
	@Override
	public void assignAcademicVisitor(String matriculation, String visitorName) {
		if (currentUser instanceof CourseCoordinator)
			admin.assignAcademicVisitor(matriculation, visitorName);
	}

	/**
	 * Allows Course Coordinator to record the visit assessment.
	 * 
	 * @param matriculation
	 * 			Matriculation number of Student as a String.
	 * @param grade
	 * 			Grade given.
	 * @param description
	 * 			Description of assessment.
	 */
	@Override
	public void recordVisitAssessment(String matriculation, UoGGrade grade,
			String description) {
		if (currentUser instanceof CourseCoordinator)
			admin.recordVisitAssessment(matriculation, grade, description);
	}

	/**
	 * Get the current Employer.
	 * 
	 * @return Employer if current user is an Employer, null otherwise.
	 */
	@Override
	public Employer getCurrentEmployer() {
		if (currentUser instanceof Employer) {
			return (Employer) currentUser;
		}
		return null;
	}

	/**
	 * Allows Student to submit a role they found themselves.
	 * 
	 * @param title
	 * 			Title of role.
	 * @param location
	 * 			Location of role.
	 * @param start
	 * 			Starting date.
	 * @param end
	 * 			Ending date.
	 * @param description
	 * 			Description of role.
	 * @param salary
	 * 			Salary offered by role.
	 * 
	 * @return The new Role created in the system, null if user isn't a Student.
	 */
	@Override
	public Role createNewSelfSourcedRole(String title, String location,
			Date start, Date end, String description, Double salary) {
		if (currentUser instanceof Student) {
			Student student = (Student) currentUser;
			Role temp = new RoleImpl(title, location, start, end, description,
					salary);
			student.getInternships().get(0).setRole(temp);
			return temp;
		}
		return null;
	}
}
