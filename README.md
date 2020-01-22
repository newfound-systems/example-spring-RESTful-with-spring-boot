# Spring RESTful CRUD Service using SpringBoot
Comprehensive Spring RESTful Server and Client Application with CRUD Service (inMemory). Contributors are most welcome and you are free to distribute and usage. Liked Sources, Just send Thanks to [NEWFOUND SYSTEMS (http://www.newfound-systems.com)]

## Getting Started

### Prerequisities
* Basic understanding of Spring Framework which includes Spring Boot, Spring Security.
* Understanding of JSON or XML.
* How to Use Google Collections.

### Technologies
* JDK 1.7 and above
* Maven 3+
* Jackson Bind
* Apache Tomcat 8+
* Google Guava Collections
* Advanced Rest Client as Chrome Extension or PostMan for REST Client Testing

## Running the server
Spring Boot Main Class, Just run below class like any another Java Code
* com.newfound.rest.server.app.RestServerApplication

## Running the client
### Run using Sample Java Client
* com.newfound.rest.client.RestTemplateClient

### Using browser for GET Method
####
* http://localhost:8080/person/find/all.json (Notice expected response is JSON)
* http://localhost:8080/person/find/all.xml (Notice expected response is XML)
####
* http://localhost:8080/person/find/id/1.json
* http://localhost:8080/person/find/gender/MALE.json
* http://localhost:8080/person/find/firstName/John

### Using Advanced REST Client or POSTMAN for POST Method
* http://localhost:8080/person/update/1/99999
* http://localhost:8080/person/create
* http://localhost:8080/person/delete/1

### Sample Output (JSON Request)
* Request http://localhost:8080/person/find/id/1.json

{
  "code" : 0,
  "message" : "SUCCESS",
  "persons" : [ {
    "id" : 1,
    "firstName" : "John",
    "lastName" : "Doe",
    "emailId" : "john.doe@somewhere.com",
    "gender" : "MALE",
    "salary" : 1000
  } ]
}

### Sample Output (XML Request)
* Request http://localhost:8080/person/find/id/1.xml

<?xml version="1.0" encoding="UTF-8" standalone="true"?>
<persons>
  <code>0</code>
  <message>SUCCESS</message>
  <person>
    <id>1</id>
    <firstName>John</firstName>
    <lastName>Doe</lastName>
    <emailId>john.doe@somewhere.com</emailId>
    <gender>MALE</gender>
    <salary>1000</salary>
  </person>
</persons>

## Using CURL in LINUX for communications
$ curl --basic --user admin:admin --request GET http://localhost:8080/person/find/id/1.json

$ curl --basic --user admin:admin --request POST http://localhost:8080/person/create

## Running on https
You can uncomment **src/main/resources/application.properties** to run secured service enabled

## Versioning
We use [Apache Subversion (https://subversion.apache.org/)] for versioning. 

## Authors
* **Chetan Anand** - *Developer* - [NEWFOUND SYSTEMS http://www.newfound-systems.com]

## License
This project is licensed under the Open Free for all License.

## Acknowledgments
* Many Inspirations from http://www.websystique.com for their walk through of Spring related technologies and our big Gurus out in http://stackoverflow.com. Many Thanks
