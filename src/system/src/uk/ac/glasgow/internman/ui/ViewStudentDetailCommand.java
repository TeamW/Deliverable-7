package uk.ac.glasgow.internman.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import uk.ac.glasgow.clui.ArgumentsDescription;
import uk.ac.glasgow.clui.CommandDescription;
import uk.ac.glasgow.clui.MinimumArguments;
import uk.ac.glasgow.clui.SystemCommand;
import uk.ac.glasgow.clui.SystemCommandException;
import uk.ac.glasgow.clui.SystemDialogue;
import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.InternMan;
import uk.ac.glasgow.internman.Internship;
import uk.ac.glasgow.internman.Role;
import uk.ac.glasgow.internman.Student;
import uk.ac.glasgow.internman.UoGGrade;
import uk.ac.glasgow.internman.Visit;
import uk.ac.glasgow.internman.Visitor;

@MinimumArguments(1)
@CommandDescription("Shows the detailed view of a SESP student in the system.")
@ArgumentsDescription("<matriculation>")
public class ViewStudentDetailCommand extends SystemCommand<InternMan> {

	public ViewStudentDetailCommand(InternMan facade, SystemDialogue dialogue) {
		super(facade, dialogue);
	}
	
	private String formatDate(Date date){
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		return df.format(date);
	}

	@Override
	public void processCommand(String... args) throws SystemCommandException {
		String matriculation = args[0];
		
		String result = "";
		
		Student student = facade.selectStudent(matriculation);
		
		if (student != null){
			result += 
			"Matriculation:\t"+student.getMatriculation()+"\n"+
			"Name:\t" + student.getSurname()+", "+student.getForename()+"\n"+
			"Programme:\t" + student.getProgramme()+"\n"+
			"Sufficient Internships:\t" + student.getEnoughInternships()+"\n";
			
			Map<Integer,Internship> internships = student.getInternships();
			
			for (Entry<Integer,Internship> internship: internships.entrySet()){
				if (internship != null){
					result+= 
						"Status:\t"+internship.getValue().getStatus()+"\n";
				}
			
				Role role = null;
				if (internship.getValue().getStatus() == Internship.InternshipStatus.APPROVED)
					role = internship.getValue().getRole();
			
				if (role != null)
					result +=
						"Role\t:"+role.getTitle()+"\n"+
						"Begin:\t"   + formatDate(role.getStart())+"\n"+
						"End:\t"     + formatDate(role.getEnd())+"\n"+
						"Salary:\t"  + role.getSalary()+"\n"+
						"Location\t:" +role.getLocation()+"\n";
				
				Employer employer = internship.getValue().getEmployer();
				
				if (role != null && employer != null)
					result += 
						"Employer:\t"+employer.getName()+"\n"+
						"Contact Details:\t"+employer.getEmailAddress()+"\n"+
						"Manager:\t"+employer.getName()+"\n";
				
				Visit visit = internship.getValue().getVisit();
				
				if (role != null && visit != null){
					Visitor visitor = visit.getVisitor();
					
					if (visitor != null)
						result+= "Visitor:\t"+visitor.getName()+"\n";
						
					UoGGrade grade = visit.getGrade();
					if (grade != null){
						result += "Grade:\t" + grade + "\n";
						result += "Description:\t" + visit.getDescription() + "\n";
					}			
				}
			}
			
		}
		dialogue.sendMessage(result);
	}
}
