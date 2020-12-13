package day05;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.MethodOrderer.* ;
// these are all available option for ordering your tests
//@TestMethodOrder(OrderAnnotation.class)
//@TestMethodOrder(Random.class)
//@TestMethodOrder(MethodName.class) // default options
@TestMethodOrder(MethodOrderer.DisplayName.class)


public class TestOrderingInJunit5 {
    //by default the test is running on alphabetical order
    //or the test method name


    @Order(3)
    @DisplayName("3. Test A method")
    @Test
    public void testA() {
        System.out.println("Running test A ");
    }
    @Order(1)
    @DisplayName("1. Test C method")
    @Test
    public void testC() {
        System.out.println("Running test C ");
    }
    @Order(4)
    @DisplayName("4. Test D method")
    @Test
    public void testD() {
        System.out.println("Running test D ");
    }
    @Order(2)
    @DisplayName("2. Test B method")
    @Test
    public void testB() {
        System.out.println("Running test B ");
    }
}