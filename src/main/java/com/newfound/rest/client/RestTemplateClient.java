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
import org.springframework.web.client.RestTemplate;

import com.newfound.rest.server.model.Person;
import com.newfound.rest.server.model.PersonList;

public class RestTemplateClient implements RestClientConstants {

	static final Logger log = LoggerFactory.getLogger(RestTemplateClient.class);

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
	 * Exchange with RESTful Web Service as Client
	 */
	private void exchange() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		/**
		 * Authorization Encode in Base64
		 */
		headers.add("Authorization", "Basic " + new String(Base64.encode((USER + ":" + PASSWORD).getBytes())));
		/**
		 * Find by ID
		 */
		{
			HttpEntity<PersonList> requestEntity = new HttpEntity<PersonList>(headers);
			String resourceUrl = REST_SERVICE_URL + FIND_BY_ID + "." + ResponseFormat.XML.getFormat();
			ResponseEntity<PersonList> responseEntity = restTemplate.exchange(resourceUrl, HttpMethod.GET,
					requestEntity, new ParameterizedTypeReference<PersonList>() {
					});
			getHttpResponseInfo(responseEntity);
		}
		/**
		 * Create Person
		 */
		{
			HttpEntity<PersonList> requestEntity = new HttpEntity<PersonList>(headers);
			String resourceUrl = REST_SERVICE_URL + CREATE;
			ResponseEntity<PersonList> responseEntity = restTemplate.exchange(resourceUrl, HttpMethod.POST,
					requestEntity, new ParameterizedTypeReference<PersonList>() {
					});
			getHttpResponseInfo(responseEntity);
		}
		/**
		 * Find By GENDER (MALE)
		 */
		{
			HttpEntity<PersonList> requestEntity = new HttpEntity<PersonList>(headers);
			String resourceUrl = REST_SERVICE_URL + FIND_BY_GENDER_MALE;

			ResponseEntity<PersonList> responseEntity = restTemplate.exchange(resourceUrl, HttpMethod.GET,
					requestEntity, new ParameterizedTypeReference<PersonList>() {
					});
			getHttpResponseInfo(responseEntity);
		}
	}

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new RestTemplateClient().exchange();
	}
}
