package uk.ac.glasgow.internman;

public interface Employer {

	String getName();

	String getEmailAddress();
	
	String getUsername();

	boolean authenticate(String password);

}
