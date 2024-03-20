package db2PostTest;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class ResponseAwareMatcherTest {
//Response Aware Matcher pozwala na pobieranie w locie danych z response
// przydatne gdy pole jest kombinacją jakichś innych pól
    @Test
    public void testGetPost(){

        when().
                get("http://localhost:3000/posts/{postId}", 1).
                then().body("specificField", new ResponseAwareMatcher<Response>() {
                    @Override
                    public Matcher<?> matcher(Response response) throws Exception {
                        return Matchers.equalTo("10192020" + response.path("secretString"));
                    }
                }).log().all();
    }

    @Test
    public void testGetPostWithLambda(){

        when().
                get("http://localhost:3000/posts/{postId}", 1).
                then().body("specificField", response -> Matchers.equalTo("10192020" + response.path("secretString"))).log().all();
    }
}
