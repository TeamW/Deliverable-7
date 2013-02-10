package uk.ac.glasgow.internman.impl;

import java.util.Date;
import java.util.Map;

import uk.ac.glasgow.clui.SystemDialogue;
import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.InternMan;
import uk.ac.glasgow.internman.Role;
import uk.ac.glasgow.internman.Student;
import uk.ac.glasgow.internman.UoGGrade;
import uk.ac.glasgow.internman.users.User;
import uk.ac.glasgow.internman.ui.InternManCLUI;
import uk.ac.glasgow.internman.impl.adManager.AdManager;
import uk.ac.glasgow.internman.impl.databases.UserDatabase;
import uk.ac.glasgow.internman.impl.login.LoginImpl;;


/**
 * Essentially Session(?)
 * 
 * @author 1002536r
 *
 */
public class Session implements InternMan {
	
	private float timeLoggedIn = 0;
	private float timeLogOut = 60 * 30; //logout after 30 mins
	private LoginImpl login;
	private User currentUser;
	private AdManager adManager;
	
	public Session() {
		login = new LoginImpl();
		adManager = new AdManager();
	}
	
	public static void main(String[] args){
		InternMan facade = new Session();
		
		SystemDialogue dialogue = new SystemDialogue(System.in, System.out, System.err);

		InternManCLUI userInterface = new InternManCLUI(facade,dialogue);
		userInterface.run();
	}

	@Override
	public boolean login(String userName, String password) {
		if(!login.userLoggedIn(userName))
			if(login.verifyUser(userName, password))
			{
				currentUser = new User(userName, password);
				return true;
			}
			else
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
		return null;
	}

	@Override
	public Employer registerNewEmployer(String name, String emailAddress) {
		//userDatabase implements the right interface but has a private constructor?
		return null;
	}

	@Override
	public Advertisement createNewAdvertisement(String applicationDetails) {
		return adManager.createNewAdvertisement(applicationDetails);
	}

	@Override
	public Advertisement selectAdvertisement(Integer index) {
		return adManager.selectAdvertisement(index);
	}

	@Override
	public Role selectRole(Integer advertisementIndex, Integer roleIndex) {
		return null;
	}

	@Override
	public Student selectStudent(String matriculation) {
		return null;
	}

	@Override
	public void publishAdvertisement(Integer advertisementIndex, String comment) {
	}

	@Override
	public void notifyAcceptedOffer(Role role, String managerName,
			String managerEmail) {
	}

	@Override
	public void approveAcceptedOffer(String matriculation) {
	}

	@Override
	public void assignAcademicVisitor(String matriculation, String visitorName) {
	}

	@Override
	public void recordVisitAssessment(String matriculation, UoGGrade grade,
			String description) {
	}

	@Override
	public Employer getCurrentEmployer() {
		return null;
	}

	@Override
	public Role createNewSelfSourcedRole(String title, String location,
			Date start, Date end, String description, Double salary) {
		return null;
	}
}
