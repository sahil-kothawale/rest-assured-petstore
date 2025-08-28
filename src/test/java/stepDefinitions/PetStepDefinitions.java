package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.module.jsv.JsonSchemaValidator;
import java.util.List;
import java.io.File;
import dataFactories.PetFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.request.PetRequest;
import utilities.ConfigReader;
import utilities.TestContext;

public class PetStepDefinitions {
	
	private TestContext context;
	public PetStepDefinitions(TestContext context) {
		this.context = context;
	}

	@Given("Petstore swagger service is available")
	public void petstore_swagger_service_is_available() {
		RequestSpecification requestSpec = given().baseUri(ConfigReader.getProperty("base_uri"))
												  .header("Content-Type", "application/json");
		context.setRequest(requestSpec);
	}

	@Given("endpoint to hit is {string}")
	public void endpoint_to_hit_is(String endpoint) {
		context.getRequest().given().basePath(endpoint);
	}

	@When("I retrieve the pet by its ID")
	public void i_retrieve_the_pet_by_its_id() {
	    Response response = context.getRequest()
	    		.when()
	    		.get();
	    context.setResponse(response);
	}
	
	@When("I retrieve the pets by their status {string}")
	public void i_retrieve_the_pets_by_their_status(String inputStatus) {
	    Response response = context.getRequest()
	    		.given().queryParam("status", inputStatus)
	    		.when()
	    		.get();
	    context.setResponse(response);
	}
	
	@When("I add new pet to the store from pet-factory")
	public void I_add_new_pet_to_the_store_from_pet_factory() {
		PetRequest requestBody = PetFactory.createRandomPet();
	    Response response = context.getRequest()
	    						   .when().contentType(ContentType.JSON)
	    						   		  .body(requestBody)
	    						   		  .post();
	    context.setResponse(response);
	}
	
	@When("I add new pet to the store from json file {string}")
	public void I_add_new_pet_to_the_store_from_json_file (String file) {
	    Response response = context.getRequest()
	    						   .when().contentType(ContentType.JSON)
	    						   		  .body(new File("src/test/resources/testData/" + file))
	    						   		  .post();
	    context.setResponse(response);
	}

	@Then("response status code should be {int}")
	public void response_status_code_should_be(Integer expectedStatusCode) {
	    context.getResponse().then().statusCode(expectedStatusCode)
	    					 		.log().all();
	}
	
	@Then("response body matches the expected schema {string}")
	public void response_body_matches_the_expected_schema(String schemaFile) {
		context.getResponse().then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFile));
	}

	@Then("response should contain the same pet ID {int}")
	public void response_should_contain_the_same_pet_id(Integer expectedId) {
	    context.getResponse().then().body("id", equalTo(expectedId));
	}
	
	@Then("validate all pets returned have status as {string}")
	public void validate_all_pets_returned_have_status_as(String expectedStatus) {
	    List<String> actualStatuses = context.getResponse().jsonPath().getList("status");
		for(String actualStatus : actualStatuses) {
			assertThat(actualStatus, equalTo(expectedStatus));
		}
	}
	
	
}
