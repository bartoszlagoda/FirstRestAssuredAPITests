package dbPostTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetSpecificFieldValueTest {

    @Test
    public void testCheckSpecificFieldWithPath(){

        Response response = RestAssured.get("http://localhost:3000/posts/1");
        String author = response.path("author");

        Assertions.assertEquals(author,"bartoszlagoda");
    }

    @Test
    public void testCheckSpecificFieldWithPathWithoutVarAuthor(){

        String response = RestAssured.get("http://localhost:3000/posts/1").path("author");

        Assertions.assertEquals(response,"bartoszlagoda");
    }

    @Test
    public void testCheckSpecificFieldWithJsonPath(){

        Response response = RestAssured.get("http://localhost:3000/posts/1");
        JsonPath jsonPath = new JsonPath(response.asString());
        String author = jsonPath.get("author");

        Assertions.assertEquals(author,"bartoszlagoda");
    }

    @Test
    public void testCheckSpecificFieldWithJsonPathWithoutVarJsonPath(){

        String stringResponse = RestAssured.get("http://localhost:3000/posts/1").asString();
        String author = JsonPath.from(stringResponse).get("author");

        Assertions.assertEquals(author,"bartoszlagoda");
    }
}
