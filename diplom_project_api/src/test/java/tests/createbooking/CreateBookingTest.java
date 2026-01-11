package tests.createbooking;

import components.enums.BodyWithInvalidDate;
import components.enums.NotValidData;
import components.enums.NotValidDate;
import components.enums.RequiredInputs;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import settings.BaseClass;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateBookingTest extends BaseClass {
    @Tag("positive")
    @DisplayName("Создание бронирования")
    @Test
    void createBookingSuccessGivenId(){
        String requestBody = """
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "totalprice": 10000,
              "depositpaid": true,
              "bookingdates": { "checkin": "2026-01-01", "checkout": "2026-03-10" },
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
                .body("bookingid", greaterThan(0))
                .body("booking.firstname", equalTo("Andrey"))
                .body("booking.lastname", equalTo("Dorokhin"))
                .body("booking.bookingdates.checkin", equalTo("2026-01-01"))
                .body("booking.bookingdates.checkout", equalTo("2026-03-10"))
                .body("booking.totalprice", equalTo(10000))
                .body("booking.depositpaid", equalTo(true))
                .body("booking.additionalneeds", equalTo("Do not disturbed"))
                ;
        ;
    }

    @Tag("negative")
    @DisplayName("Создание бронирования без заполнения обязательных полей")
    @ParameterizedTest(name = "POST без {0}")
    @EnumSource(RequiredInputs.class)

    void createBookingWithoutRequiredInput(RequiredInputs requiredInputs){

        given()
                .contentType("application/json")
                .body(requiredInputs.getRequestBody())
                .when()
                .log().uri()
                .post("/booking")
                .then()
                .log().status()
                .log().body()
                .statusCode(anyOf(is(400), is(500)))
        ;
    }

    @Disabled //валидация данных дат отсутствует, бронирование создается с любыми датами.Тест написан как демонстрация того как бы я реализовал проверку
    @Tag("negative")
    @DisplayName("POST с некорректными датами")
    @ParameterizedTest(name = "POST с невалидной датой {0}")
    @EnumSource(BodyWithInvalidDate.class)
    void createBookingWithInvalidDates(BodyWithInvalidDate bodyWithInvalidDate){
        given()
                .contentType("application/json")
                .body(bodyWithInvalidDate.getRequestBody())
                .when()
                .log().uri()
                .post("/booking")
                .then()
                .log().status()
                .log().body()
                .statusCode(anyOf(is(400), is(500)));
    }

    @Tag("negative")
    @DisplayName("Создание бронирования с невалидным форматом данных")
    @ParameterizedTest(name = "POST с неверным форматом данных {0}")
    @EnumSource(NotValidData.class)
    void createBookingWithNotValidData(NotValidData notValidData){
        given()
                .contentType("application/json")
                .body(notValidData.getRequestBody())
                .when()
                .log().uri()
                .log().body()
                .post("/booking")
                .then()
                .log().status()
                .log().body()
                .statusCode(anyOf(is(400), is(500)));
    }
}
