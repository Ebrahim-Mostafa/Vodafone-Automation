package RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetPostByID {

    @Test(priority = 10)
    public void getPostByIDTest(){

        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
        RequestSpecification request = RestAssured.given().log().all(); //log is used to get more info

        Response response = request.get("/posts/1");

        System.out.println(response.asString());  //to print all result in JSON
        System.out.println(response.prettyPrint()); //to print on console appropriately
        System.out.println(response.getStatusCode()); //to see which code 200, 400 etc.

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        boolean title = response.getBody().prettyPrint().contains("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        Assert.assertTrue(title);

    }
}
