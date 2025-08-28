Feature: Pets feature
Contains API tests for Pets feature of Swagger Petstore API

@APItest @Pets @GET
Scenario Outline: GET - Find a Pet by Id
	Given Petstore swagger service is available
	And endpoint to hit is "/pet/<id>"
	When I retrieve the pet by its ID
	Then response status code should be 200
	And response should contain the same pet ID <id>
Examples:
| id |
| 13 |

@APItest @Pets @GET
Scenario Outline: GET - Find Pets by Status
	Given Petstore swagger service is available
	And endpoint to hit is "/pet/findByStatus"
	When I retrieve the pets by their status "<status>"
	Then response status code should be 200
	And validate all pets returned have status as "<status>"
Examples:
|  status   |
| available |

@APItest @Pets @POST
Scenario: POST - Add a new pet to store - By using POJO
	Given Petstore swagger service is available
	And endpoint to hit is "/pet"
	When I add new pet to the store from pet-factory
	Then response status code should be 200
	And response body matches the expected schema "schemas/postPet-schema.json"

@APItest @Pets @POST
Scenario: POST - Add a new pet to store - By using JSON
	Given Petstore swagger service is available
	And endpoint to hit is "/pet"
	When I add new pet to the store from json file "pet/PetRequest.json"
	Then response status code should be 200
	And response body matches the expected schema "schemas/postPet-schema.json"
	