package dbPostTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class DeletePostsTests {

    @Test
    public void testDeletePost(){
        when()
                .delete("http://localhost:3000/posts/5")
                .then()
                .log()
                .all();
    }
}
