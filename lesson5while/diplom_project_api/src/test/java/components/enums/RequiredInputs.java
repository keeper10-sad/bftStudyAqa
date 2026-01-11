package components.enums;

public enum RequiredInputs {
FIRST_NAME("""
            {
              "lastname": "Dorokhin",
              "totalprice": 10000,
              "depositpaid": true,
              "bookingdates": { "checkin": "2025-12-01", "checkout": "2026-03-10" },
              "additionalneeds": "Do not disturbed"
            }
        """),
    LAST_NAME("""
            {
              "firstname": "Andrey",
              "totalprice": 10000,
              "depositpaid": true,
              "bookingdates": { "checkin": "2025-12-01", "checkout": "2026-03-10" },
              "additionalneeds": "Do not disturbed"
            }
        """),
    TOTAL_PRICE("""
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "depositpaid": true,
              "bookingdates": { "checkin": "2025-12-01", "checkout": "2026-03-10" },
              "additionalneeds": "Do not disturbed"
            }
        """),
    DEPOSITE_PAID("""
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "totalprice": 10000,
              "bookingdates": { "checkin": "2025-12-01", "checkout": "2026-03-10" },
              "additionalneeds": "Do not disturbed"
            }
        """),
    BOOKING_DATES("""
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "totalprice": 10000,
              "depositpaid": true,
              "additionalneeds": "Do not disturbed"
            }
        """),
    CHECKIN("""
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "totalprice": 10000,
              "depositpaid": true,
              "bookingdates": { "checkout": "2026-03-10" },
              "additionalneeds": "Do not disturbed"
            }
        """),
    CHECKOUT("""
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "totalprice": 10000,
              "depositpaid": true,
              "bookingdates": { "checkin": "2025-12-01"},
              "additionalneeds": "Do not disturbed"
            }
        """),
    ALL("{}");

private final String requestBody;

RequiredInputs(String requestBody){
    this.requestBody = requestBody;
}

    public String getRequestBody() {
        return requestBody;
    }
}
