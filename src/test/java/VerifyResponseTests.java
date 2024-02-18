import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class VerifyResponseTests {

    @Test
    public void getAllPostTest(){

        String expected = "{\n" +
                "  \"title\": \"Lekcja 172: Nadpisywanie istniejącego posta\",\n" +
                "  \"author\": \"bartoszlagoda\",\n" +
                "  \"id\": 1\n" +
                "}";

        given()
                .log()
                .all()
                .when()
                .get("http://localhost:3000/posts/{postId}", 1)
                .then()
                .log()
                .all()
                .body(Matchers.equalTo(expected));
    }

    @Test
    public void getPostContainsTest(){

        given()
                .log()
                .all()
                .when()
                .get("http://localhost:3000/posts/{postId}", 1)
                .then()
                .log()
                .all()
                .body(Matchers.containsString("Lekcja 172: Nadpisywanie istniejącego posta"));
    }

    @Test
    public void checkSpecificFieldTest(){

        given()
                .log()
                .all()
                .when()
                .get("http://localhost:3000/posts/{postId}", 1)
                .then()
                .log()
                .all()
                .body("title",Matchers.equalTo("Lekcja 172: Nadpisywanie istniejącego posta"))
                .and()
                .body("author", Matchers.equalTo("bartoszlagoda"));
    }
}
