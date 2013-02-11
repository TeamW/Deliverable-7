package uk.ac.glasgow.internman;

public interface Visit {

	Visitor getVisitor();

	UoGGrade getGrade();

	String getDescription();

	void setUoGGrade(UoGGrade grade);

	void setDescription(String description);

}
