package be.fastned.application.domain.PersoonAbstracties;


import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonProfessional;

public abstract class PersoonProfessionalImpl extends PersoonDefaultImpl implements Persoon, PersoonProfessional {
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Properties //----------------// */
    protected String m_BTWNummer = null;
    protected String m_Bedrijfsnaam = null;
    protected String m_Adres = null;

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    /* //----------------// -#####- |--------------| -#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####- |--------------| -#####- //----------------// */

    public PersoonProfessionalImpl(){
        super();
    }

    public PersoonProfessionalImpl(String emailadres, String gebruikersnaam, String wachtwoord ){
        super(emailadres, gebruikersnaam, wachtwoord);
    }

    public PersoonProfessionalImpl(String emailadres, String gebruikersnaam, String wachtwoord, String adres, String bedrijfsnaam, String btwNummer, String naam, String voornaam, String geslacht, String gsm ){
        super(emailadres, gebruikersnaam, wachtwoord, naam, voornaam, geslacht, gsm);
        this.m_Adres = adres;
        this.m_BTWNummer = btwNummer;
        this.m_Bedrijfsnaam = bedrijfsnaam;
    }

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */
    public PersoonProfessional identificeer(String adres, String bedrijfsnaam, String btwNummer, String voornaam, String naam, String geslacht, String gsm) {
        this.setVoorNaam(voornaam);
        this.setNaam(naam);
        this.setGeslacht(geslacht);
        this.setGsm(gsm);
        return this;
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */

    /* //----------------// PROPERTY: Persoon-Adres //----------------// */
    public String getAdres() { return m_Adres; }
    public void setAdres(String value) { m_Adres = value; }

    /* //----------------// PROPERTY: Persoon-Bedrijfsnaam //----------------// */
    public String getBedrijfsnaam() { return m_Bedrijfsnaam; }
    public void setBedrijfsNaam(String value) { m_Bedrijfsnaam = value; }

    /* //----------------// PROPERTY: Persoon-BTWNummer //----------------// */
    public String getBTWNummer() { return m_BTWNummer; }
    public void setBTWNummer(String value) { m_BTWNummer = value; }
}
