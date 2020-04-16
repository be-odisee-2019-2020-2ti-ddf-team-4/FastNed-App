package be.fastned.application.domain.PersoonEntiteiten;

public interface PersoonExtended {

    String getBedrijfsnaam();
    void setBedrijfsnaam(String value);

    String getBTWNummer();
    void setBTWNummer(String value);

    String getAdres();
    void setAdres(String value);

    PersoonExtended identificeer(String bedrijfsnaam, String adres, String btwNummer, String naam, String voornaam, String geslacht, String gsm);
}
