package page.enums;

public enum DateValues {

    VALID_DATE("11", "December", "1986", "11 Dec 1986", true),
    LEAP_YEAR("29", "February", "2020", "29 Feb 2020", true),
    MIN_DATE("01", "January", "1900", "01 Jan 1900", true),
    MAX_DATE("31", "December", "2100", "31 Dec 2100", true),

    NON_LEAP_YEAR("29", "February", "2026", null, false),
    INVALID_DATE("31", "February", "2000", null, false);

    public final String day;
    public final String month;
    public final String year;
    public final String expected;
    public final boolean positive;

    DateValues(String day, String month, String year, String expected, boolean positive) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.expected = expected;
        this.positive = positive;
    }

    public String getDay() { return day; }
    public String getMonth() { return month; }
    public String getYear() { return year; }
    public String getExpected() { return expected; }
    public boolean isPositive() { return positive; }
}
