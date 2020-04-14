package be.fastned.application.domain;

public enum EnumStatus {
    AANGEMAAKT("aangemaakt");

    private String value;

    EnumStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
