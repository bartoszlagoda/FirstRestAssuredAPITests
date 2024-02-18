import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class AddPostsTests {

    @Test
    public void addPost(){

        String newPost = "{\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"Pierwszy post\",\n" +
                "    \"author\": \"Bartosz\"\n" +
                "}";

        given()
                .body(newPost)
        .when()
                .body("http://localhost:3000/posts")
        .then()
                .log().all();
    }
}
