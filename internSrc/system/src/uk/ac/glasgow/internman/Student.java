package uk.ac.glasgow.internman;

import uk.ac.glasgow.internman.Internship;

public interface Student {

	public enum Programme {
		ESE, SE, CS3H, CS3
	}

	Internship getInternship();

	void setInternship(Internship i);

	String getSurname();

	String getMatriculation();

	String getForename();

	String getEmail();

	String getUsername();

	Programme getProgramme();
}
