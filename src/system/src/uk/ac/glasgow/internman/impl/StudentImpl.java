package uk.ac.glasgow.internman.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

import uk.ac.glasgow.internman.Internship;
import uk.ac.glasgow.internman.Student;
import uk.ac.glasgow.internman.users.User;

/**
 * {@link uk.ac.glasgow.internman.Student}
 * 
 * @author TeamW
 * 
 */
public class StudentImpl extends User implements Student, Serializable {

	private static final long serialVersionUID = 1L;
	private String forename;
	private String surname;
	private String email;
	private String matric;
	private ArrayList<Internship> internships;
	private Programme programme;
	private boolean sufficientInternships = false;

	public StudentImpl(String forename, String surname, String email,
			String matric, Programme programme) {
		super(matric, "letmein");
		this.forename = forename;
		this.surname = surname;
		this.email = email;
		this.matric = matric;
		this.internships = new ArrayList<Internship>();
		this.programme = programme;
	}

	@Override
	public ArrayList<Internship> getInternships() {
		if(!internships.isEmpty())
			return internships;
		return null;
	}

	public void setInternship(Internship i) {
		// need to check that internships don't overlap.
		// overlap if ( start1 < end2 and start2 < end1 )
		for( Internship internship : internships){
			if( i.getRole().getStart().before(internship.getRole().getEnd())
				&& internship.getRole().getStart().before(i.getRole().getEnd()))
				return; // overlap, return
		}
		internships.add(i);
		
		// check if student now has sufficient internships
		calcSufficientInternships();
	}
	
	// check if student now has sufficient internships
	private void calcSufficientInternships(){
		Date start = internships.get(0).getRole().getStart();
		Date end = internships.get(0).getRole().getEnd();
		for( Internship internship : internships){
			if( internship.getRole().getStart().before(start))
				start = internship.getRole().getStart();
			if( internship.getRole().getEnd().after(end))
				end = internship.getRole().getEnd();
		}
		
		// get the difference in milliseconds, subtract and convert to days
		final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
		int diffInDays = (int) ((end.getTime() - start.getTime())/ DAY_IN_MILLIS );
		
		// if the number of days in internships is greater than or equal to the desired 
		// time of internships (at the moment 12 weeks) change the flag to true
		if(diffInDays >= 7*12)
			sufficientInternships = true;
	}

	@Override
	public String getSurname() {
		return surname;
	}

	@Override
	public String getMatriculation() {
		return matric;
	}

	@Override
	public String getForename() {
		return forename;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public Programme getProgramme() {
		return programme;
	}
	
	public Boolean getSufficientInternships() {
		return sufficientInternships;
	}

}
