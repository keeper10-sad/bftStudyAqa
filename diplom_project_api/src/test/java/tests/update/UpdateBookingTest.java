package tests.update;

import components.enums.UpdateInvalidData;
import components.enums.UpdateRequredInputs;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import settings.BaseClass;
import utils.CreateBooking;
import utils.CreateToken;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class UpdateBookingTest extends BaseClass {
    @Tag("positive")
    @DisplayName("Успешное обновление бронирования")
    @Test
    void successUpdateBooking(){
        String token = CreateToken.createToken();
        int bookingid = CreateBooking.createBookingId();

        Response bookingAfterCreate =
                given()
                        .when()
                        .get("/booking/" + bookingid)
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

        String updateBody = """
        {
            "firstname": "Andrey",
            "lastname": "Dorokhin_20",
            "totalprice": 5000,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2025-12-01",
                "checkout": "2026-02-10"
            },
            "additionalneeds": "Dinner"
        }
        """;
        given()
                .contentType("application/json")
                .cookie("token", token)
                .body(updateBody)
                .when()
                .log().uri()
                .put("/booking/" + bookingid)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("firstname", equalTo("Andrey"))
                .body("lastname", equalTo("Dorokhin_20"))
                .body("bookingdates.checkin", equalTo("2025-12-01"))
                .body("bookingdates.checkout", equalTo("2026-02-10"))
                .body("totalprice", equalTo(5000))
                .body("depositpaid", equalTo(false))
                .body("additionalneeds", equalTo("Dinner"))
        ;
    }

    @Tag("negative")
    @DisplayName("Обновление без авторизации")
    @Test
    void updateBookingWithoutAuth(){
        //String token = CreateToken.createToken();
        int bookingid = CreateBooking.createBookingId();

        String updateBody = """
        {
            "firstname": "Andrey",
            "lastname": "Dorokhin_20",
            "totalprice": 5000,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2025-12-01",
                "checkout": "2026-02-10"
            },
            "additionalneeds": "Dinner"
        }
        """;
        given()
                .contentType("application/json")
                //.cookie("token", token)
                .body(updateBody)
                .when()
                .log().uri()
                .put("/booking/" + bookingid)
                .then()
                .log().status()
                .log().body()
                .statusCode(403)
        ;
    }

    @Tag("negative")
    @DisplayName("Обновление невалидного или несуществующего id")
    @ParameterizedTest(name = "Невалидный id:{0}")
    @ValueSource(strings = {"", "iryuiru", "^%$%&", " ", "90876", "-101"})
    void updateBookingWithInvalidId(String invalidIds){
        String token = CreateToken.createToken();
        int bookingid = CreateBooking.createBookingId();

        String updateBody = """
        {
            "firstname": "Andrey",
            "lastname": "Dorokhin_20",
            "totalprice": 5000,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2025-12-01",
                "checkout": "2026-02-10"
            },
            "additionalneeds": "Dinner"
        }
        """;
        given()
                .contentType("application/json")
                .cookie("token", token)
                .body(updateBody)
                .when()
                .log().uri()
                .put("/booking/" + invalidIds)
                .then()
                .log().status()
                .log().body()
                .statusCode(anyOf(is(404), is(405)));
        ;
    }

    @Tag("negative")
    @DisplayName("PUT без обязательных полей")
    @ParameterizedTest(name = "Обновление без {0}")
    @EnumSource(UpdateRequredInputs.class)
    void updateBookingWithoutRequiredInputs(UpdateRequredInputs updateRequredInputs){
        String token = CreateToken.createToken();
        int bookingid = CreateBooking.createBookingId();

        Response bookingAfterCreate =
                given()
                        .when()
                        .get("/booking/" + bookingid)
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

        given()
                .contentType("application/json")
                .cookie("token", token)
                .body(updateRequredInputs.getUpdateBody())
                .when()
                .log().uri()
                .put("/booking/" + bookingid)
                .then()
                .log().status()
                .log().body()
                .statusCode(anyOf(is(400), is(500)))
        ;
    }

    @Tag("negative")
    @DisplayName("Обновление невалидными форматами данных")
    @ParameterizedTest(name = "PUT с неверным фоматом данных {0}")
    @EnumSource(UpdateInvalidData.class)
    void updateBookingWithInvalidData(UpdateInvalidData updateInvalidData){
        String token = CreateToken.createToken();
        int bookingid = CreateBooking.createBookingId();

        given()
                .contentType("application/json")
                .cookie("token", token)
                .body(updateInvalidData.getUpdateBody())
                .when()
                .log().uri()
                .log().body()
                .put("/booking/" + bookingid)
                .then()
                .log().status()
                .log().body()
                .statusCode(400);
        ;
    }
}
