package components.enums;

public enum NotValidData {
    NOT_VALID_TOTALPRICE("""
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "totalprice": ietie,
              "depositpaid": true,
              "bookingdates": { "checkin": "2026-01-01", "checkout": "2026-03-10" },
              "additionalneeds": "Do not disturbed"
            }
        """), // ожидаем number
    NOT_VALID_DEPOSITEPAID("""
            {
              "firstname": "Andrey",
              "lastname": "Dorokhin",
              "totalprice": 10000,
              "depositpaid": wrieytehhe,
              "bookingdates": { "checkin": "2026-01-01", "checkout": "2026-03-10" },
              "additionalneeds": "Do not disturbed"
            }
        """);// ожидаем boolean

    private final String requestBody;

    NotValidData(String requestBody){
        this.requestBody = requestBody;
    }

    public String getRequestBody() {
        return requestBody;
    }
}
