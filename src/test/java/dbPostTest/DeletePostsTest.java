package dbPostTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class DeletePostsTest {

    @Test
    public void deletePost(){
        when()
                .delete("http://localhost:3000/posts/5")
                .then()
                .log()
                .all();
    }
}
