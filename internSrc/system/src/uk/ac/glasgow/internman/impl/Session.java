package uk.ac.glasgow.internman.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import uk.ac.glasgow.clui.*;
import uk.ac.glasgow.internman.*;
import uk.ac.glasgow.internman.users.User;
import uk.ac.glasgow.internman.ui.*;
import uk.ac.glasgow.internman.impl.adManager.AdManager;
import uk.ac.glasgow.internman.impl.admin.Admin;
import uk.ac.glasgow.internman.impl.login.LoginImpl;

;

/**
 * Session
 * 
 * @author 1002536r
 * 
 */
public class Session implements InternMan {
	private LoginImpl login;
	private User currentUser = null;
	private AdManager adManager;
	private Admin admin;

	public Session() {
		login = new LoginImpl();
		adManager = new AdManager();
		admin = Admin.getInstance();
	}

	public static void main(String[] args) {
		InternMan facade = new Session();
		SystemDialogue dialogue = new SystemDialogue(System.in, System.out,
				System.err);
		InternManCLUI userInterface = new InternManCLUI(facade, dialogue);
		addCommands(facade, dialogue, userInterface);
		userInterface.run();
	}

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

	@Override
	public boolean login(String userName, String password) {
		currentUser = login.verifyUser(userName, password);
		if (currentUser == null)
			return false;
		return true;
	}

	@Override
	public User getCurrentUser() {
		return currentUser;
	}

	@Override
	public Map<Integer, Advertisement> getAdvertisements() {
		if (currentUser instanceof CourseCoordinator)
			return adManager.getAdvertisements();
		if (currentUser instanceof Employer)
		{
			//return a new map of only the employers ads.
			//(Gordon you probably wanna look over this,
			//I don't have much experience using hmaps in java)
			Map<Integer, Advertisement> returnMap = new HashMap<Integer, Advertisement>();
			Iterator it = adManager.getAdvertisements().entrySet().iterator();
			while (it.hasNext())
			{
				Map.Entry me = (Map.Entry)it.next();
				Advertisement temp = (Advertisement)me.getValue();
				if (currentUser == temp.getEmployer())
					returnMap.put((Integer)me.getKey(), (Advertisement)me.getValue());
			}
			return returnMap;
		}
		if (currentUser instanceof Student)
		{
			//return a new map of only published ads
			Map<Integer, Advertisement> returnMap = new HashMap<Integer, Advertisement>();
			Iterator it = adManager.getAdvertisements().entrySet().iterator();
			while (it.hasNext())
			{
				Map.Entry me = (Map.Entry)it.next();
				Advertisement temp = (Advertisement)me.getValue();
				if (temp.getStatus() == Advertisement.AdvertisementStatus.PUBLISHED)
					returnMap.put((Integer)me.getKey(), (Advertisement)me.getValue());
			}
			return returnMap;
		}
		return null;
	}

	@Override
	public Map<String, Student> getStudents() {
		if (currentUser instanceof CourseCoordinator)
			return admin.getStudents();
		return null;
	}

	@Override
	public Employer registerNewEmployer(String name, String emailAddress) {
		if (currentUser instanceof CourseCoordinator
				|| currentUser instanceof Employer)
			return admin.registerNewEmployer(name, emailAddress);
		return null;
	}

	@Override
	public Advertisement createNewAdvertisement(String applicationDetails) {
		if (currentUser instanceof CourseCoordinator
				|| currentUser instanceof Employer)
			return adManager.createNewAdvertisement((Employer) currentUser,
					applicationDetails);
		return null;
	}

	@Override
	public Advertisement selectAdvertisement(Integer index) {
		return adManager.selectAdvertisement(index);
	}

	@Override
	public Role selectRole(Integer advertisementIndex, Integer roleIndex) {
		Advertisement temp = adManager.selectAdvertisement(advertisementIndex);
		return temp != null ? temp.getRoles().get(roleIndex) : null;
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
		System.out
				.println("Notified course coordinator of acceptance of role: "
						+ role.getTitle() + " offered by " + managerName + " "
						+ managerEmail);
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
		if (currentUser instanceof Employer) {
			return (Employer) currentUser;
		}
		return null;
	}

	@Override
	public Role createNewSelfSourcedRole(String title, String location,
			Date start, Date end, String description, Double salary) {
		return null;
	}
}
