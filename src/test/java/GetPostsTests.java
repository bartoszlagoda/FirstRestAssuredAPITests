import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class GetPostsTests {
    /*
     *  Given - warunek wstepny      Uzytkownik jest zalogowany
     *  When - wykonana akcja        Doda jeden produkt do koszyka
     *  Then - oczekiwany rezultat   Ilość produktów w koszyku powinna wzrosnąć o jeden
     * */

    @Test
    public void getAllPostsAndLogBodyTest() {
        when()
                .get("http://localhost:3000/posts")
                .then()
                .log().body(); // zaloguj tylko body z response
    }

    @Test
    public void getAllPostsAndLogAllTest() {
        when()
                .get("http://localhost:3000/posts")
                .then()
                .log().all(); // zaloguj wszystkie informacje na temat requestu
    }

    @Test
    public void getAllPostsAndValidateStatusCodeTest() {
        when()
                .get("http://localhost:3000/posts")
                .then()
                .log()
                .ifValidationFails()// zaloguj response tylko w razie błędu walidacji
                .statusCode(200); // walidacja sprawdzajaca kod odpowiedzi czy jest to 200
    }

    @Test
    public void getOnePostAndLogAllTest() {
        when()
                .get("http://localhost:3000/posts/1")
                .then()
                .log().all(); // zaloguj wszystkie informacje na temat requestu
    }
}
