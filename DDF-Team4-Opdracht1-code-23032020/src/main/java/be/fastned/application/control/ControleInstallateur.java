package be.fastned.application.control;

import be.fastned.application.domain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:52
 */
@Component("controleInstallateurInst")
public class ControleInstallateur {
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    /*@Autowired
    private ControleRegisterUser controleRegisterUserInst;
    private Installateur m_Installateur = (Installateur) controleRegisterUserInst.getSignedIn("Installateur");*/

    @Autowired
    private Persoon activeGebruikerInst;
    private Installateur m_Installateur = (Installateur) activeGebruikerInst;

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
    public ControleInstallateur(){

    }

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie retourneert deze met-persoongegevens-aangevulde persoon. */
    public Persoon identificeer(String voornaam, String naam, String geslacht, String emailAdres, String gsm){
        try{
            if (voornaam != "" || naam != "" || geslacht != "" || emailAdres != "" || gsm != "")
                return m_Installateur.identificeer(voornaam, naam, geslacht, emailAdres, gsm);
            else
                throw new Exception("Persoon is niet geïdentificeerd vanwege een lege of null-waarde in voornaam/familienaam/geslacht/emailadres/gsm");
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * Deze Domein-functie retourneert de installatie documentatie via een laadpaal. */
    public String toonInstallatieDoc(Laadpaal laadpaal){
        try{
            if (laadpaal != null){
                return laadpaal.getInstallatieDoc();
            }
            else
                throw new Exception("Installatiedocumentatie is niet opgehaald vanwege een lege of null-waarde voor laadpaal.");
        }
        catch (Exception ex){
            return null;
        }
    }
    /**
     * Deze Domein-functie retourneert de reparatie documentatie via een laadpaal. */
    public String toonReparatieDoc(Laadpaal laadpaal){

        try{
            if (laadpaal != null){
                return laadpaal.getReparatieDoc();
            }
            else
                throw new Exception("Reparatiedocumentatie is niet opgehaald vanwege een lege of null-waarde voor laadpaal.");
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * Deze Domein-functie retourneert een aangemaakt probleem via een afspraak-installatie. */
    public Probleem maakInstallatieProbleem(Afspraak afspraak, Laadpaal laadpaal, String beschrijving){

        try{
            if (afspraak != null || laadpaal != laadpaal || beschrijving != ""){
                afspraak.getInstallatie().setProbleem(new Probleem(laadpaal, beschrijving));
                return afspraak.getInstallatie().getProbleem();
            }
            else
                throw new Exception("Installatie-Probleem is niet aangemaakt vanwege een lege of null-waarde voor afspraak/laadpaal/beschrijving");
        }
        catch (Exception ex){
            return null;
        }
    }
    /**
     * Deze Domein-functie retourneert een aangemaakt probleem via een afspraak-reparatie. */
    public Probleem maakReparatieProbleem(Afspraak afspraak, Laadpaal laadpaal, String beschrijving){

        try{
            if (afspraak != null || laadpaal != null || beschrijving != ""){
                afspraak.getReparatie().setProbleem(new Probleem(laadpaal, beschrijving));
                return afspraak.getReparatie().getProbleem();
            }
            else
                throw new Exception("Reparatie-Probleem is niet aangemaakt vanwege een lege of null-waarde voor afspraak/laadpaal/beschrijving");
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * Deze Domein-functie retourneert een aangemaakt oplossing via een afspraak-installatie. */
    public Oplossing maakInstallatieOplossing(Afspraak afspraak, String beschrijving){

        try{
            if (afspraak != null || beschrijving != ""){
                afspraak.getInstallatie().getProbleem().setOplossing(new Oplossing(beschrijving));
                return afspraak.getInstallatie().getProbleem().getOplossing();
            }
            else
                throw new Exception("Installatie-Oplossing is niet aangemaakt vanwege een lege of null-waarde voor afspraak/beschrijving.");
        }
        catch (Exception ex){
            return null;
        }
    }
    /**
     * Deze Domein-functie retourneert een aangemaakt oplossing via een afspraak-reparatie. */
    public Oplossing maakReparatieOplossing(Afspraak afspraak, String beschrijving){

        try{
            if (afspraak != null || beschrijving != ""){
                afspraak.getReparatie().getProbleem().setOplossing(new Oplossing(beschrijving));
                return afspraak.getReparatie().getProbleem().getOplossing();
            }
            else
                throw new Exception("Reparatie-Oplossing is niet aangemaakt vanwege een lege of null-waarde voor afspraak/beschrijving.");
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * Deze Domein-functie updated de status van een afspraak. */
    public void updateAfspraakStatus(Afspraak afspraak, String status){

        try{
            if (afspraak != null || status != "")
                afspraak.setStatus(status);
            else
                throw new Exception("Afspraak-Status is niet geüpdated vanwege een lege of null-waarde voor afspraak/status");
        }
        catch (Exception ex){
            // throw new Exception();
        }
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */
}