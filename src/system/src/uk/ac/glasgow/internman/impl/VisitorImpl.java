package uk.ac.glasgow.internman.impl;

import uk.ac.glasgow.internman.Visitor;

/**
 * {@link uk.ac.glasgow.internman.Visitor}
 * 
 * @author TeamW
 * 
 */
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
