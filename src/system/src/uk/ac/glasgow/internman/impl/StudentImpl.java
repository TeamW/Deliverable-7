package uk.ac.glasgow.internman.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
	private HashMap<Integer, Internship> internships;
	private Programme programme;
	private boolean sufficientInternships = false;
	private Integer maxInternshipId = 0;

	public StudentImpl(String forename, String surname, String email,
			String matric, Programme programme) {
		super(matric, "letmein");
		this.forename = forename;
		this.surname = surname;
		this.email = email;
		this.matric = matric;
		this.internships = new HashMap<Integer, Internship>();
		this.programme = programme;
	}

	@Override
	public HashMap<Integer, Internship> getInternships() {
		if(!internships.isEmpty())
			return internships;
		return null;
	}

	public void setInternship(Internship i) {
		// need to check that internships don't overlap.
		// overlap if ( start1 < end2 and start2 < end1 )
		for( Entry<Integer, Internship> internship : internships.entrySet()){
			if( i.getRole().getStart().before(internship.getValue().getRole().getEnd())
				&& internship.getValue().getRole().getStart().before(i.getRole().getEnd()))
				return; // overlap, return
		}
		maxInternshipId += 1;
		internships.put(maxInternshipId, i);
		
		// check if student now has sufficient internships
		calcSufficientInternships();
	}
	
	// check if student now has sufficient internships
	private void calcSufficientInternships(){
		Date start = internships.get(0).getRole().getStart();
		Date end = internships.get(0).getRole().getEnd();
		for( Entry<Integer, Internship> internship : internships.entrySet()){
			if( internship.getValue().getRole().getStart().before(start))
				start = internship.getValue().getRole().getStart();
			if( internship.getValue().getRole().getEnd().after(end))
				end = internship.getValue().getRole().getEnd();
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
	
	public Integer getMaxInternshipId()
	{
		return maxInternshipId;
	}

}
