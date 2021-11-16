package model;

public enum UserPassURL {
    USER1("postgres"),
    PASSWORD1("terebitia"),
    URL1("jdbc:postgresql://localhost:5432/booking_project");

    private final String value;

    UserPassURL(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
