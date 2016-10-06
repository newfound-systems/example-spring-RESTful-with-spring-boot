/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package com.newfound.rest.client;

public interface RestClientConstants {
	public final static String REST_SERVICE_URL = "http://localhost:8080/person";

	public final static String FIND_ALL = "/find/all";
	public final static String FIND_BY_ID = "/find/id/1";
	public final static String FIND_BY_FIRSTNAME = "/find/firstName/John";
	public final static String FIND_BY_GENDER_FEMALE = "/find/gender/FEMALE";
	public final static String FIND_BY_GENDER_MALE = "/find/gender/MALE";

	public final static String CREATE_AUTO = "/autoCreate";
	public final static String CREATE = "/manualCreate";
	public final static String DELETE = "/delete/1";

	public final static String USER = "admin";
	public final static String PASSWORD = "admin";

	public static final int URL_TIMEOUT = 5000;

	public enum ResponseFormat {
		XML("xml"), JSON("json");
		private String format;

		private ResponseFormat(String format) {
			this.format = format;
		}

		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}
	}

	public enum RequestMethod {
		GET, POST;
	}
}
