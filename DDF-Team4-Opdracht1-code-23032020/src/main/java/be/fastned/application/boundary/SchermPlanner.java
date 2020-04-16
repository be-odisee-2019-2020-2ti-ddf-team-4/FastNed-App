package be.fastned.application.boundary;

import be.fastned.application.boundary.Technisch.SchermBase;
import be.fastned.application.control.ControlePlanner;
import be.fastned.application.domain.AndereEntiteiten.*;
import be.fastned.application.domain.PersoonEntiteiten.Installateur;
import be.fastned.application.domain.PersoonEntiteiten.PersoonDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 2.0
 * @created 15-Mar-2020 14:24:55
 */
public class SchermPlanner extends SchermBase {
    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private ControlePlanner m_ParentControleInstance = null;

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
    public SchermPlanner(ControlePlanner parentControlePlanner){
        m_ParentControleInstance = parentControlePlanner;
        m_SchermViewer = m_ParentControleInstance.getActieveGebruiker();
    }

    /* //----------------// -#####----------------#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####----------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie updated de actieve persoon met persoons-/gebruikersgegevens.
     * @return Het aangevulde persoon-object van de actieve gebruiker.
     */
    public PersoonDefault identificeer(String voornaam, String naam, String geslacht, String gsm){
        return m_ParentControleInstance.identificeer(voornaam, naam, geslacht, gsm);
    }

    /**
     * Deze Domein-functie zoekt alle locatietoestemmingen.
     * @return Een lijst van alle locatietoestemmingen.
     */
    public ArrayList<Locatietoestemming> zoekLocatieToestemmingen(){
        return m_ParentControleInstance.zoekLocatieToestemmingen();
    }

    /**
     * Deze Domein-functie maakt een afspraak via deze planner.
     * @return De aangemaakt afspraak via deze planner.
     */
    public void maakAfspraak(Laadpaal laadpaal, Installateur installateur, Contract contract){
        m_ParentControleInstance.maakAfspraak(laadpaal, installateur, contract);
    }

    /**
     * Deze Domein-functie zoekt installatieprobleem op basis van een afspraak.
     * @return Het probleem binnen een afspraak via deze planner.
     */
    public Probleem zoekInstallatieProbleem(Afspraak afspraak){
        return m_ParentControleInstance.zoekInstallatieProbleem(afspraak);
    }
    /**
     * Deze Domein-functie zoekt reparatieprobleem op basis van een afspraak.
     * @return Het probleem binnen een afspraak via deze planner.
     */
    public Probleem zoekReparatieProbleem(Afspraak afspraak){
        return m_ParentControleInstance.zoekReparatieProbleem(afspraak);
    }

    /**
     * Deze Domein-functie updated een status van een probleem via deze planner.
     * @return Het geüpdatete probleem.
     */
    public void evalueerProbleem(Probleem probleem, String nieuweStatus){
        m_ParentControleInstance.evalueerProbleem(probleem, nieuweStatus);
    }

    /**
     * Deze Domein-functie updated een status (automatisch) van een probleem via deze planner.
     * @return Het geüpdatete/gesloten probleem.
     */
    public void sluitProbleem(Probleem probleem){
        m_ParentControleInstance.sluitProbleem(probleem);
    }

    /**
     * Deze Domein-functie updated een status van een locatietoestemming via deze planner.
     * @return De geüpdatete locatietoestemming.
     */
    public void evalueerAanmelding(Locatietoestemming aanmelding, String nieuweStatus){
        m_ParentControleInstance.evalueerAanmelding(aanmelding, nieuweStatus);
    }

    /**
     * Deze Domein-functie maakt een contract via deze planner binnen een afspraak.
     * @return Het aangemaakte contract binnen een afspraak via deze planner.
     */
    public Contract maakContract(LocalDateTime contractDatum, LocalDateTime installatieDatum, Afspraak parentAfspraak){
        return m_ParentControleInstance.maakContract(contractDatum, installatieDatum, parentAfspraak);
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####------------------#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Properties //----------------// */
    /**
     * Deze domein-attribuut setter vertegenwoordigt de controle-instantie in deze boundary-instantie.
     */
    public void setControleInstance(ControlePlanner value){
        this.m_ParentControleInstance = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de controle-instantie in deze boundary-instantie.
     */
    public ControlePlanner getControleInstance(){
        return this.m_ParentControleInstance;
    }

    /* //----------------// SECTIE: Technische-Properties //----------------// */
}