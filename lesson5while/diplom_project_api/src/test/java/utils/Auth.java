package utils;

import static io.restassured.RestAssured.given;

public class Auth {

    public static String getToken() {
        return given()
                .contentType("application/json")
                .body("""
                        {
                          "username": "admin",
                          "password": "password123"
                        }
                        """)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}
