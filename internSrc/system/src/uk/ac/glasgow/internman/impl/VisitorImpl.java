package uk.ac.glasgow.internman.impl;

import uk.ac.glasgow.internman.Visitor;

public class VisitorImpl implements Visitor {

	private String name;

	public VisitorImpl(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
