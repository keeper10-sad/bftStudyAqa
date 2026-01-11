package settings;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.BeforeClass;

public class BaseClass {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
}
