package dbPostTests;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class ResponseTimeTests {

    @Test
    public void getPostTest() {

        long responseTime = given().log().all().when()
                .get("http://localhost:3000/posts/{postId}", 1).
                timeIn(TimeUnit.MILLISECONDS);
        Assertions.assertTrue(responseTime < 1000, "Czas odpowiedzi wyniósł: " + responseTime);
    }

    @Test
    public void getPostInLessThan500msTest() {

        given().log().all().when()
                .get("http://localhost:3000/posts/{postId}", 1).
                then().time(Matchers.lessThan(1000L), TimeUnit.MILLISECONDS).onFailMessage("Czas odpowiedzi był większy niż 1000 ms").log().all();
    }
}
