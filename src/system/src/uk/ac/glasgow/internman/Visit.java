package uk.ac.glasgow.internman;

/**
 * Each Software-Engineering student has to have an internship. Their
 * performance is assessed by a visitor going to their place of work. This
 * records the visit and associated grade.
 * 
 * @author TeamW
 * 
 */
public interface Visit {

	/**
	 * This method returns the visitor assigned to conduct the visit.
	 * 
	 * @return Visitor object representing who will take the visit, otherwise
	 *         null.
	 */
	Visitor getVisitor();

	/**
	 * Return the grade given to the student based on their internship
	 * performance.
	 * 
	 * @return A grade if visit completed, otherwise null.
	 */
	UoGGrade getGrade();

	/**
	 * An addition to a grade, the visitor can also write comments supporting
	 * the given grade.
	 * 
	 * @return String representing visitor's thoughts on student's performance.
	 */
	String getDescription();

	/**
	 * After a visit has been completed, the visitor assigns a grade.
	 * 
	 * @param grade
	 *            The grade assigned.
	 */
	void setUoGGrade(UoGGrade grade);

	/**
	 * After a visit has been completed, the visitor can give a description
	 * supporting the given grade.
	 * 
	 * @param description
	 *            String representing supporting reason for grade.
	 */
	void setDescription(String description);

}
