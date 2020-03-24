package be.fastned.application.boundary;

import be.fastned.application.domain.*;
import be.fastned.application.control.ControleLaadklant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */
@Component("schermLaadklantInst")
public class SchermLaadklant {
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

    @Autowired
    private ControleLaadklant controleLaadklantInst;
    //private ControleLaadklant controle = controleLaadklantInst;

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
    public SchermLaadklant(){

	}

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie retourneert deze met-persoongegevens-aangevulde persoon. */
    public Persoon identificeer(String voornaam, String naam, String geslacht, String emailAdres, String gsm){
        return controleLaadklantInst.identificeer(voornaam, naam, geslacht, emailAdres, gsm);
    }

    /**
     * Deze Domein-functie retourneert een berekende (geschatte) laadtijd. */
    public double berekenLaadtijd(Laadsessie laadsessie){
        return controleLaadklantInst.berekenLaadtijd(laadsessie);
    }

    /**
     * Deze Domein-functie retourneert een aangemaakt probleem via deze laadklant. */
    public Probleem meldProbleem(Laadpaal laadpaal, String beschrijving){
        return controleLaadklantInst.meldProbleem(laadpaal, beschrijving);
    }
    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */
    /**
     * Deze domein-attribuut setter vertegenwoordigt de huidige Laadklant. */
    public void setLaadklant(Laadklant value){
        this.m_Laadklant = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de huidige Laadklant. */
    public Laadklant getLaadklant(){
        return (Laadklant) this.activeGebruikerInst;
    }
}