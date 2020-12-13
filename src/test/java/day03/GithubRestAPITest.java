package day03;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;

public class GithubRestAPITest {
    // create a test for testing gitHub rest api users/user endpoint
    @DisplayName("Test Github GET /users/{username}")
    @Test
    public void testGitHub(){
        // provide your username as path variable in given section of the chain
        given()
                .pathParam("username", "minara35").
        when()
                .get("https://api.github.com/users/{username}").

                then()
                .assertThat()
                .statusCode(is (200))
        .contentType(ContentType.JSON)
        .header("server", "GitHub.com")
        .body("login", is("minara35"))
                .body("id", is(69086518))

        ;
    }



}
