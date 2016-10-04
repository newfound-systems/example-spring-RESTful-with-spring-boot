/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package com.newfound.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.security.crypto.codec.Base64;

public class TestGenericHttpService implements RestClientConstants {

	/**
	 * Rest Client Request
	 * 
	 * @param urlString
	 * @param requestMethod
	 * @throws IOException
	 */
	public void request(String urlString, RequestMethod requestMethod) throws IOException {
		HttpURLConnection connection = null;
		try {
			System.out.println("----\nSending request: " + urlString);
			URL url = new URL(urlString);
			String auth = USER + ":" + PASSWORD;
			String encodedLogin = new String(Base64.encode(auth.getBytes()));
			/**
			 * Use HttpURLConnection for both GET or POST
			 */
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod(requestMethod.name());
			connection.setDoOutput(true);
			connection.setConnectTimeout(URL_TIMEOUT);
			connection.setReadTimeout(URL_TIMEOUT);

			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Authorization", "Basic " + encodedLogin);
			/**
			 * Open Stream
			 */
			InputStream content = (InputStream) connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(content));
			String line;

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
	}

	/**
	 * Main
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		TestGenericHttpService client = new TestGenericHttpService();

		client.request(REST_SERVICE_URL + FIND_ALL, RequestMethod.GET);
		client.request(REST_SERVICE_URL + FIND_BY_ID, RequestMethod.GET);
		client.request(REST_SERVICE_URL + FIND_BY_FIRSTNAME, RequestMethod.GET);
		client.request(REST_SERVICE_URL + FIND_BY_GENDER_FEMALE, RequestMethod.GET);
		client.request(REST_SERVICE_URL + CREATE, RequestMethod.POST);
		client.request(REST_SERVICE_URL + DELETE, RequestMethod.POST);
	}
}
