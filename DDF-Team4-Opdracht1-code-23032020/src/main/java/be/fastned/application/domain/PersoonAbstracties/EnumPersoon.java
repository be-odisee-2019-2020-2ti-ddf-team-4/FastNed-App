package be.fastned.application.domain.PersoonAbstracties;

public enum EnumPersoon {
    INSTALLATEUR("installateur"),
    PLANNER("planner"),
    LOCATIEHOUDER("locatiehouder"),
    LAADKLANT("laadklant");

    private String value;

    EnumPersoon(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
