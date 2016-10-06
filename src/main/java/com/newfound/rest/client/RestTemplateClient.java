/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package com.newfound.rest.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.newfound.rest.server.constants.Gender;
import com.newfound.rest.server.model.Person;
import com.newfound.rest.server.model.PersonList;

public class RestTemplateClient implements RestClientConstants {

	static final Logger log = LoggerFactory.getLogger(RestTemplateClient.class);

	static final String encodedLogin = new String(Base64.encode((USER + ":" + PASSWORD).getBytes()));

	/**
	 * Get Http Information from ResponseEntity
	 * 
	 * @param <T>
	 */
	private <T> void getHttpResponseInfo(ResponseEntity<T> responseEntity) {
		MediaType contentType = responseEntity.getHeaders().getContentType();
		HttpStatus statusCode = responseEntity.getStatusCode();

		log.info("\n");
		log.info("ContentType: " + contentType);
		log.info("StatusCode: " + statusCode);

		PersonList persons = (PersonList) responseEntity.getBody();
		log.info("Code: " + persons.getCode());
		log.info("Message: " + persons.getMessage());

		for (Person person : persons.getPersons()) {
			log.info(person.toString());
		}
	}

	/**
	 * Find by ID
	 */
	private void findById() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		/**
		 * Authorization
		 */
		headers.add("Authorization", "Basic " + encodedLogin);

		HttpEntity<PersonList> requestEntity = new HttpEntity<PersonList>(headers);
		String resourceUrl = REST_SERVICE_URL + FIND_BY_ID + "." + ResponseFormat.XML.getFormat();
		ResponseEntity<PersonList> responseEntity = restTemplate.exchange(resourceUrl, HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<PersonList>() {
				});
		getHttpResponseInfo(responseEntity);
	}

	/**
	 * Find by Gender
	 */
	private void findByGender() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		/**
		 * Authorization
		 */
		headers.add("Authorization", "Basic " + encodedLogin);

		HttpEntity<PersonList> requestEntity = new HttpEntity<PersonList>(headers);
		String resourceUrl = REST_SERVICE_URL + FIND_BY_GENDER_MALE;

		ResponseEntity<PersonList> responseEntity = restTemplate.exchange(resourceUrl, HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<PersonList>() {
				});
		getHttpResponseInfo(responseEntity);
	}

	/**
	 * Create
	 */
	private void create() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		/**
		 * Authorization
		 */
		headers.add("Authorization", "Basic " + encodedLogin);

		ResponseEntity<String> responseEntity = null;
		try {
			/**
			 * Add Person
			 */
			Person person = new Person(10, "NEWFOUND", "SYSTEMS", "support@newfound-systems.com", Gender.MALE, 8888);
			/**
			 * Add content type JSON
			 */
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Person> requestEntity = new HttpEntity<Person>(person, headers);
			String resourceUrl = REST_SERVICE_URL + CREATE;
			responseEntity = restTemplate.exchange(resourceUrl, HttpMethod.POST, requestEntity,
					new ParameterizedTypeReference<String>() {
					});
			/**
			 * Get Status and RestController Response Body
			 */
			log.info("StatusCode: " + responseEntity.getStatusCode());
			log.info("Message: " + responseEntity.getBody() + " "  + responseEntity.getStatusCodeValue());
		} catch (HttpClientErrorException e) {
			log.info(e.getStatusCode().toString());
			log.info(e.getResponseBodyAsString());
		}
	}

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RestTemplateClient client = new RestTemplateClient();

		client.findById();
		client.findByGender();
		client.create();
	}
}
