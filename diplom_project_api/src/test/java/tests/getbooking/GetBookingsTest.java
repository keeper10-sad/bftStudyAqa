package tests.getbooking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import settings.BaseClass;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

public class GetBookingsTest extends BaseClass {

    @Tag("positive")
    @DisplayName("Возврат конкретного бронирования по id")
    @Test
    void getBookingsGivenBookings(){
        given()
                .when()
                .log().uri()
                .get("/booking/806")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("firstname", notNullValue())
                .body("lastname", notNullValue())
                .body("bookingdates.checkin", notNullValue())
                .body("bookingdates.checkout", notNullValue());
    }

    @Tag("negative")
    @DisplayName("Запрос бронирования по несуществующему id")
    @ParameterizedTest(name = "Невалидные id:{0}")
    @ValueSource( strings = {"0", "-10", "%^&&*", "1000000000000", " %111%", "abcde" })
    void getBookingsintsByNotExistId(String notExistId){
        given()
                .when()
                .log().uri()
                .get("/booking/" + notExistId)
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }
}