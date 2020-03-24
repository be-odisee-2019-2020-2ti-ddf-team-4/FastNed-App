package be.fastned.application.control;

import be.fastned.application.domain.Persoon;
import be.fastned.application.domain.PersoonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */
@Component("controleRegisterUserInst")
public class ControleRegisterUser {

    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    /*private static Persoon s_SignedInLocatiehouder = null;
    private static Persoon s_SignedInPlanner = null;
    private static Persoon s_SignedInInstallateur = null;*/
    @Autowired
    private Persoon testLaadklant;

    private Persoon s_SignedInLocatiehouder = null;
    private Persoon s_SignedInPlanner = null;
    private Persoon s_SignedInInstallateur = null;
    private Persoon s_SignedInLaadklant = testLaadklant;

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */


    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    /* //----------------// -#####- |--------------| -#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####- |--------------| -#####- //----------------// */

    public ControleRegisterUser(){

    }

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie retourneert een met-persoongegevens-aangevulde persoon. */
    public Persoon register(String gebruikersnaam, String wachtwoord, String type, String voornaam, String naam, String geslacht, String emailAdres, String gsm){
        try{
            if (gebruikersnaam != "" && wachtwoord != "" && type != "" && voornaam != "" && naam != "" && geslacht != "" && emailAdres != "" && gsm != "")
                return PersoonFactory.createPersoon(gebruikersnaam, wachtwoord, type, voornaam, naam, geslacht, emailAdres, gsm);
            else
                throw new Exception("Persoon is niet geïdentificeerd vanwege een lege of null-waarde in gebruikersnaam/wachtwoord/type/voornaam/familienaam/geslacht/emailadres/gsm");
        }
        catch (Exception ex){
            return null;
        }
    }
    public Persoon register(String gebruikersnaam, String wachtwoord, String adres, String bedrijfsNaam, String BTWNummer, String type, String voornaam, String naam, String geslacht, String emailAdres, String gsm){
        try{
            if (gebruikersnaam != "" && wachtwoord != "" && type != "" && adres != "" && bedrijfsNaam != "" && BTWNummer != "" && voornaam != "" && naam != "" && geslacht != "" && emailAdres != "" && gsm != "")
                return PersoonFactory.createPersoon(gebruikersnaam, wachtwoord, type, adres, bedrijfsNaam, BTWNummer, voornaam, naam, geslacht, emailAdres, gsm);
            else
                throw new Exception("Persoon is niet geïdentificeerd vanwege een lege of null-waarde in gebruikersnaam/wachtwoord/type/bedrijfsnaam/adres/BTWNummer/voornaam/familienaam/geslacht/emailadres/gsm");
        }
        catch (Exception ex){
            return null;
        }
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// PROPERTY: SignedIn //----------------// */
    public Persoon getSignedIn(String type){
        try{
            if (type == null || type == "") throw new Exception("Actieve persoon is niet gevonden vanwege een lege of null-waarde voor type.");
            else if (type == "Laadklant") return testLaadklant;
            else throw new Exception("Actieve persoon is niet gevonden op basis van het gegeven type.");
        }
        catch(Exception e){
            return null;
        }
    }
}
