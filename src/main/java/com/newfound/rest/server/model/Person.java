/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package com.newfound.rest.server.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.newfound.rest.server.constants.Gender;

@XmlRootElement(name = "person")
@XmlType(propOrder = { "id", "firstName", "lastName", "emailId", "gender", "salary" })
public class Person implements Serializable {

	private static final long serialVersionUID = -2294968021837897119L;

	private Integer id = -1;
	private String firstName = "";
	private String lastName = "";
	private String emailId = "";
	private Gender gender;
	private Integer salary;

	/**
	 * Default Constructor
	 */
	public Person() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param emailId
	 * @param gender
	 * @param salary
	 */
	public Person(Integer id, String firstName, String lastName, String emailId, Gender gender, Integer salary) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.gender = gender;
		this.salary = salary;
	}

	/**
	 * @return the id
	 */
	@XmlElement
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	@XmlElement
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	@XmlElement
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailId
	 */
	@XmlElement
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the gender
	 */
	@XmlElement
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the salary
	 */
	@XmlElement
	public Integer getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", gender=" + gender + ", salary=" + salary + "]";
	}

}
