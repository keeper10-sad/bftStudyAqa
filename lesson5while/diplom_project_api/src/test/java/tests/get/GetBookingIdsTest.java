package tests.get;

import components.enums.InvalidDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import settings.BaseClass;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

public class GetBookingIdsTest extends BaseClass {

    @Tag("positive")
    @DisplayName("Успешеый запрос списка бронирований")
    @Test
    void shouldReturnBookingIds() {
        given()
                .when()
                .log().uri()
                .get("/booking")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("$", not(empty())) // проверяем, что ответ в виде массива и он не пустой
                .body("bookingid", everyItem(notNullValue())) // проверка, что у всех объектов есть id
                .body("booking", everyItem(instanceOf(Integer.class))) //проверка типа данных у id
                .body("bookingid", greaterThan(0)) //проверка, что id не отрицательное число
        ;
    }

    @Tag("positive")
    @DisplayName("Фильтрация по имени")
    @Test
    void getBookingIds_filteredByName_shouldReturnCorrectBookings() {

        int bookingId =
                given()
                        .queryParam("firstname", "Andrey")
                        .queryParam("lastname", "Dorokhin")
                        .when()
                        .get("/booking")
                        .then()
                        .statusCode(200)
                        .body("$", not(empty()))
                        .extract()
                        .path("bookingid[0]");

        given()
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Andrey"))
                .body("lastname", equalTo("Dorokhin"));
    }

    @Disabled
    @Tag("positive")
    @DisplayName("Фильтрация по дате")
    @Test
    void getBookingIds_filteredByDates_shouldReturnBookingsInRange() {

        int bookingId =
                given()
                        .queryParam("checkin", "2019-01-01")
                        .queryParam("checkout", "2019-01-01")
                        .when()
                        .log().uri()
                        .get("/booking")
                        .then()
                        .log().body()
                        .statusCode(200)
                        .body("$", not(empty()))
                        .extract()
                        .path("bookingid[0]");

        given()
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("bookingdates.checkin", equalTo("2019-01-01"))
                .body("bookingdates.checkout", equalTo("2019-01-01"));
    }

    @Tag("negative")
    @DisplayName("Проверяем фильтрацию по несуществующему имени")
    @Test
    void getBookingIdsFilteringNotExistName() {
        given()
                .queryParam("firstname", "PKLKL")
                .queryParam("lastname", "4jknv,")
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .body("$", empty());
    }

    static Stream<InvalidDate> invalidDateProvider() {
        return Stream.of(InvalidDate.values());
    }
    @Tag("negative")
    @DisplayName("Проверяем фильтрацию с невалидными датами")
    @ParameterizedTest(name = "Фильтрация с невалидной датой {0}")
    @MethodSource("invalidDateProvider")
    void getBookingIdsFilteringNotValidDate(InvalidDate invalidDate) {

        String checkin = invalidDate.getCheckin();
        String checkout = invalidDate.getCheckout();

            given()
                    .queryParam("checkin", checkin)
                    .queryParam("checkout", checkout)
                    .when()
                    .log().uri()
                    .get("/booking")// путь к которому добавляются параметры для фильтрации
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(200) //проверка статуса ответа
                    .body("$", empty());
            }

    @Tag("negative")
    @DisplayName("Проверяем фильтрацию с невалидными датами")
    @ParameterizedTest(name = "Проверка невалидного bookingId: {0}")
    @ValueSource(strings = {"0", "-1", "999999", "*^%&**)", "hehrh"}) // набор невалидных ID
    void getBookingByInvalidId_shouldReturn404(String invalidId) {
        given()
                .when()
                .log().uri()
                .get("/booking/" + invalidId)
                .then()
                .log().status()
                .log().body()
                .statusCode(404); }
        }