/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
/**
 * 
 */
package com.newfound.rest.server.constants;

public enum Gender {
	MALE("MALE"), FEMALE("FEMALE");
	private String type;

	/**
	 * @param type
	 */
	private Gender(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
