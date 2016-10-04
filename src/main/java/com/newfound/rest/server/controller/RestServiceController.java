/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package com.newfound.rest.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newfound.rest.server.constants.Gender;
import com.newfound.rest.server.model.PersonList;
import com.newfound.rest.server.service.PersonDaoServiceImpl;

@RestController
@RequestMapping("/person")
public class RestServiceController {

	@Autowired
	PersonDaoServiceImpl personDaoServiceImpl;

	/**
	 * Find Person(s)
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/all")
	public ResponseEntity<PersonList> getAllPersons() {
		PersonList persons = personDaoServiceImpl.findAllPersons();
		return new ResponseEntity<PersonList>(persons, HttpStatus.OK);
	}

	/**
	 * Find Person By Id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/id/{id}")
	public ResponseEntity<PersonList> getPersonById(@AuthenticationPrincipal final UserDetails user,
			@PathVariable("id") Integer id) {
		PersonList persons = personDaoServiceImpl.findPersonById(id);
		return new ResponseEntity<PersonList>(persons, HttpStatus.OK);
	}

	/**
	 * Find Person By Gender
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/gender/{gender}")
	public ResponseEntity<PersonList> getPersonByGender(@AuthenticationPrincipal final UserDetails user,
			@PathVariable("gender") String g) {
		Gender gender = Gender.valueOf(g);
		PersonList persons = personDaoServiceImpl.findPersonByGender(gender);
		return new ResponseEntity<PersonList>(persons, HttpStatus.OK);
	}

	/**
	 * Find Person By FirstName
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/firstName/{firstName}")
	public ResponseEntity<PersonList> getPersonByFirstName(@AuthenticationPrincipal final UserDetails user,
			@PathVariable("firstName") String firstName) {
		PersonList persons = personDaoServiceImpl.findPersonByFirstName(firstName);
		return new ResponseEntity<PersonList>(persons, HttpStatus.OK);
	}

	/**
	 * Create Person
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<PersonList> createPerson(@AuthenticationPrincipal final UserDetails user) {
		PersonList persons = personDaoServiceImpl.createPerson();
		return new ResponseEntity<PersonList>(persons, HttpStatus.OK);
	}

	/**
	 * Delete Person by id
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/delete/{id}")
	public ResponseEntity<PersonList> deletePerson(@AuthenticationPrincipal final UserDetails user,
			@PathVariable("id") Integer id) {
		PersonList persons = personDaoServiceImpl.deletePerson(id);
		return new ResponseEntity<PersonList>(persons, HttpStatus.OK);
	}

	/**
	 * Update Person with new Salary
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/update/{id}/{salary}")
	public ResponseEntity<PersonList> updatePerson(@AuthenticationPrincipal final UserDetails user,
			@PathVariable("id") Integer id, @PathVariable("salary") Integer salary) {
		PersonList persons = personDaoServiceImpl.updatePerson(id, salary);
		return new ResponseEntity<PersonList>(persons, HttpStatus.OK);
	}
}
