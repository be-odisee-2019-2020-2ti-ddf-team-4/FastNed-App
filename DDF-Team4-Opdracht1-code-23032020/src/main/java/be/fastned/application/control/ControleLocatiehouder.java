package be.fastned.application.control;

import be.fastned.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:52
 */
@Component("controleLocatiehouderInst")
public class ControleLocatiehouder {
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    /*@Autowired
    private ControleRegisterUser controleRegisterUserInst;
    private Locatiehouder m_Locatiehouder = (Locatiehouder) controleRegisterUserInst.getSignedIn("Locatiehouder");*/

    @Autowired
    private Persoon activeGebruikerInst;
    private Locatiehouder m_Locatiehouder = (Locatiehouder) activeGebruikerInst;

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    /* //----------------// -#####- |--------------| -#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####- |--------------| -#####- //----------------// */
    /**
     * Default Constructor voor deze klasse. */
    public ControleLocatiehouder(){

    }

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie retourneert deze met-persoongegevens-aangevulde persoon. */
    public Persoon identificeer(String adres, String bedrijfsNaam, String BTWNummer, String voornaam, String naam, String geslacht, String emailAdres, String gsm){

        try{
            if(adres != "" && bedrijfsNaam != "" && BTWNummer != "" && voornaam != "" && naam != "" && geslacht != "" && emailAdres != "" && gsm != ""){
                m_Locatiehouder.identificeer(voornaam, naam, geslacht, emailAdres, gsm);
                m_Locatiehouder.setAdres(adres);
                m_Locatiehouder.setBedrijfsnaam(BTWNummer);
                m_Locatiehouder.setBTWNummer(adres);
                return m_Locatiehouder;
            }
            else
                throw new Exception("Persoon is niet geïdentificeerd vanwege een lege of null-waarde in adres/bedrijfsnaam/BTWNummer/voornaam/familienaam/geslacht/emailadres/gsm");
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * Deze Domein-functie retourneert een aangemaakte locatietoestemming via deze locatiehouder. */
    public Locatietoestemming meldLocatieAan(int aantalLaadpalen, String typeLaadpaal){

        try{
            if (aantalLaadpalen > 0 && typeLaadpaal != ""){
                return m_Locatiehouder.meldLocatieAan(aantalLaadpalen, typeLaadpaal);
            }
            else
                throw new Exception("Locatietoestemming is niet aangemaakt vanwege een lege of null-waarde voor aantalLaadpalen/typeLaadpaal");
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * Deze Domein-functie retourneert een locatietoestemming zijn status via deze locatiehouder. */
    public String toonAanmeldingResultaat(Locatietoestemming aanmelding){

        try{
            if (aanmelding != null){
                return aanmelding.getStatus();
            }
            else
                throw new Exception("Aanmelding-Resultaat is niet opgevraagd vanwege een lege of null-waarde voor aanmelding.");
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * Deze Domein-functie retourneert een aangemaakt probleem via deze locatiehouder. */
    public Probleem meldProbleem(Laadpaal laadpaal, String beschrijving){

        try{
            if (laadpaal != null && beschrijving != ""){
                Probleem probleem = new Probleem(laadpaal, beschrijving);
                m_Locatiehouder.ActiveLocatietoestemmingen.add(probleem);
                return probleem;
            }
            else
                throw new Exception("Probleem is niet aangemaakt vanwege een lege of null-waarde voor laadpaal/beschrijving");
        }
        catch (Exception ex){
            return null;
        }
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */
}