package be.fastned.application.boundary;

import be.fastned.application.domain.*;
import be.fastned.application.control.ControleInstallateur;
  
import org.springframework.stereotype.Component;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */
@Component("schermInstallateurInst")
public class SchermInstallateur {

    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private Installateur m_Installateur = new Installateur();
    private ControleInstallateur controle = new ControleInstallateur();

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
    public SchermInstallateur(){

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
     * Deze Domein-functie retourneert de installatie documentatie via een laadpaal. */
    public String toonInstallatieDoc(Laadpaal laadpaal){
        return controle.toonInstallatieDoc(laadpaal);
    }
    /**
     * Deze Domein-functie retourneert de reparatie documentatie via een laadpaal. */
    public String toonReparatieDoc(Laadpaal laadpaal){
        return controle.toonReparatieDoc(laadpaal);
    }

    /**
     * Deze Domein-functie retourneert een aangemaakt probleem via een afspraak-installatie. */
    public Probleem maakInstallatieProbleem(Afspraak afspraak, Laadpaal laadpaal, String beschrijving){
        return controle.maakInstallatieProbleem(afspraak, laadpaal, beschrijving);
    }
    /**
     * Deze Domein-functie retourneert een aangemaakt probleem via een afspraak-reparatie. */
    public Probleem maakReparatieProbleem(Afspraak afspraak, Laadpaal laadpaal, String beschrijving){
        return controle.maakReparatieProbleem(afspraak, laadpaal, beschrijving);
    }

    /**
     * Deze Domein-functie retourneert een aangemaakt oplossing via een afspraak-installatie. */
    public Oplossing maakInstallatieOplossing(Afspraak afspraak, String beschrijving){
        return controle.maakInstallatieOplossing(afspraak, beschrijving);
    }
    /**
     * Deze Domein-functie retourneert een aangemaakt oplossing via een afspraak-reparatie. */
    public Oplossing maakReparatieOplossing(Afspraak afspraak, String beschrijving){
        return controle.maakReparatieOplossing(afspraak, beschrijving);
    }

    /**
     * Deze Domein-functie updated de status van een afspraak. */
    public void updateAfspraakStatus(Afspraak afspraak, String status){
        controle.updateAfspraakStatus(afspraak, status);
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */
}