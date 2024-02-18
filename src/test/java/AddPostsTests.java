import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class AddPostsTests {

    @Test
    public void addPostAsStringTest() {

        String newPost = "{\n" +
                "    \"title\": \"Lekca 167: Dodanie nowego posta - zmiana content type\",\n" +
                "    \"author\": \"bartoszlagoda\"\n" +
                "}";

        given()
                .log().all() // wypisanie logów requesta
                .contentType(ContentType.JSON) // ustawienie jsonowego kontentu
                .body(newPost) // ustalenie tego co bedzie wysylane
                .when()
                .post("http://localhost:3000/posts")// na jaki adres jest wysylka
                .then()
                .log().all(); // wyświetlenie logów response
    }

    @Test
    public void addPostFromFileTest(){
        File newPost = new File("src/test/resources/post.json");

        given()
                .log().all() // wypisanie logów requesta
                .contentType(ContentType.JSON) // ustawienie jsonowego kontentu
                .body(newPost) // ustalenie tego co bedzie wysylane
                .when()
                .post("http://localhost:3000/posts")// na jaki adres jest wysylka
                .then()
                .log().all(); // wyświetlenie logów response
    }
}
