package datadriverntesting;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static datadriverntesting.XLUtils.*;


public class dataDrivenTestDemo {

    // Define a method with TestNG annotation @Test
    @Test(dataProvider = "userDataProvider")
    void CreateNewUsers(String username, String job){
        // define the base uri
        RestAssured.baseURI = "https://reqres.in";
        // Add a request specification
        RequestSpecification httpRequest = RestAssured.given();

        // Create a json object to hold the request body data
        JSONObject requestParams = new JSONObject();

        // Create the request body

        requestParams.put("name",username);
        requestParams.put("job",job);

        // add the request headers
        httpRequest.header("Content-Type", "application/json");

        // add the request body to request

        httpRequest.body(requestParams.toJSONString());

        // get the response
        Response response = httpRequest.request(Method.POST, "/api/users");

        System.out.println(response.getBody().asString());
        String responseBody = response.getBody().asString();

        int responseStatusCode = response.getStatusCode();
        Assert.assertEquals(responseStatusCode, 201);
        Assert.assertEquals(responseBody.contains(username),true);



    }

    @DataProvider(name="userDataProvider")
    public static Object[][] getuserData() throws IOException {

        String path=System.getProperty("user.dir") + "/src/test/java/datadriverntesting/userinfo.xlsx";
        System.out.println(path);
        int rowCount = getRowCount(path,"usersinfo");
        System.out.println(rowCount);
        int ColCount = XLUtils.getCelCount(path,"usersinfo", 1 );
        System.out.println("This is the coloumn count" + ColCount);
        String userData[][] = new String[rowCount][ColCount];

        for (int i=1; i<=rowCount; i++){

            for (int j=0; j < ColCount; j++){
                userData[i-1][j]= String.valueOf(XLUtils.getCellData(path,"usersinfo", i, j));

            }
        }
        //String userData[][] = {{"SP1","QA"},{"SP2","DEV"},{"SP3","DEVOps"}};
        return(userData);
    }








}
