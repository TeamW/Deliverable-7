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
 * @author TeamW
 * 
 */
public class InternManTeamW implements InternMan {
	private LoginImpl login;
	private User currentUser = null;
	private AdManager adManager;
	private Admin admin;

	public InternManTeamW() {
		login = new LoginImpl();
		adManager = AdManager.getInstance();
		admin = Admin.getInstance();
	}

	public static void main(String[] args) {
		InternMan facade = new InternManTeamW();
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
		if (currentUser instanceof CourseCoordinator)
			return adManager.createNewAdvertisement(null, applicationDetails);
		else if (currentUser instanceof Employer)
			return adManager.createNewAdvertisement((Employer) currentUser,
					applicationDetails);
		return null;
	}

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
					&& currentUser == ad.getEmployer())
				return ad;
		}
		return null;
	}

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

	@Override
	public Student selectStudent(String matriculation) {
		if (currentUser instanceof CourseCoordinator)
			return admin.selectStudent(matriculation);
		return null;
	}

	@Override
	public void publishAdvertisement(Integer advertisementIndex, String comment) {
		if (currentUser instanceof CourseCoordinator)
			adManager.publishAdvertisement(advertisementIndex, comment);
	}

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

	@Override
	public void approveAcceptedOffer(String matriculation) {
		if (currentUser instanceof CourseCoordinator)
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
		if (currentUser instanceof Student) {
			Student student = (Student) currentUser;
			Role temp = new RoleImpl(title, location, start, end, description,
					salary);
			student.getInternship().setRole(temp);
			return temp;
		}
		return null;
	}
}
