package dbPostTests;

import io.restassured.http.ContentType;
import model.Post;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void addPostAsMapTest(){
//        java.lang.IllegalStateException: Cannot serialize object because no JSON serializer found in classpath.
//        Please put Jackson (Databind), Gson, Johnzon, or Yasson in the classpath

        Map<String,Object> newPost = new HashMap<>();
        newPost.put("title","Lekca 169: Dodanie nowego posta - ładowanie requestu body jako mapa");
        newPost.put("author","bartoszlagoda");

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
    public void addPostFromClassTest(){
        Post newPost = new Post();
        newPost.setTitle("Lekcja 178: Filtrowanie postów - query params");
        newPost.setAuthor("unknownauthor");

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
