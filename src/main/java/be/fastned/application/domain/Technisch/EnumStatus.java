package be.fastned.application.domain.Technisch;

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
