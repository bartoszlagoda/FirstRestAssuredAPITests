import io.restassured.http.ContentType;
import model.Post;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdatePostsTests {

    @Test
    public void updatePost(){
        Post newPost = new Post();
        newPost.setTitle("Lekcja 172: Nadpisywanie istniejącego posta");
        newPost.setAuthor("bartoszlagoda");

        given()
                .log().all() // wypisanie logów requesta
                .contentType(ContentType.JSON) // ustawienie jsonowego kontentu
                .body(newPost) // ustalenie tego co bedzie wysylane
                .when()
                .put("http://localhost:3000/posts/1")// PUT służy do aktualizacji całego zasobu
                .then()
                .log().all(); // wyświetlenie logów response
    }
}
