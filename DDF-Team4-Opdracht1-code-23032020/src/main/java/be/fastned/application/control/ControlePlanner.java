package be.fastned.application.control;

import be.fastned.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:52
 */
@Component("controlePlannerInst")
public class ControlePlanner {
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    /*@Autowired
    private ControleRegisterUser controleRegisterUserInst;
    private Planner m_Planner = (Planner) controleRegisterUserInst.getSignedIn("Planner");*/

    @Autowired
    private Persoon activeGebruikerInst;
    private Planner m_Planner = (Planner) activeGebruikerInst;

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
    public ControlePlanner(){

    }

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie retourneert deze met-persoongegevens-aangevulde persoon. */
    public Persoon identificeer(String voornaam, String naam, String geslacht, String emailAdres, String gsm){
        try{
            if (voornaam != "" && naam != "" && geslacht != "" && emailAdres != "" && gsm != "")
                return m_Planner.identificeer(voornaam, naam, geslacht, emailAdres, gsm);
            else
                throw new Exception("Persoon is niet geïdentificeerd vanwege een lege of null-waarde in voornaam/familienaam/geslacht/emailadres/gsm");
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * Deze Domein-functie retourneert een lijst met locatietoestemmingen van deze planner. */
    public ArrayList<Locatietoestemming> toonLocatieToestemmingen(){

        try{
            if (m_Planner.toonLocatieToestemmingen() != null){
                return m_Planner.toonLocatieToestemmingen();
            }
            else
                throw new Exception("Locatietoestemming is niet opgevraagd vanwege een lege of null-waarde voor de lijst van locatietoestemmingen.");
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * Deze Domein-functie retourneert een aangemaakte afspraak via deze planner. */
    public void maakAfspraak(Laadpaal laadpaal, Installateur installateur, Contract contract){

        try{
            if (laadpaal != null && installateur != null && contract != null){
                m_Planner.maakAfspraak(laadpaal, installateur, contract);
            }
            else
                throw new Exception("Afspraak is niet aangemaakt vanwege een lege of null-waarde voor laadpaal/installateur/contract");
        }
        catch (Exception ex){
            //throw new NotImplementedException();
        }
    }
    public void maakAfspraak(ArrayList<Laadpaal> laadpalen, Installateur installateur, Contract contract){

        try{
            if (laadpalen != null && installateur != null && contract != null){
                m_Planner.maakAfspraak(laadpalen, installateur, contract);
            }
            else
                throw new Exception("Afspraak is niet aangemaakt vanwege een lege of null-waarde voor laadpalen/installateur/contract");
        }
        catch (Exception ex){
            //throw new NotImplementedException();
        }
    }

    /**
     * Deze Domein-functie retourneert een probleem binnen een afspraak via deze planner. */
    public Probleem toonInstallatieProbleem(Afspraak afspraak){

        try{
            if (afspraak != null){
                return afspraak.getInstallatie().getProbleem();
            }
            else
                throw new Exception("Installatie-Probleem is niet opgevraagd vanwege een lege of null-waarde voor afspraak.");
        }
        catch (Exception ex){
            return null;
        }
    }
    public Probleem toonReparatieProbleem(Afspraak afspraak){

        try{
            if (afspraak != null){
                return afspraak.getReparatie().getProbleem();
            }
            else
                throw new Exception("Reparatie-Probleem is niet opgevraagd vanwege een lege of null-waarde voor afspraak.");
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * Deze Domein-functie updated de status van een probleem binnen een afspraak via deze planner. */
    public void evalueerProbleem(Probleem probleem, String status){

        try{
            if (probleem != null && status != ""){
                probleem.setStatus(status);
            }
            else
                throw new Exception("Probleem-Status is niet gëupdated vanwege een lege of null-waarde voor probleem/status.");
        }
        catch (Exception ex){
            //throw new NotImplementedException();
        }
    }

    /**
     * Deze Domein-functie updated de status van een probleem binnen een afspraak via deze planner. */
    public void sluitProbleem(Probleem probleem){

        try{
            if (probleem != null){
                probleem.setStatus("probleem gesloten");
            }
            else
                throw new Exception("Probleem-Status is niet gesloten vanwege een lege of null-waarde voor probleem.");
        }
        catch (Exception ex){
            //throw new NotImplementedException();
        }
    }

    /**
     * Deze Domein-functie updated de status van een locatietoestemming via deze planner. */
    public void evalueerAanmelding(Locatietoestemming aanmelding, String status){

        try{
            if (aanmelding != null && status != ""){
                aanmelding.setStatus(status);
            }
            else
                throw new Exception("Locatietoestemming-Status is niet gëupdated vanwege een lege of null-waarde voor aanmelding/status.");
        }
        catch (Exception ex){
            //throw new NotImplementedException();
        }
    }

    /**
     * Deze Domein-functie retourneert een aangemaakt contract via deze planner. */
    public Contract maakContract(LocalDateTime contractDatum, LocalDateTime installatieDatum, Afspraak parentAfspraak){

        try{
            if (contractDatum != null && installatieDatum != null && parentAfspraak != null){
                return m_Planner.maakContract(contractDatum, installatieDatum, parentAfspraak);
            }
            else
                throw new Exception("Contract is niet aangemaakt vanwege een lege of null-waarde voor contarctDatum/installatieDatum/parentAfspraak");
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