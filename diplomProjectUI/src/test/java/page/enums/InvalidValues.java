package page.enums;

public enum InvalidValues {
    PHONE_SHORT("911"),
    PHONE_WITH_CODE("+79003058045"),
    EMAIL_INVALID("urywriwuwoeuru"),
    EMAIL_WITHOUT_DOT("tersat09@mailru"),
    EMAIL_WITHOUT_HOST("tersat09@mail"),
    EMAIL_WITHOUT_DOG("stud-86mail.ru");

    private final String value;

    InvalidValues(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
