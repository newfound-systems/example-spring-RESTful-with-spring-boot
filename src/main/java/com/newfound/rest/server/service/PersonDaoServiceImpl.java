/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package com.newfound.rest.server.service;

import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.newfound.rest.server.constants.Gender;
import com.newfound.rest.server.model.Person;
import com.newfound.rest.server.model.PersonList;

@Service("personDaoServiceImpl")
public class PersonDaoServiceImpl implements PersonDaoService {

	private static final Logger log = LoggerFactory.getLogger(PersonDaoServiceImpl.class);

	private static final String DEFAULT_FNAME = "Chetan";
	private static final String DEFAULT_LNAME = "Anand";
	private static final String DEFAULT_EMAIL = "support@newfound-systems.com";

	public static final String MESSAGE_SUCCESS = "SUCCESS";
	public static final String MESSAGE_FAILURE = "FAILURE";
	public static final String MESSAGE_CREATED = "CREATED";
	public static final String MESSAGE_DELETED = "DELETED";
	public static final String MESSAGE_UPDATED = "UPDATED";

	private static final int STATUS_ERROR = -1;
	private static final int STATUS_SUCCESS = 0;

	private Person UNKNOWN = new Person();
	private PersonList personList;

	/**
	 * Constructor
	 */
	public PersonDaoServiceImpl() {
		super();
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hello.PersonRepo#addPersons()
	 */
	public void init() {

		List<Person> persons = Lists.newArrayList();
		personList = new PersonList();

		persons.add(new Person(1, "John", "Doe", "john.doe@somewhere.com", Gender.MALE, 1000));
		persons.add(new Person(2, "Michelle", "Turner", "michelle.turner@somewhere.com", Gender.FEMALE, 2000));
		persons.add(new Person(3, "Tom", "Hanks", "tom.hanks@somewhere.com", Gender.MALE, 3000));
		persons.add(new Person(4, "Ray", "Ramano", "ray.ramano@somewhere.com", Gender.MALE, 4000));
		persons.add(new Person(5, "John", "Travolta", "john.travolta@somewhere.com", Gender.MALE, 5000));
		persons.add(new Person(6, "Will", "Smith", "will.smith@somewhere.com", Gender.MALE, 6000));
		persons.add(new Person(7, "Jerry", "Seinfeld", "jerry.seinfeld@somewhere.com", Gender.MALE, 7000));
		persons.add(new Person(8, "Elene", "Benis", "elene.benis@somewhere.com", Gender.FEMALE, 8000));

		personList.setCode(STATUS_SUCCESS);
		personList.setMessage(MESSAGE_SUCCESS);
		personList.setPersons(persons);

		log.info("Initial create person(s), size: " + persons.size());
	}

	/**
	 * @param filter
	 * @return
	 */
	private PersonList sendResponse(Predicate<Person> filter) {
		PersonList persons = new PersonList();
		List<Person> result = FluentIterable.from(Iterables.filter(personList.getPersons(), filter)).toList();
		if (result == null || result.size() == 0) {
			persons.setCode(STATUS_ERROR);
			persons.setMessage(MESSAGE_FAILURE);
			persons.setPersons(Lists.newArrayList(UNKNOWN));
		} else {
			persons.setCode(STATUS_SUCCESS);
			persons.setMessage(MESSAGE_SUCCESS);
			persons.setPersons(result);
		}
		return persons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.newfound.rest.server.service.PersonDaoService#findAllPersons()
	 */
	public PersonList findAllPersons() {
		return personList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.newfound.rest.server.service.PersonDaoService#findPerson(int)
	 */
	public PersonList findPersonById(final Integer id) {
		Predicate<Person> filter = new Predicate<Person>() {
			public boolean apply(Person person) {
				return person.getId() == id;
			}
		};
		return sendResponse(filter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newfound.rest.server.service.PersonDaoService#findPersonByFirstName(
	 * java.lang.String)
	 */
	public PersonList findPersonByFirstName(final String firstName) {
		Predicate<Person> filter = new Predicate<Person>() {
			public boolean apply(Person person) {
				return person.getFirstName().equalsIgnoreCase(firstName);
			}
		};
		return sendResponse(filter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newfound.rest.server.service.PersonDaoService#findPersonByGender(java
	 * .lang.String)
	 */
	public PersonList findPersonByGender(final Gender gender) {
		Predicate<Person> filter = new Predicate<Person>() {
			public boolean apply(Person person) {
				return person.getGender().equals(gender);
			}
		};
		return sendResponse(filter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.newfound.rest.server.service.PersonDaoService#autoCreatePerson()
	 */
	public PersonList autoCreatePerson() {
		Integer id = Integer.parseInt(DateTimeFormat.forPattern("yyMMddmmss").print(new DateTime()));
		Person person = new Person(id, DEFAULT_FNAME, DEFAULT_LNAME, DEFAULT_EMAIL, Gender.MALE, 9000);
		log.info("Creating new person id: " + id + "[yyMMddmmss]" + " " + person);
		/**
		 * Add to Persons
		 */
		personList.getPersons().add(person);
		/**
		 * Send Response
		 */
		PersonList persons = new PersonList();
		persons.setCode(STATUS_SUCCESS);
		persons.setMessage(MESSAGE_CREATED);
		persons.setPersons(Lists.newArrayList(person));

		return persons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newfound.rest.server.service.PersonDaoService#deletePerson(Integer)
	 */
	public PersonList deletePerson(Integer id) {
		boolean success = false;
		Person person = null;
		for (Iterator<Person> iter = personList.getPersons().listIterator(); iter.hasNext();) {
			person = iter.next();
			if (person.getId().equals(id)) {
				iter.remove();
				success = true;
				break;
			}
		}
		PersonList persons = new PersonList();
		if (!success) {
			persons.setCode(STATUS_ERROR);
			persons.setMessage(MESSAGE_FAILURE);
			persons.setPersons(Lists.newArrayList(UNKNOWN));
		} else {
			persons.setCode(STATUS_SUCCESS);
			persons.setMessage(MESSAGE_DELETED);
			persons.setPersons(Lists.newArrayList(person));
		}
		return persons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newfound.rest.server.service.PersonDaoService#updatePerson(java.lang.
	 * Integer, java.lang.Integer)
	 */
	public PersonList updatePerson(Integer id, Integer newSalary) {
		boolean success = false;
		Person person = null;
		Person newPerson = null;
		for (Iterator<Person> iter = personList.getPersons().listIterator(); iter.hasNext();) {
			person = iter.next();
			if (person.getId().equals(id)) {
				iter.remove();
				newPerson = new Person();
				/**
				 * Copy Object Properties and Update Salary
				 */
				BeanUtils.copyProperties(person, newPerson);
				newPerson.setSalary(newSalary);
				personList.getPersons().add(newPerson);
				success = true;
				break;
			}
		}
		PersonList persons = new PersonList();
		if (!success) {
			persons.setCode(STATUS_ERROR);
			persons.setMessage(MESSAGE_FAILURE);
			persons.setPersons(Lists.newArrayList(UNKNOWN));
		} else {
			persons.setCode(STATUS_SUCCESS);
			persons.setMessage(MESSAGE_UPDATED);
			persons.setPersons(Lists.newArrayList(newPerson));
		}
		return persons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newfound.rest.server.service.PersonDaoService#manualCreatePerson(com.
	 * newfound.rest.server.model.Person)
	 */
	public boolean manualCreatePerson(Person person) {
		log.info("Creating user defined (manual) person: " + person.toString());
		if(isUserExist(person.getId())) {
			log.info("User: " + person + " already exists");
			return false;
		}
		/**
		 * Add to Persons
		 */
		personList.getPersons().add(person);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newfound.rest.server.service.PersonDaoService#isUserExist(java.lang.
	 * Integer)
	 */
	public boolean isUserExist(Integer id) {
		Person person = null;
		boolean exists = false;
		for (Iterator<Person> iter = personList.getPersons().listIterator(); iter.hasNext();) {
			person = iter.next();
			if (person.getId().equals(id)) {
				exists = true;
				break;
			}
		}
		return exists;
	}
}
