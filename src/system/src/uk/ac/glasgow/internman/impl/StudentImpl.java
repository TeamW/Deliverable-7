package uk.ac.glasgow.internman.impl;

import java.io.Serializable;
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
		internships.add(i);
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

}
