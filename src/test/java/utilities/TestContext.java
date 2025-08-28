package utilities;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContext {
	
	private RequestSpecification requestSpec;
	private Response response;
	
	public RequestSpecification getRequest() {
		return this.requestSpec;
	}
	
	public void setRequest(RequestSpecification requestSpec) {
		this.requestSpec = requestSpec;
	}
	
	public Response getResponse() {
		return this.response;
	}
	
	public void setResponse(Response response) {
		this.response = response;
	}
}
