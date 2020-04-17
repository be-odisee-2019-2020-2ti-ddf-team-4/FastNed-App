package be.fastned.application.code.domain.Technisch;

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
