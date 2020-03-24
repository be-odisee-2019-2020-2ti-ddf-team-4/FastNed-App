package be.fastned.application.boundary;

import be.fastned.application.domain.*;
import be.fastned.application.control.ControlePlanner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:55
 */
@Component("schermPlannerInst")
public class SchermPlanner {
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private Planner m_Planner = new Planner();
    private ControlePlanner controle = new ControlePlanner();

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
    public SchermPlanner(){

	}

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie retourneert deze met-persoongegevens-aangevulde persoon. */
    public Persoon identificeer(String voornaam, String naam, String geslacht, String emailAdres, String gsm){
        return controle.identificeer(voornaam, naam, geslacht, emailAdres, gsm);
    }

    /**
     * Deze Domein-functie retourneert een lijst met locatietoestemmingen van deze planner. */
    public ArrayList<Locatietoestemming> toonLocatieToestemmingen(){
        return controle.toonLocatieToestemmingen();
    }

    /**
     * Deze Domein-functie retourneert een aangemaakte afspraak via deze planner. */
    public void maakAfspraak(Laadpaal laadpaal, Installateur installateur, Contract contract){
        controle.maakAfspraak(laadpaal, installateur, contract);
    }
    public void maakAfspraak(ArrayList<Laadpaal> laadpalen, Installateur installateur, Contract contract){
        controle.maakAfspraak(laadpalen, installateur, contract);
    }

    /**
     * Deze Domein-functie retourneert een probleem binnen een afspraak via deze planner. */
    public Probleem toonInstallatieProbleem(Afspraak afspraak){
        return controle.toonInstallatieProbleem(afspraak);
    }
    public Probleem toonReparatieProbleem(Afspraak afspraak){
        return controle.toonReparatieProbleem(afspraak);
    }

    /**
     * Deze Domein-functie updated de status van een probleem binnen een afspraak via deze planner. */
    public void evalueerProbleem(Probleem probleem, String status){
        controle.evalueerProbleem(probleem, status);
    }

    /**
     * Deze Domein-functie updated de status van een probleem binnen een afspraak via deze planner. */
    public void sluitProbleem(Probleem probleem){
        controle.sluitProbleem(probleem);
    }

    /**
     * Deze Domein-functie updated de status van een locatietoestemming via deze planner. */
    public void evalueerAanmelding(Locatietoestemming aanmelding, String status){
        controle.evalueerAanmelding(aanmelding, status);
    }

    /**
     * Deze Domein-functie retourneert een aangemaakt contract via deze planner. */
    public Contract maakContract(LocalDateTime contractDatum, LocalDateTime installatieDatum, Afspraak parentAfspraak){
        return controle.maakContract(contractDatum, installatieDatum, parentAfspraak);
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */
}