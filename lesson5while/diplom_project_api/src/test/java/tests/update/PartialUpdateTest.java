package tests.update;

import components.enums.InvalidData;
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
import static org.hamcrest.CoreMatchers.is;

public class PartialUpdateTest extends BaseClass {
    @Tag("positive")
    @DisplayName("Успешное частичное обновление бронирования") //Обновляю только сумму и дату заселения
    @Test
     void successPartialUpdateBooking(){
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
            "totalprice": 7500,
            "bookingdates": {
                "checkin": "2025-12-20",
                "checkout": "2026-02-10"
            }
        }
        """;
        given()
                .contentType("application/json")
                .cookie("token", token)
                .body(updateBody)
                .when()
                .log().uri()
                .log().method()
                .patch("/booking/" + bookingid)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("firstname", equalTo("Andrey"))
                .body("lastname", equalTo("Dorokhin"))
                .body("bookingdates.checkin", equalTo("2025-12-20"))
                .body("bookingdates.checkout", equalTo("2026-02-10"))
                .body("totalprice", equalTo(7500))
                .body("depositpaid", equalTo(true))
                .body("additionalneeds", equalTo("Do not disturbed"))
        ;
    }

    @Tag("positive")
    @DisplayName("Проверка отсутствия валидации заполнения обязательных полей") //Для метода нет обязательных полей
    @Test
    void PartialUpdateBookingWithEmptyBody(){
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
                .body("{}")
                .when()
                .log().uri()
                .log().method()
                .patch("/booking/" + bookingid)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("firstname", equalTo("Andrey"))
                .body("lastname", equalTo("Dorokhin"))
                .body("bookingdates.checkin", equalTo("2026-01-01"))
                .body("bookingdates.checkout", equalTo("2026-03-10"))
                .body("totalprice", equalTo(10000))
                .body("depositpaid", equalTo(true))
                .body("additionalneeds", equalTo("Do not disturbed"))
        ;
    }

    @Tag("negative")
    @DisplayName("Частичное обнолвение бронирования без авторизации")
    @Test
    void partialUpdateBookingWithoutAuth(){
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
            "totalprice": 5000,
            "bookingdates": {
                "checkin": "2025-12-01",
                "checkout": "2026-02-10"
            }
        }
        """;
        given()
                .contentType("application/json")
                // .cookie("token", token)
                .body(updateBody)
                .when()
                .log().uri()
                .log().method()
                .patch("/booking/" + bookingid)
                .then()
                .log().status()
                .log().body()
                .statusCode(403)
        ;
    }

    @Tag("negative")
    @DisplayName("Частичное обновление бронирования с невалидными id")
    @ParameterizedTest(name = "PATCH с невалидным id = {0}")
    @ValueSource(strings = {"-101", "768905", "", " ", "(&(("})
    void partialUpdateBookingNonExistId(String nonExistId){
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
                .patch("/booking/" + nonExistId)
                .then()
                .log().status()
                .log().body()
                .statusCode(anyOf(is(405), is(404)));
    }

    @Tag("negative")
    @DisplayName("Частичное обновление бронирования с невалидным форматом данных")
    @ParameterizedTest(name = "PATCH с невалидным {0}")
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
