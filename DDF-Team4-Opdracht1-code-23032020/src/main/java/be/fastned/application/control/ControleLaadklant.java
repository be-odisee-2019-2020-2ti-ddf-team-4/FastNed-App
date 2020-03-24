package be.fastned.application.control;

import be.fastned.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:52
 */
@Component("controleLaadklantInst")
public class ControleLaadklant {
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    /*@Autowired
    private ControleRegisterUser controleRegisterUserInst;
    private Laadklant m_Laadklant = (Laadklant) controleRegisterUserInst.getSignedIn("Laadklant");*/

    @Autowired
    private Persoon activeGebruikerInst;
    private Laadklant m_Laadklant = (Laadklant) activeGebruikerInst;

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
    public ControleLaadklant(){

    }

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie retourneert deze met-persoongegevens-aangevulde persoon. */
    public Persoon identificeer(String voornaam, String naam, String geslacht, String emailAdres, String gsm){

        try{
            if (voornaam != "" && naam != "" && geslacht != "" && emailAdres != "" && gsm != ""){
                return m_Laadklant.identificeer(voornaam, naam, geslacht, emailAdres, gsm);
            }
            else
                throw new Exception("Persoon is niet geïdentificeerd vanwege een lege of null-waarde in voornaam/familienaam/geslacht/emailadres/gsm");
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * Deze Domein-functie retourneert een berekende (geschatte) laadtijd. */
    public double berekenLaadtijd(Laadsessie laadsessie){

        try{
            if (laadsessie != null){
                return laadsessie.berekenLaadtijd();
            }
            else
                throw new Exception("Berekende Laadtijd is niet opgevraagd vanwege een lege of null-waarde voor laadsessie.");
        }
        catch (Exception ex){
            return 0;
        }
    }

    /**
     * Deze Domein-functie retourneert een aangemaakt probleem via deze laadklant. */
    public Probleem meldProbleem(Laadpaal laadpaal, String beschrijving){

        try{
            if (laadpaal != null && beschrijving != ""){
                return ((Laadklant)activeGebruikerInst).meldProbleem(laadpaal, beschrijving);
            }
            else
                throw new Exception("Probleem is niet aangemaakt door een lege of null-waarde voor laadpaal/beschrijving");
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