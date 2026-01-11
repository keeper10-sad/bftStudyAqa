package utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

public class CreateBooking {
    public static int createBookingId(){
        String requestBody = """
        {
          "firstname": "Andrey",
          "lastname": "Dorokhin",
          "totalprice": 10000,
          "depositpaid": true,
          "bookingdates": {
            "checkin": "2026-01-01",
            "checkout": "2026-03-10"
          },
          "additionalneeds": "Do not disturbed"
        }
        """;

        return given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .extract()
                .path("bookingid");
    }
}
