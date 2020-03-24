package be.fastned.application.domain;

import be.fastned.application.domain.custom.ArrayListExtended;

import javax.persistence.Column;
import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */
public abstract class PersoonImpl implements Persoon {
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Properties //----------------// */
    protected long m_Id;
    @Column
    protected String m_Naam = null;
    @Column
    protected String m_Voornaam = null;
    @Column
    protected String m_Geslacht = null;
    @Column
    protected String m_Emailadres = null;
    @Column
    protected String m_Gsm = null;
    @Column
    protected String m_Gebruikersnaam = null;
    @Column
    protected String m_Wachtwoord = null;

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private long m_IdCounter = 0;

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */
    /**
     * Collectie van actieve & nieuwe Personen. (data-bron voor schermen) */
    public static ArrayListExtended<Persoon, Persoon> s_ActivePersonen = new ArrayListExtended<Persoon, Persoon>();
    /**
     * Collectie van verlopen & afgehandelde Personen. (repository voor rollback) */
    public static ArrayList<Persoon> s_ArchivedPersoonPersoon = new ArrayList<Persoon>();

    /* //----------------// -#####- |--------------| -#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####- |--------------| -#####- //----------------// */
    /**
     * Default Constructor voor deze klasse. */
    public PersoonImpl(){
        s_ActivePersonen.add(this);
    }
    /**
     * Volledige Constructor voor deze klasse. */
    public PersoonImpl(String gebruikersnaam, String wachtwoord, String naam, String voornaam, String geslacht, String emailadres, String gsm ){
        m_Naam = naam;
        m_Voornaam = voornaam;
        m_Emailadres = emailadres;
        m_Geslacht = geslacht;
        m_Gsm = gsm;
        m_Gebruikersnaam = gebruikersnaam;
        m_Wachtwoord = wachtwoord;
        s_ActivePersonen.add(this);
    }

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */
    public Persoon identificeer(String voornaam, String naam, String geslacht, String emailAdres, String gsm) {
        Persoon persoon = this;
        persoon.setVoorNaam(voornaam);
        persoon.setNaam(naam);
        persoon.setGeslacht(geslacht);
        persoon.setEmailadres(emailAdres);
        persoon.setGsm(gsm);
        return persoon;
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */
    /**
     * Deze Domein-functie schrijft een deze instantie over van de Active-ArrayList naar de Archived-ArrayList.
     * Dit via klasse "ArrayListExtended" via naamgeving "s_ArchivedKlasseItemKlasse" of dit zonder "s_". */
    public void archiveer(){
        this.s_ActivePersonen.removeWrapped(Persoon.class, Persoon.class, true);
    }

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// PROPERTY: Persoon-Id //----------------// */
    public long getId() { return m_Id; }
    public void setId(long id) { m_Id = id; }

    /* //----------------// PROPERTY: Persoon-Naam //----------------// */
    public String getNaam() { return m_Naam; }
    public void setNaam(String naam) { m_Naam = naam; }

    /* //----------------// PROPERTY: Persoon-Voornaam //----------------// */
    public String getVoorNaam() { return m_Voornaam; }
    public void setVoorNaam(String voorNaam) { m_Voornaam = voorNaam; }

    /* //----------------// PROPERTY: Persoon-Geslacht //----------------// */
    public String getGeslacht() { return m_Geslacht; }
    public void setGeslacht(String geslacht) { m_Geslacht = geslacht; }

    /* //----------------// PROPERTY: Persoon-Emailadres //----------------// */
    public String getEmailadres() { return m_Emailadres; }
    public void setEmailadres(String emailadres) { m_Emailadres = emailadres; }

    /* //----------------// PROPERTY: Persoon-Gsm //----------------// */
    public String getGsm() { return m_Gsm; }
    public void setGsm(String gsm) { m_Gsm = gsm; }

    /* //----------------// PROPERTY: Persoon-Gebruikersnaam //----------------// */
    public String getGebruikersnaam() { return m_Gebruikersnaam; }
    public void setGebruikersnaam(String gebruikersnaam) { m_Gebruikersnaam = gebruikersnaam; }
    /* //----------------// PROPERTY: Persoon-Wachtwoord //----------------// */
    public String getWachtwoord() { return m_Wachtwoord; }
    public void setWachtwoord(String wachtwoord) { m_Wachtwoord = wachtwoord; }

}