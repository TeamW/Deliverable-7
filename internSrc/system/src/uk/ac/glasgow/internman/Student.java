package uk.ac.glasgow.internman;

import uk.ac.glasgow.internman.Internship;

public interface Student {

	public enum Programme {
		ESE, SE, CS3H, CS3
	}

	Internship getInternship();

	String getSurname();

	String getMatriculation();

	String getForename();

	String getEmail();

	String getUsername();

	Programme getProgramme();

	boolean authenticate(String password);
}
