package StepDefinition;

import Pojo.AddApi;
import Pojo.Location;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlace extends Utils {
    RequestSpecification placeReq;
    Response response;
    ResponseSpecification res;
    String addPlaceResp;
    static String placeId;
    TestDataBuild data=new TestDataBuild();


    @Given("Add place payload")
    @Given("Add place payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address)  throws IOException {
        placeReq=given().spec(requestSpecification())
                .body(data.addPlacePayload(name,language,address));
    }
    @When("user calls {string} with {string} http request")
    public void userCallsWithHttpRequest(String resource, String method) {
        APIResources apiRes = APIResources.valueOf(resource);
        if (method.equalsIgnoreCase( "POST")) {
            response = placeReq.when().post(apiRes.getResource());
        } else if (method.equalsIgnoreCase("Get")) {
            response=placeReq.when().get(apiRes.getResource());
        }
    }
    @Then("verify status code is {int}")
    public void verifyStatusCodeIs(int code) {
        assertEquals(response.getStatusCode(),code);
    }
    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String keyValue, String expectedValue) {
        assertEquals(RawToJson(response,keyValue),expectedValue);
    }


    @And("verify place_id  mapped to {string}  using {string}")
    public void verifyPlace_idMappedToUsing(String keyName, String resource) throws IOException {
        placeId=RawToJson(response,"place_id");
        placeReq=given().spec(requestSpecification()).queryParam("place_id",placeId);
        userCallsWithHttpRequest(resource,"Get");
        assertEquals(RawToJson(response,"name"),keyName);
    }


    @Given("Delete place payload")
    public void deletePlacePayload() throws IOException {
      placeReq=  given().spec(requestSpecification()).body(data.deleteApiPayload(placeId));
    }
}
