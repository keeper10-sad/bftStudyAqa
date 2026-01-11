package components.enums;

public enum NotValidDate {
    LARGE_DATE("2030-01-01", "2030-01-10"),
    SMALL_DATE("1931-01-01", "1931-01-02"),
    CHECKIN_BIGGER_CHECKOUT("2025-01-10", "2025-01-01"),
    SAME_DATES("2099-12-31", "2099-12-31"),
    NOT_EXIST_DATE("1-02-31", "1-11-21"),
    NOT_VALID_DATE("2025-16-01", "2026-01-10");

    private final String checkin;
    private final String checkout;

    NotValidDate(String checkin, String checkout) {
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
