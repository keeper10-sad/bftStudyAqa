package tests.delete;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import settings.BaseClass;
import utils.CreateBooking;
import utils.CreateToken;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

public class DeleteBookingTest extends BaseClass {
    @Tag("positive")
    @DisplayName("Успешное удаление бронирования")
    @Test
    void successDeleteBooking(){
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
                .cookie("token", token)
                .when()
                .log().uri()
                .log().method()
                .delete("/booking/" + bookingid)
                .then()
                .log().status()
                .statusCode(201);

        given()
                .when()
                .log().uri()
                .log().method()
                .get("/booking/" + bookingid)
                .then()
                .log().status()
                .statusCode(404);
    }

    @Tag("negative")
    @DisplayName("Удаление без авторизации")
    @Test
    void deleteBookingWithoutAuth(){
        //String token = CreateToken.createToken();
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
                //.cookie("token", token)
                .when()
                .log().uri()
                .log().method()
                .delete("/booking/" + bookingid)
                .then()
                .log().status()
                .statusCode(403);
    }

    @Tag("negative")
    @DisplayName("Удаление несуществующих ID")
    @ParameterizedTest(name = "Невалидный id:{0}")
    @ValueSource(strings = {"", "iryuiru", "^%$%&", " ", "90876", "-101"})
    void deleteBookingWithInvalidIds(String invalidIds){
        String token = CreateToken.createToken();

        given()
                .cookie("token", token)
                .when()
                .log().uri()
                .log().method()
                .delete("/booking/" + invalidIds)
                .then()
                .log().status()
                .statusCode(anyOf(is(404), is(405)));
    }
}
