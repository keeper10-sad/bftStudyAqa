package tests.delete;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import settings.BaseClass;
import utils.Auth;
import utils.GetId;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;

public class DeleteBookingTest extends BaseClass {
    @Tag("positive")
    @DisplayName("Успешное удаление бронирования")
    @Test
    void successDeleteBooking() {
        int bookingId = GetId.createBookingId();
        String token = Auth.getToken();

        given()
                .when()
                .log().uri()
                .log().method()
                .get("/booking/" + bookingId)
                .then()
                .log().body()
                .statusCode(200);

        given()
                .cookie("token", token)
                .when()
                .log().method()
                .log().uri()
                .delete("/booking/" + bookingId)
                .then()
                .log().status()
                .statusCode(201); // Heroku API возвращает 201 при успешном delete

        given()
                .when()
                .log().method()
                .log().uri()
                .get("/booking/" + bookingId)
                .then()
                .log().status()
                .statusCode(404);
    }

    @Tag("negative")
    @DisplayName("Удаление бронирования без авторизации")
    @Test
    void deleteBookingWithoutAuth() {
        int bookingId = GetId.createBookingId();
        //String token = Auth.getToken();

        given()
                .when()
                .log().uri()
                .log().method()
                .get("/booking/" + bookingId)
                .then()
                .log().body()
                .statusCode(200);

        given()
                //.cookie("token", token)
                .when()
                .log().method()
                .log().uri()
                .delete("/booking/" + bookingId)
                .then()
                .log().status()
                .statusCode(403); // Heroku API возвращает 201 при успешном delete
    }

    @Tag("negative")
    @DisplayName("Удаление бронирования с невалидными id")
    @ParameterizedTest(name = " DELETE с невалидным id = {0}")
    @ValueSource(strings = {"-101", "768905", "", " ", "(&(("})
    void deleteBookingWithInvalidId(String notInvalidId){
        int bookingId = GetId.createBookingId();
        String token = Auth.getToken();

        given()
                .cookie("token", token)
                .when()
                .log().method()
                .log().uri()
                .delete("/booking/" + notInvalidId)
                .then()
                .log().status()
                .statusCode(anyOf(is(404), is(405)));
    }
}
