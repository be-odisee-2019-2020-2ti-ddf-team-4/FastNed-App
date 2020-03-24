package be.fastned.application.boundary;

import be.fastned.application.domain.*;
import be.fastned.application.control.ControleLocatiehouder;
import org.springframework.stereotype.Component;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:55
 */
@Component("schermLocatiehouderInst")
public class SchermLocatiehouder {
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private Locatiehouder m_Locatiehouder = new Locatiehouder();
    private ControleLocatiehouder controle = new ControleLocatiehouder();

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
	public SchermLocatiehouder(){

	}

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie retourneert deze met-persoongegevens-aangevulde persoon. */
    public Persoon identificeer(String adres, String bedrijfsNaam, String BTWNummer, String voornaam, String naam, String geslacht, String emailAdres, String gsm){
        return controle.identificeer(adres, bedrijfsNaam, BTWNummer, voornaam, naam, geslacht, emailAdres, gsm);
    }

    /**
     * Deze Domein-functie retourneert een aangemaakte locatietoestemming via deze locatiehouder. */
    public Locatietoestemming meldLocatieAan(int aantalLaadpalen, String typeLaadpaal){
        return controle.meldLocatieAan(aantalLaadpalen, typeLaadpaal);
    }

    /**
     * Deze Domein-functie retourneert een locatietoestemming zijn status via deze locatiehouder. */
    public String toonAanmeldingResultaat(Locatietoestemming aanmelding){
        return controle.toonAanmeldingResultaat(aanmelding);
    }

    /**
     * Deze Domein-functie retourneert een aangemaakt probleem via deze locatiehouder. */
    public Probleem meldProbleem(Laadpaal laadpaal, String beschrijving){
        return controle.meldProbleem(laadpaal, beschrijving);
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */
}