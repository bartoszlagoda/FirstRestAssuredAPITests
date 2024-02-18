package db2PostTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class JsonPathTests {

    @Test
    public void checkSpecificFieldJsonPath(){
        Response response = RestAssured.get("http://localhost:3000/posts/1");

        System.out.println(response.asString());
    }
}
