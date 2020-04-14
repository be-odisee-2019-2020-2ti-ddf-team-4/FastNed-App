package be.fastned.application.domain.PersoonAbstracties.Interfaces;

public interface PersoonProfessional {

    String getId();
    void setId(String id);

    String getAdres();
    void setAdres(String value);

    String getBedrijfsnaam();
    void setBedrijfsNaam(String value);

    String getBTWNummer();
    void setBTWNummer(String value);

    PersoonProfessional identificeer(String adres, String bedrijfsnaam, String btwNummer, String voornaam, String naam, String geslacht, String gsm);
}
