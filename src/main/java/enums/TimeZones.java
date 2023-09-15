package enums;

public enum TimeZones {

    UTC_PLUS_14("UTC +14:00");

    // could add a lot of time zone values

    private final String value;

    public String getValue() {
        return value;
    }

    TimeZones(final String value) {
        this.value = value;
    }
}
