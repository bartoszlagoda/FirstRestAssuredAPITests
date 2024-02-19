package dbPostTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecificationTests {

    RequestSpecification spec;
    ResponseSpecification resSpec;

    @BeforeEach
    public void setUp(){
        spec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:3000")
                .setBasePath("posts")
                .build();

        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
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
                .body() // zaloguj tylko body z response
                .spec(resSpec);
    }

    @Test
    public void getPostTest(){
        given().
                baseUri("http://localhost:3000").
                basePath("posts").
        when()
                .get("/1")
                .then()
                .log().body() // zaloguj tylko body z response
                .statusCode(200);
    }
}
