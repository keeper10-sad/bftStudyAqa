package components.enums;

public enum UpdateRequredInputs {
    FIRST_NAME("""
        {
            "lastname": "Dorokhin_20",
            "totalprice": 5000,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2025-12-01",
                "checkout": "2026-02-10"
            },
            "additionalneeds": "Dinner"
        }
        """),
    LAST_NAME("""
        {
            "firstname": "Andrey",
            "totalprice": 5000,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2025-12-01",
                "checkout": "2026-02-10"
            },
            "additionalneeds": "Dinner"
        }
        """
    ),
    TOTAL_PRICE("""
        {
            "firstname": "Andrey",
            "lastname": "Dorokhin_20",
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2025-12-01",
                "checkout": "2026-02-10"
            },
            "additionalneeds": "Dinner"
        }
        """),
    DEPOSITE_PAID("""
        {
            "firstname": "Andrey",
            "lastname": "Dorokhin_20",
            "totalprice": 5000,
            "bookingdates": {
                "checkin": "2025-12-01",
                "checkout": "2026-02-10"
            },
            "additionalneeds": "Dinner"
        }
        """),
    BOOKINGDATES("""
        {
            "firstname": "Andrey",
            "lastname": "Dorokhin_20",
            "totalprice": 5000,
            "depositpaid": false,
            "additionalneeds": "Dinner"
        }
        """),
    CHECKIN("""
        {
            "firstname": "Andrey",
            "lastname": "Dorokhin_20",
            "totalprice": 5000,
            "depositpaid": false,
            "bookingdates": {
                "checkout": "2026-02-10"
            },
            "additionalneeds": "Dinner"
        }
        """),
    CHECKOUT("""
        {
            "firstname": "Andrey",
            "lastname": "Dorokhin_20",
            "totalprice": 5000,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2025-12-01"
            },
            "additionalneeds": "Dinner"
        }
        """),
    ALL("{}");

    private final String updateBody;

    UpdateRequredInputs(String updateBody){
        this.updateBody = updateBody;
    }

    public String getUpdateBody() {
        return updateBody;
    }
}
