package uk.ac.glasgow.internman.impl;

import uk.ac.glasgow.internman.UoGGrade;
import uk.ac.glasgow.internman.Visit;
import uk.ac.glasgow.internman.Visitor;

public class VisitImpl implements Visit {

	private Visitor visitor;
	private UoGGrade grade;
	private String description;

	public VisitImpl(Visitor visitor, UoGGrade grade, String description) {
		this.visitor = visitor;
		this.grade = grade;
		this.description = description;
	}

	@Override
	public Visitor getVisitor() {
		return visitor;
	}

	@Override
	public UoGGrade getGrade() {
		return grade;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
