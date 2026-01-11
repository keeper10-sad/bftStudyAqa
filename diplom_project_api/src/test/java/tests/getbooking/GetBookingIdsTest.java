package tests.getbooking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import settings.BaseClass;
import components.enums.NotValidDate;

import java.util.stream.Stream;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetBookingIdsTest extends BaseClass {

    @Tag("positive")
    @DisplayName("приходит список бронирований")
    @Test
    void getBookingIdsGivenList() {
        given()
                .log().uri()
                .when()
                .get("/booking")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("$", not(empty())) // проверяем, что ответ в виде массива и он не пустой
                .body("bookingid", everyItem(notNullValue())) // проверка, что у всех объектов есть id
                .body("bookingid", everyItem(instanceOf(Integer.class))) //проверка типа данных у id
                .body("bookingid", everyItem(greaterThan(0))) //проверка, что id не отрицательное число
        ;
    }

    @Tag("positive")
    @DisplayName("Фильтрация по имени")
    @Test
    void getBookingIdsFilteringByName() {
        int bookingId = //объявляем переменну, куда будем сохранять полученные айди и подставлять в метод, для получения ингформации по этому id
                given()
                        .queryParam("firstname", "Andrey") //Параметры по которым происходит фильтрация
                        .queryParam("lastname", "Dorokhin")
                        .when()
                        .get("/booking")// путь к которому добавляются параметры для фильтрации
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200) //проверка статуса ответа
                        .body("$", not(empty())) // проверка что ответ не пустой
                        .extract()
                        .path("bookingid[0]"); // извлечение id в переменную bookingId начиная с первого элемента в массиве

        given() //второй запрос, чтобы проверить, что отфильтровались не случайные id
                .when()
                //.log().uri()
                .get("/booking/" + bookingId) // к урлу добавляется отфильтрованный id
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("firstname", equalTo("Andrey"))
                .body("lastname", equalTo("Dorokhin"));
    }

    @Disabled
    @Tag("positive")
    @DisplayName("Фильтрация по датам")
    @Test //фильтрация по дате не работает корректно, срабатывает только по конкретному id бронирования
    void getBookingIdsFilteringByDate() {
                given()
                        .queryParam("checkin", "2026-01-01") //Параметры по которым происходит фильтрация
                        .queryParam("checkout", "2026-03-10")
                        .when()
                        .get("/booking/806")// путь к которому добавляются параметры для фильтрации
                        .then()
                        //.log().status()
                        //.log().body()
                        .statusCode(200) //проверка статуса ответа
                        .body("bookingdates.checkin", equalTo("2026-01-01"))
                        .body("bookingdates.checkout", equalTo("2026-03-10"));
    }

    @Disabled
    @Tag("positive")
    @DisplayName("Фильтрация по имени и датам")
    @Test //Совместная фильтрация не работает
    void getBookingIdsFilteringByNameAndDate() {
        int bookingId = //объявляем переменну, куда будем сохранять полученные айди и подставлять в метод, для получения ингформации по этому id
                given()
                        .queryParam("firstname", "Andrey")
                        .queryParam("lastname", "Dorokhin")
                        .queryParam("checkin", "2026-01-01") //Параметры по которым происходит фильтрация
                        .queryParam("checkout", "2026-03-10")
                        .when()
                        .get("/booking")// путь к которому добавляются параметры для фильтрации
                        .then()
                        //.log().status()
                        //.log().body()
                        .statusCode(200) //проверка статуса ответа
                        .body("$", not(empty())) // проверка что ответ не пустой
                        .extract()
                        .path("bookingid[0]"); // извлечение id в переменную bookingId начиная с первого элемента в массиве

        given() //второй запрос, чтобы проверить, что отфильтровались не случайные id
                .when()
                //.log().uri()
                .get("/booking/" + bookingId) // к урлу добавляется отфильтрованный id
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("firstname", equalTo("Andrey"))
                .body("lastname", equalTo("Dorokhin"))
                .body("bookingdates.checkin", equalTo("2026-01-01"))
                .body("bookingdates.checkout", equalTo("2026-03-10"));
    }

    @Tag("negative")
    @DisplayName("Фильтрация по несуществующему имени")
    @Test
    void getBookingIdsFilteringNotExistName() {
        given()
                .queryParam("firstname", "934579&((*")
                .queryParam("lastname", "984608(*&^")
                .when()
                .log().uri()
                .get("/booking")// путь к которому добавляются параметры для фильтрации
                .then()
                .log().status()
                .log().body()
                .statusCode(200) //проверка статуса ответа
                .body("$", empty());
    }

    static Stream<NotValidDate> notValidDateProvider() {
        return Stream.of(NotValidDate.values());
    }
    @ParameterizedTest(name = "Невалидные даты: {0}")
    @MethodSource("notValidDateProvider")
    @Tag("negative")
    @DisplayName("Фильтрация по невалидным датам")
    void getBookingIdsFilteringNotValidDate(NotValidDate notValidDate) {
        String checkin = notValidDate.getCheckin();
        String checkout = notValidDate.getCheckout();

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
    }

