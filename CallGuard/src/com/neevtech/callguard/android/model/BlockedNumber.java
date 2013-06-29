package com.neevtech.callguard.android.model;

/**
 * Contains the Blocked Number details.
 * 
 */
public class BlockedNumber {

	private long id;
	private long number;
	private int reportCount;
	private boolean banned;
	private Category category;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the number
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(long number) {
		this.number = number;
	}

	/**
	 * @return the reportCount
	 */
	public int getReportCount() {
		return reportCount;
	}

	/**
	 * @param reportCount
	 *            the reportCount to set
	 */
	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	/**
	 * @return the banned
	 */
	public boolean isBanned() {
		return banned;
	}

	/**
	 * @param banned
	 *            the banned to set
	 */
	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	/**
	 * @param banned
	 *            the banned to set
	 */
	public void setBanned(int banned) {
		this.banned = banned == 1 ? true : false;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

}
