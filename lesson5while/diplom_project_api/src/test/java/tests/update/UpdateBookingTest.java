package tests.update;

import components.enums.InvalidData;
import components.enums.RequiredInputs;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import settings.BaseClass;
import utils.Auth;
import utils.GetId;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;


public class UpdateBookingTest extends BaseClass {
    @Tag("positive")
    @DisplayName("Успешное обновление бронирования")
    @Test
    void successUpdateBooking(){
        int bookingid = GetId.createBookingId();
        String token = Auth.getToken();

        Response bookingBeforeUpdate =
                given()
                        .when()
                        .get("/booking/" + bookingid)
                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract()
                        .response();

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
                .log().method()
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
    @DisplayName("Обнолвение бронирования без авторизации")
    @Test
    void updateBookingWithoutAuth(){
        int bookingid = GetId.createBookingId();
       // String token = Auth.getToken();

        Response bookingBeforeUpdate =
                given()
                        .when()
                        .get("/booking/" + bookingid)
                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract()
                        .response();

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
               // .cookie("token", token)
                .body(updateBody)
                .when()
                .log().uri()
                .log().method()
                .put("/booking/" + bookingid)
                .then()
                .log().status()
                .log().body()
                .statusCode(403)
        ;
    }

    @Tag("negative")
    @DisplayName("Обнолвение бронирования с невалидными id")
    @ParameterizedTest(name = "PUT с невалидным id = {0}")
    @ValueSource(strings = {"-101", "768905", "", " ", "(&(("})
    void updateBookingNonExistId(String nonExistId){
        String token = Auth.getToken();

        String updateBody = """
    {
        "firstname": "Test",
        "lastname": "User",
        "totalprice": 100,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2026-01-01",
            "checkout": "2026-01-10"
        },
        "additionalneeds": "None"
    }
    """;

        given()
                .contentType("application/json")
                .cookie("token", token)
                .body(updateBody)
                .when()
                .log().uri()
                .log().method()
                .put("/booking/" + nonExistId)
                .then()
                .log().status()
                .log().body()
                .statusCode(anyOf(is(405), is(404)));
    }


    @Tag("negative")
    @DisplayName("Обнолвение бронирования без обязательных полей")
    @ParameterizedTest(name = "Обновление без {0}")
    @EnumSource(RequiredInputs.class)
    void updateBookingWithoutRequiredInputs(RequiredInputs requiredInputs){
        int bookingid = GetId.createBookingId();
        String token = Auth.getToken();

        Response bookingBeforeUpdate =
                given()
                        .when()
                        .get("/booking/" + bookingid)
                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract()
                        .response();

        given()
                .contentType("application/json")
                .cookie("token", token)
                .body(requiredInputs.getRequestBody())
                .when()
                .log().uri()
                .log().method()
                .put("/booking/" + bookingid)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
        ;
    }

    @Tag("negative")
    @DisplayName("Создание бронирования с невалидным форматом данных")
    @ParameterizedTest(name = "PUT с невалидным {0}")
    @EnumSource(InvalidData.class)
    void updateBookingWithInvalidData(InvalidData invalidData){
        int bookingid = GetId.createBookingId();
        String token = Auth.getToken();

        Response bookingBeforeUpdate =
                given()
                        .when()
                        .get("/booking/" + bookingid)
                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract()
                        .response();

        given()
                .contentType("application/json")
                .cookie("token", token)
                .body(invalidData.getRequestBody())
                .when()
                .log().uri()
                .log().method()
                .put("/booking/" + bookingid)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
        ;
    }
}
