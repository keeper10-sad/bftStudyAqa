package settings;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseClass {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
}
