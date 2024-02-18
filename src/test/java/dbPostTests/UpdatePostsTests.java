package dbPostTests;

import io.restassured.http.ContentType;
import model.Post;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdatePostsTests {

    @Test
    public void updatePostTest(){
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

    @Test
    public void updateTitlePostTest(){
        Post newPost = new Post();
        newPost.setTitle("Lekcja 171: Dodanie nowego posta - klasa Post (UPD: Lekcja 174)");

        given()
                .log().all() // wypisanie logów requesta
                .contentType(ContentType.JSON) // ustawienie jsonowego kontentu
                .body(newPost) // ustalenie tego co bedzie wysylane
                .when()
                .put("http://localhost:3000/posts/5")// PUT służy do aktualizacji całego zasobu
                .then()
                .log().all(); // wyświetlenie logów response
    }

    @Test
    public void patchTitlePostTest(){
        Post newPost = new Post();
        newPost.setAuthor("bartoszlagoda");

        given()
                .log().all() // wypisanie logów requesta
                .contentType(ContentType.JSON) // ustawienie jsonowego kontentu
                .body(newPost) // ustalenie tego co bedzie wysylane
                .when()
                .patch("http://localhost:3000/posts/5")// PUT służy do aktualizacji całego zasobu
                .then()
                .log().all(); // wyświetlenie logów response
    }


}
