package day01;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Day1 Hello B20")

public class HelloTest {
// Junit5 Annotations

    //BeforeAll , AfterAll, BeforeEach, AfterEach

    @BeforeAll
    public static void setUp(){
        System.out.println("BeforeAll is running");

    }

    @AfterAll
    public static void tearDown(){
        System.out.println("AfterAll is running");

    }

    @BeforeEach
    public  void setUpTest(){
        System.out.println("BeforeEach is running");

    }

    @AfterEach
    public  void tearDownTest(){
        System.out.println("AfterEach is running");

    }
    @DisplayName("Test 1+3=4")
    @Test

    public void test(){
        Assertions.assertEquals(4, 1+3);
        System.out.println("test1 is running ");

    }
    @Disabled
    @DisplayName("Test 4*3=12")
    @Test
    public void test2(){
        // assert 4times 3 is 12

        assertEquals(12, 4*3);
        System.out.println("test2 is running ");
    }
}
