package be.fastned.application.code.boundary.Base;

import be.fastned.application.code.control.ControleRegisterUser;
import be.fastned.application.code.domain.Personen.Persoon;
import be.fastned.application.code.domain.Technisch.EnumPersoon;

/**
 * @author TiboVG
 * @version 2.0
 * @created 15-Mar-2020 14:24:55
 */
public class SchermRegisterUser{
    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private ControleRegisterUser m_ParentControleInstance = null;

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    /* //----------------// -#####-------------------------#####- //----------------// */
    /* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####-------------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Variabelen //----------------// */

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    /* //----------------// -#####--------------------#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####--------------------#####- //----------------// */

    /**
     * Default Constructor voor deze klasse.
     */
    public SchermRegisterUser(ControleRegisterUser parentControleLaadklant){
        m_ParentControleInstance = parentControleLaadklant;
    }

    /* //----------------// -#####----------------#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####----------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie retourneert een aangemaakte persoon.
     * @return De aangemaakte persoon.
     */
    public Persoon register(EnumPersoon type, String emailAdres, String gebruikersnaam, String wachtwoord, String voornaam, String naam, String geslacht, String gsm){
        return m_ParentControleInstance.register(type, gebruikersnaam, emailAdres, wachtwoord, voornaam, naam, geslacht, gsm);
    }
    /**
     * Deze Domein-functie retourneert een aangemaakte persoon. (extra param adres/bedrijfsnaam/BTWNummer)
     * @return De aangemaakte persoon. (extra param adres/bedrijfsnaam/BTWNummer)
     */
    public Persoon register(EnumPersoon type, String emailAdres, String gebruikersnaam, String wachtwoord, String adres, String bedrijfsNaam, String btwNummer, String voornaam, String naam, String geslacht, String gsm){
        return m_ParentControleInstance.register(type, gebruikersnaam, emailAdres, wachtwoord, adres, bedrijfsNaam, btwNummer, voornaam, naam, geslacht, gsm);
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####------------------#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Properties //----------------// */
    /**
     * Deze domein-attribuut setter vertegenwoordigt de controle-instantie in deze boundary-instantie.
     */
    public void setControleRegisterUser(ControleRegisterUser value){
        this.m_ParentControleInstance = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de controle-instantie in deze boundary-instantie.
     */
    public ControleRegisterUser getControleRegisterUser(){
        return this.m_ParentControleInstance;
    }

    /* //----------------// SECTIE: Technische-Properties //----------------// */
}
