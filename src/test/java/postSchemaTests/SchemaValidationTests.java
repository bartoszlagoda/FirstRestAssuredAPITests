package postSchemaTests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidationTests {

    @Test
    public void getPostTest(){

        // https://www.liquid-technologies.com/online-json-to-schema-converter

        given()
                .log()
                .all().
        when()
                .get("http://localhost:3000/posts/1").
        then()
                .log()
                .all()
                .assertThat().body(matchesJsonSchemaInClasspath("postSchema.json"));
    }
}
