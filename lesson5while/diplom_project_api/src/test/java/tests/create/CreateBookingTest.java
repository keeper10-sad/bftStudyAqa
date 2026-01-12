package tests.create;

import components.enums.InvalidData;
import components.enums.RequiredInputs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import settings.BaseClass;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;

public class CreateBookingTest extends BaseClass {
    @Tag("positive")
    @DisplayName("Успешное создание бронирования")
    @Test
    void successCreateBooking(){

        String requestBody = """
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "totalprice": 10000,
              "depositpaid": true,
              "bookingdates": { "checkin": "2025-12-01", "checkout": "2026-03-10" },
              "additionalneeds": "Do not disturbed"
            }
        """;
        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/booking")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .body("booking.firstname", equalTo("Andrey"))
                .body("booking.lastname", equalTo("Dorokhin"))
                .body("booking.bookingdates.checkin", equalTo("2025-12-01"))
                .body("booking.bookingdates.checkout", equalTo("2026-03-10"))
                .body("booking.totalprice", equalTo(10000))
                .body("booking.depositpaid", equalTo(true))
                .body("booking.additionalneeds", equalTo("Do not disturbed"))
                .body("bookingid", greaterThan(0))
                ;
    }

    @Tag("negative")
    @DisplayName("Создание бронирования без обязательных полей")
    @ParameterizedTest(name = "Создание без {0}")
    @EnumSource(RequiredInputs.class)
    void createBookingWithoutRequiredInputs(RequiredInputs requiredInputs){
        given()
                .contentType("application/json")
                .body(requiredInputs.getRequestBody())
                .when()
                .post("/booking")
                .then()
                .log().status()
                .log().body()
                .statusCode(anyOf(is(400), is(500)));
    }

    @Tag("negative")
    @DisplayName("Создание бронирования с невалидным форматом данных")
    @ParameterizedTest(name = "CREATE с невалидным {0}")
    @EnumSource(InvalidData.class)
    void createBookingWithInvalidData(InvalidData invalidData){
        given()
                .contentType("application/json")
                .body(invalidData.getRequestBody())
                .when()
                .post("/booking")
                .then()
                .log().status()
                .log().body()
                .statusCode(anyOf(is(400), is(500)));
    }
}
