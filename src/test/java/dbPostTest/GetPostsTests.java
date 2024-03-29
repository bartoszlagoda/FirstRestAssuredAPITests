package dbPostTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetPostsTests {
    /*
     *  Given - warunek wstepny      Uzytkownik jest zalogowany
     *  When - wykonana akcja        Doda jeden produkt do koszyka
     *  Then - oczekiwany rezultat   Ilość produktów w koszyku powinna wzrosnąć o jeden
     * */

    @Test
    public void testGetAllPostsAndLogBody() {
        when()
                .get("http://localhost:3000/posts")
                .then()
                .log().body(); // zaloguj tylko body z response
    }

    @Test
    public void testGetAllPostsAndLogAll() {
        when()
                .get("http://localhost:3000/posts")
                .then()
                .log().all(); // zaloguj wszystkie informacje na temat requestu
    }

    @Test
    public void testGetAllPostsAndValidateStatusCode() {
        when()
                .get("http://localhost:3000/posts")
                .then()
                .log()
                .ifValidationFails()// zaloguj response tylko w razie błędu walidacji
                .statusCode(200); // walidacja sprawdzajaca kod odpowiedzi czy jest to 200
    }

    @Test
    public void testGetOnePostAndLogAll() {
        when()
                .get("http://localhost:3000/posts/1")
                .then()
                .log().all(); // zaloguj wszystkie informacje na temat requestu
    }

    @Test
    public void testGetPathParamPost(){
        given()
                .log()
                .all()
                .pathParam("postId", 1)
                .when()
                .get("http://localhost:3000/posts/{postId}")
                .then()
                .log()
                .all();
    }

    @Test
    public void testGetParamPost(){
        given()
                .log()
                .all()
                .when()
                .get("http://localhost:3000/posts/{postId}", 1)
                .then()
                .log()
                .all();
    }
}
