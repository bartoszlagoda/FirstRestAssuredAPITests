package dbPostTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecificationTests {

    RequestSpecification reqSpec;
    ResponseSpecification resSpec;

    @BeforeEach
    public void testSetUp(){
        reqSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:3000")
                .setBasePath("posts")
                .build();

        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
        ResponseLoggingFilter responseLoggingFilter = new ResponseLoggingFilter(); // zamiast then().log().body()
        RequestLoggingFilter requestLoggingFilter = new RequestLoggingFilter(); // zamiast given().log().body()
        RestAssured.filters(requestLoggingFilter,responseLoggingFilter);
    }

    @Test
    public void testGetAllPosts(){
        given().
                spec(reqSpec).
        when()
                .get()
                .then()
                .spec(resSpec);
    }

    @Test
    public void testGetPost(){
        given().
                baseUri("http://localhost:3000").
                basePath("posts").
        when()
                .get("/1")
                .then()
                .statusCode(200);
    }
}
