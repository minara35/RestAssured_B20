package day04;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;

public class OpenMovieDB_Test {

    //http://www.omdbapi.com/?t=See&apikey=d7cbe9cb
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://www.omdbapi.com/?t=See&apikey=d7cbe9cb";

    }

    @AfterAll
    public static void tearDown(){
        reset();
    }



    @DisplayName("Test Search Movie or OpenMovieDB Test")
    @Test
    public void testMovie(){
        given()
                .queryParam("apikey", "d7cbe9cb")
                .queryParam("t", "See").
        when()
                .get().prettyPeek(). // our request url is already complete do not need to add anything
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("Title",is("See"))
                .body("Ratings[0].Source", is ("Internet Movie Database"))
                ;
    }

    @DisplayName("Getting the log of request and response")
    @Test
    public void testSendingRequestAndGetTheLog(){
        given()
                .queryParam("apikey", "d7cbe9cb")
                .queryParam("t", "John Wick")
                //loging the request should be in given section
              .log().all().
                //                .log().uri().
//                  .log().params().
        when()
                .get().
                then()
                //logging the response should be in then section
        .log().all()
                //                .log().all()
//                .log().status()
//                .log().body()
                .log().ifValidationFails()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("Plot",containsString("ex-hit-man"))
                //second Ratings source is Rotten Tomatoes
                .body("Ratings[1].Source", is ("Rotten Tomatoes"));



    }
}
