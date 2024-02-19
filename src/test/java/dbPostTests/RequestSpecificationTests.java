package dbPostTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecificationTests {

    RequestSpecification spec;

    @BeforeEach
    public void setUp(){
        spec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:3000")
                .setBasePath("posts")
                .build();
    }

    @Test
    public void getAllPostsTest(){
        given().
                spec(spec).
        when()
                .get()
                .then()
                .log()
                .body(); // zaloguj tylko body z response
    }

    @Test
    public void getPostTest(){
        given().
                baseUri("http://localhost:3000").
                basePath("posts").
        when()
                .get("/1")
                .then()
                .log().body(); // zaloguj tylko body z response
    }
}
