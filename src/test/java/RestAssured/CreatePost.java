package RestAssured;

import Recorders.ATUTestRecord;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreatePost {

    @Test(priority = 11)
    public void createPostAPITest() throws ATUTestRecorderException {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RequestSpecification request = RestAssured.given().log().all();

        String payload = "{\r\n\t\"title\": \"foo\",\r\n    \"body\": \"bar\",\r\n    \"userId\": 1\r\n}";

        request.contentType("application/json");
        request.body(payload);
        Response response = request.post("/posts");

        System.out.println(response.prettyPrint());
        System.out.println(response.statusCode());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);

        JsonPath js = response.jsonPath();
        js.get("userId");

        int userId = js.get("userId");
        System.out.println("userId is"+ userId);
        Assert.assertNotNull(userId);

        ATUTestRecord.stopScreenRecorder();
    }
}
