import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.TestRunner;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.List;

public class TC01_GETUserDetails {

    @BeforeSuite
    public void setup() {

        RestAssured.baseURI = "https://api.postcodes.io/";
    }

    @Test
    public void getzipCodeDetails(){
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("random/postcodes")
                    .then()
                    .extract().response();
            System.out.println(response.getBody().prettyPrint());
            Assert.assertEquals(200, response.statusCode());
        System.out.println(response.jsonPath().getString("result"));
    }


    @Test
    void CreateNewUsers(){
        // Define Base URI

        RestAssured.baseURI = "https://reqres.in";

        // Mention reqeustspecification
        RequestSpecification httpRequest = RestAssured.given();

        // create requestParams object using json Object
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Keenu Reeve");
        requestParams.put("Job", "Actor");

        // add request body
        httpRequest.body(requestParams.toJSONString());

        // add request headers
        httpRequest.header("Content-Type","application/json");

        // send the request
        Response response = httpRequest.request(Method.POST, "/api/users");

        // capture the response body
        System.out.println(response.getBody().asString());

        // assert the response status code
        int responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, 201);

    }
}
