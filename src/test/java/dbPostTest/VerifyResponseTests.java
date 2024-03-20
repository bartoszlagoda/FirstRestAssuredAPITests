package dbPostTest;

import io.restassured.http.ContentType;
import model.Post;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
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

    @Test
    public void getPostObjectTest(){

        Post newPost = given()
                .log()
                .all()
                .when()
                .get("http://localhost:3000/posts/{postId}", 1)
                .then()
                .log()
                .all()
                .extract().as(Post.class);

        Assertions.assertAll(
                () -> Assertions.assertEquals(newPost.getAuthor(), "bartoszlagoda"),
                () -> Assertions.assertEquals(newPost.getTitle(), "Lekcja 172: Nadpisywanie istniejącego posta"),
                () -> Assertions.assertEquals(newPost.getId(), 1)
        );
    }

    @Test
    public void addPostObjectTest(){
        Post newPost = new Post();
        newPost.setTitle("Lekcja 184: Weryfikacja ciała odpowiedzi - porównanie obiektów");
        newPost.setAuthor("Jan Kowalski");

        Post createdPost = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(newPost)
                .when()
                .post("http://localhost:3000/posts")
                .then()
                .log().all()
                .extract().body().as(Post.class);

        Assertions.assertEquals(newPost,createdPost);
        Assertions.assertEquals(createdPost.getTitle(),"Lekcja 184: Weryfikacja ciała odpowiedzi - porównanie obiektów");
        Assertions.assertEquals(createdPost.getAuthor(),"Jan Kowalski");
    }
}
