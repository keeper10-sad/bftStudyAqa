package tests.update;

import components.enums.UpdateInvalidData;
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
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

public class PartialUpdateBookingTest extends BaseClass {
    @Tag("positive")
    @DisplayName("Успешное частичное обновление бронирования") // обновляю общую сумму и дату выселения
    @Test
    void successPartialUpdateBooking(){
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
            "totalprice": 5000,
            "bookingdates": {
                "checkin": "2025-12-01",
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
                .body("bookingdates.checkin", equalTo("2025-12-01"))
                .body("bookingdates.checkout", equalTo("2026-02-10"))
                .body("totalprice", equalTo(5000))
                .body("depositpaid", equalTo(true))
                .body("additionalneeds", equalTo("Do not disturbed"))
        ;
    }

    @Tag("positive")
    @DisplayName("Проверка отсутствия валидации заполнения обязательных полей") // Для метода все поля необязательные
    @Test
    void partialUpdateBookingWithEmptyBody(){
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
    @DisplayName("Обновление без авторизации")
    @Test
    void partialUpdateBookingWithoutAuth(){
        //String token = CreateToken.createToken();
        int bookingid = CreateBooking.createBookingId();

        String updateBody = """
        {
            "totalprice": 5000,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2025-12-01",
                "checkout": "2026-01-10"
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
                .patch("/booking/" + bookingid)
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
    void partialUpdateBookingWithInvalidId(String invalidIds){
        String token = CreateToken.createToken();
        int bookingid = CreateBooking.createBookingId();

        String updateBody = """
        {
            "totalprice": 5000,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2025-12-01",
                "checkout": "2026-01-10"
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
                .patch("/booking/" + invalidIds)
                .then()
                .log().status()
                .log().body()
                .statusCode(anyOf(is(404), is(405)));
        ;
    }

    @Tag("negative")
    @DisplayName("Обновление невалидными форматами данных")
    @ParameterizedTest(name = "PUT с неверным фоматом данных {0}")
    @EnumSource(UpdateInvalidData.class)
    void partialUpdateBookingWithInvalidData(UpdateInvalidData updateInvalidData){
        String token = CreateToken.createToken();
        int bookingid = CreateBooking.createBookingId();

        given()
                .contentType("application/json")
                .cookie("token", token)
                .body(updateInvalidData.getUpdateBody())
                .when()
                .log().uri()
                .log().body()
                .patch("/booking/" + bookingid)
                .then()
                .log().status()
                .log().body()
                .statusCode(400);
        ;
    }
}
