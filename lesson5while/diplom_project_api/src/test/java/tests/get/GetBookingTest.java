package tests.get;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import settings.BaseClass;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

public class GetBookingTest extends BaseClass {
    @Tag("negative")
    @DisplayName("Проверяем фильтрацию с невалидными датами")
    @Test
    void getBookingById() {
        given()
                .when()
                .log().uri()
                .get("/booking/1955")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("firstname", notNullValue())
                .body("lastname", notNullValue())
                .body("bookingdates.checkin", notNullValue())
                .body("bookingdates.checkout", notNullValue());
    }
}
