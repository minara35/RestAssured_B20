package day02;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Spartan_CRUD_Operation {

    private static int newSpartanID; //to be able to use variable inside another test

    @DisplayName("Setting up environment")
    @BeforeAll
    public static void setUp(){
        baseURI = "http://your url here";
        basePath = "/api" ;
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @Order(1)
    @DisplayName("Testing POST /api/spartans end point create an spartan")
    @Test()
    public void createSpartan(){
        Faker faker  = new Faker();

        Response response = given()
                .body("{\n" +
                        "  \"name\": \""+faker.name().firstName()+"\",\n" +
                        "  \"gender\": \"Female\",\n" +
                        "  \"phone\": "+faker.phoneNumber().subscriberNumber(10)+"\n" +
                        "}")
                .contentType(ContentType.JSON).
                        when()
                .post("/spartans");

        JsonPath responseJson = response.jsonPath();

        newSpartanID = responseJson.getInt("data.id");

        response.
                then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"));

        response.prettyPrint();

    }

    @Order(2)
    @DisplayName("Testing GET /api/spartans/{id} end point JSON Response")
    @Test
    public void getOneSpartan(){
        given()
                .accept(ContentType.JSON).
                when()
                .get("/spartans/{id}", newSpartanID).
                then()
                .assertThat()
                .statusCode( is(200) )
                .contentType(ContentType.JSON);

    }

    @Order(3)
    @DisplayName("Testing PATCH /api/spartans/{id} end point update spartan")
    @Test
    public void updateSpartan(){

        given()
                .header("Content-Type", ContentType.JSON)
                .body("{\"name\":\"Tucky\"}")
                .when()
                .patch("/spartans/{id}", newSpartanID)
                .then()
                .statusCode(204);
    }


    @Order(4)
    @DisplayName("Testing GET /api/spartans/{id} end point for updated spartan")
    @Test
    public void getUpdatedSpartan(){

        Response response = given()
                .accept(ContentType.JSON).
                        when()
                .get("/spartans/{id}", newSpartanID);

        String newName = response.path("name");

        assertThat(newName, is ("Tucky"));

    }


    @Order(5)
    @DisplayName("Testing DELETE /api/spartans/{id} end point ")
    @Test
    public void deleteSpartan(){

        given()
                .header("Content-Type", ContentType.JSON)
                .when()
                .delete("/spartans/{id}", newSpartanID)
                .then()
                .statusCode(204);

    }
    @Order(6)
    @DisplayName("Testing GET /api/spartans/{id} end point for updated spartan")
    @Test
    public void getDeletedSpartan() {

        given()
                .accept(ContentType.JSON).
                when()
                .get("/spartans/{id}", newSpartanID).
                then()
                .statusCode(404)
                .body("error", is("Not Found"));
    }
}
