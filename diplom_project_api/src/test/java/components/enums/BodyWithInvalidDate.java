package components.enums;

public enum BodyWithInvalidDate {
    LARGE_DATE("""
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "totalprice": 10000,
              "depositpaid": true,
              "bookingdates": { "checkin": "2200-01-01", "checkout": "2500-03-10" },
              "additionalneeds": "Do not disturbed"
            }
        """),

    SMALL_DATE("""
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "totalprice": 10000,
              "depositpaid": true,
              "bookingdates": { "checkin": "5-01-01", "checkout": "100-03-10" },
              "additionalneeds": "Do not disturbed"
            }
        """),

    CHECKIN_BIGGER_CHECKOUT("""
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "totalprice": 10000,
              "depositpaid": true,
              "bookingdates": { "checkin": "2026-01-01", "checkout": "2025-03-10" },
              "additionalneeds": "Do not disturbed"
            }
        """),

    NOT_VALID_DATE("""
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "totalprice": 10000,
              "depositpaid": true,
              "bookingdates": { "checkin": "etdgsdg", "checkout": "AGDddd" },
              "additionalneeds": "Do not disturbed"
            }
        """),

    NOT_EXIST_DATE("""
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "totalprice": 10000,
              "depositpaid": true,
              "bookingdates": { "checkin": "2026-02-31", "checkout": "2026-17-10" },
              "additionalneeds": "Do not disturbed"
            }
        """);

    private final String requestBody;

    BodyWithInvalidDate(String requestBody){
        this.requestBody = requestBody;
    }

    public String getRequestBody() {
        return requestBody;
    }
}
