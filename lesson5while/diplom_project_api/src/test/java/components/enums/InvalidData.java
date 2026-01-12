package components.enums;

public enum InvalidData {
    TOTAL_PRICE("""
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "totalprice": reyereru,
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
              "depositpaid": dfhdfhfdh,
              "bookingdates": { "checkin": "2025-12-01", "checkout": "2026-03-10" },
              "additionalneeds": "Do not disturbed"
            }
        """);

    private final String requestBody;

    InvalidData(String requestBody){
        this.requestBody = requestBody;
    }

    public String getRequestBody() {
        return requestBody;
    }
}
