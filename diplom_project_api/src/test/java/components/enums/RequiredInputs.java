package components.enums;

public enum RequiredInputs {
    FIRSTNAME("""
        {
          "lastname": "Dorokhin",
          "totalprice": 10000,
          "depositpaid": true,
          "bookingdates": {
            "checkin": "2025-12-01",
            "checkout": "2026-01-10"
          }
        }
    """),

    LASTNAME("""
        {
          "firstname": "Andrey",
          "totalprice": 10000,
          "depositpaid": true,
          "bookingdates": {
            "checkin": "2025-12-01",
            "checkout": "2026-01-10"
          }
        }
    """),

    TOTALPRICE("""
        {
          "firstname": "Andrey",
          "lastname": "Dorokhin",
          "depositpaid": true,
          "bookingdates": {
            "checkin": "2025-12-01",
            "checkout": "2026-01-10"
          }
        }
    """),

    DEPOSITPAID("""
        {
          "firstname": "Andrey",
          "lastname": "Dorokhin",
          "totalprice": 10000,
          "bookingdates": {
            "checkin": "2025-12-01",
            "checkout": "2026-01-10"
          }
        }
    """),

    BOOKINGDATES("""
        {
          "firstname": "Andrey",
          "lastname": "Dorokhin",
          "totalprice": 10000,
          "depositpaid": true
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
