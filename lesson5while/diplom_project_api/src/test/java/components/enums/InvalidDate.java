package components.enums;

public enum InvalidDate {
    FUTURE("2030-01-01", "2030-01-10"),
    PAST("1931-01-01", "1931-01-02"),
    CHECKIN_AFTER_CHECKOUT("2025-01-10", "2025-01-01"),
    SAME_DATES("2099-12-31", "2099-12-31"),
    NON_EXISTENT_DATE("1-02-31", "1-11-21");

    private final String checkin;
    private final String checkout;

    InvalidDate(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }
}
