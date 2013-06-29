package com.neevtech.callguard.android.model;

/**
 * Contains the personal group details.
 */
public class PersonalGroup {

	private long id;
	private long number;
	private Group group;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
