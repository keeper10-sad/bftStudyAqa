package components.enums;

public enum UpdateInvalidData {
    TOTALPRICE("""
        {
            "firstname": "Andrey",
            "lastname": "Dorokhin_20",
            "totalprice": estdgd,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2025-12-01",
                "checkout": "2026-02-10"
            },
            "additionalneeds": "Dinner"
        }
        """),
    DEPOSITEPAID("""
        {
            "firstname": "Andrey",
            "lastname": "Dorokhin_20",
            "totalprice": 5000,
            "depositpaid": rsdfgffdf,
            "bookingdates": {
                "checkin": "2025-12-01",
                "checkout": "2026-02-10"
            },
            "additionalneeds": "Dinner"
        }
        """);

    private final String updateBody;

    UpdateInvalidData(String updateBody){
        this.updateBody = updateBody;
    }

    public String getUpdateBody() {
        return updateBody;
    }
}
