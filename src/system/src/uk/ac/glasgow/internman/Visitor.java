package uk.ac.glasgow.internman;

/**
 * A Visitor is an academic member of staff. They conduct a job visit during a
 * students internship to grade their performance.
 * 
 * @author TeamW
 * 
 */
public interface Visitor {

	/**
	 * A visitor is a named individual. This method returns the name.
	 * 
	 * @return String representing Visitor name.
	 */
	String getName();

}
