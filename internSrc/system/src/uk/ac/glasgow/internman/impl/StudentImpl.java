package uk.ac.glasgow.internman.impl;

import uk.ac.glasgow.internman.Internship;
import uk.ac.glasgow.internman.Student;
import uk.ac.glasgow.internman.users.User;

public class StudentImpl extends User implements Student {

	private static final long serialVersionUID = 1L;
	private String forename;
	private String surname;
	private String email;
	private String matric;
	private Internship internship;
	private Programme programme;

	public StudentImpl(String forename, String surname, String email,
			String matric, Programme programme) {
		super(matric, "letmein");
		this.forename = forename;
		this.surname = surname;
		this.email = email;
		this.matric = matric;
		this.internship = new InternshipImpl();
		this.programme = programme;
	}

	@Override
	public Internship getInternship() {
		return internship;
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
