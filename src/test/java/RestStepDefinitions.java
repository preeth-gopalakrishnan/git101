

import org.junit.Assert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestStepDefinitions {

	private String endpoint = "http://localhost:8080/api";
	private RequestSpecification request;
	private Response response;


	@When("^I send a GET request to \"([^\"]*)\"$")
	public void iSendAGETRequestTo(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		request = given().queryParam("name", "preeth");
		String uri = endpoint+arg1;
		response = request.get(uri);
		System.out.println(response.getBody().asString());
	}

	@Then("^the response status should be \"([^\"]*)\"$")
	public void theResponseStatusShouldBe(int code) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(code, response.getStatusCode());
	}

	@When("^I send a GET request to \"([^\"]*)\" with name \"([^\"]*)\"$")
	public void iSendAGETRequestToWithName(String url, String name) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		request = given().queryParam("name", name);
		String uri = endpoint+url;
		response = request.get(uri);
		System.out.println(response.getBody().asString());
	}

	@Then("^the response should contain \"([^\"]*)\"$")
	public void theResponseShouldContain(String expectedContent) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		JsonPath jsonPath = new JsonPath(response.getBody().asString());
		Assert.assertEquals(expectedContent, jsonPath.get("content"));
	}


	@When("^I send a POST request to \"([^\"]*)\" with first name \"([^\"]*)\"  and id \"([^\"]*)\"\\.$")
	public void iSendAPOSTRequestToWithFirstNameAndId(String url, String name, int id) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String content = "{\"id\":1,\"firstName\":\"preeth\",\"lastName\":\"g\",\"email\":\"email@email.com\"}";
		request = given().contentType(ContentType.JSON).body(content);
		String uri = endpoint+url;
		response = request.post(uri);
	}

	@Then("^the response should contain first name \"([^\"]*)\"  and id \"([^\"]*)\"\\.$")
	public void theResponseShouldContainFirstNameAndId(String name, int id) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		JsonPath jsonPath = new JsonPath(response.getBody().asString());
		Assert.assertEquals(name, jsonPath.get("firstName"));
		Assert.assertEquals(id, (int)jsonPath.get("id"));
	}
	
	
	
	@When("^I send a PUT request to \"([^\"]*)\" with first name \"([^\"]*)\" and id \"([^\"]*)\"\\.$")
	public void iSendAPUTRequestToWithFirstNameAndId(String url, String name, String id) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String content = "{\"id\":2,\"firstName\":\"preeth\",\"lastName\":\"g\",\"email\":\"email@email.com\"}";
		request = given().contentType(ContentType.JSON).body(content);
		String uri = endpoint+url;
		response = request.put(uri);
	}

	@Then("^the response should contain first name \"([^\"]*)\" and id \"([^\"]*)\"\\.$")
	public void theResponseShouldContainFirstNameAndId1(String name, int id) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		JsonPath jsonPath = new JsonPath(response.getBody().asString());
		Assert.assertEquals(name, jsonPath.get("firstName"));
		Assert.assertEquals(id, (int)jsonPath.get("id"));
	}

}
