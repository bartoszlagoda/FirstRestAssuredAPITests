package dbPostTest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class FilterPostsTests {

    @Test
    public void testFilterPostsByAuthor(){
        given()
                .log()
                .all()
                .queryParam("author","unknownauthor")
                .when()
                .get("http://localhost:3000/posts")
                .then()
                .log()
                .all();
    }

    @Test
    public void testFilterPostsById(){
        given()
                .log()
                .all()
                .queryParam("id","1","2")
                .when()
                .get("http://localhost:3000/posts")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void testFilterPostsByAuthorAndTitle(){
        Map<String,Object> params = new HashMap<>();
        params.put("author","bartoszlagoda");
        params.put("title","Lekca 167: Dodanie nowego posta - zmiana content type");
        given()
                .log()
                .all()
                .queryParams(params)
                .when()
                .get("http://localhost:3000/posts")
                .then()
                .log()
                .all()
                .statusCode(Matchers.equalTo(200)); // Matchery mają wiele funkcji walidujących
    }

    @Test
    public void testFilterPostsByAuthorAndTitleWithStatusLineValidation(){
        Map<String,Object> params = new HashMap<>();
        params.put("author","bartoszlagoda");
        params.put("title","Lekca 167: Dodanie nowego posta - zmiana content type");
        given()
                .log()
                .all()
                .queryParams(params)
                .when()
                .get("http://localhost:3000/posts")
                .then()
                .log()
                .all()
                .statusLine(Matchers.containsString("OK"));
    }
}
