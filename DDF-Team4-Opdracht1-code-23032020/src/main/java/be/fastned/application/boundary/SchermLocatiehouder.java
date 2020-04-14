package be.fastned.application.boundary;

import be.fastned.application.boundary.Technisch.SchermBase;
import be.fastned.application.domain.*;
import be.fastned.application.control.ControleLocatiehouder;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonProfessional;

/**
 * @author TiboVG
 * @version 2.0
 * @created 15-Mar-2020 14:24:55
 */
public class SchermLocatiehouder extends SchermBase {
    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private ControleLocatiehouder m_ParentControleInstance = null;

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
	public SchermLocatiehouder(ControleLocatiehouder parentControleLocatiehouder){
        m_ParentControleInstance = parentControleLocatiehouder;
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
    public PersoonProfessional identificeer(PersoonProfessional gegevensOwner, String adres, String bedrijfsNaam, String btwNumer, String voornaam, String naam, String geslacht, String gsm){
        return m_ParentControleInstance.identificeer(gegevensOwner, adres, bedrijfsNaam, btwNumer, voornaam, naam, geslacht, gsm);
    }

    /**
     * Deze Domein-functie maakt een locatietoestemming via deze locatiehouder.
     * @return De aangemaakte locatietoestemming via deze locatiehouder.
     */
    public Locatietoestemming maakLocatieAan(int aantalLaadpalen, String typeLaadpaal){
        return m_ParentControleInstance.maakLocatieAan(aantalLaadpalen, typeLaadpaal);
    }

    /**
     * Deze Domein-functie toont het resultaat van een aanmelding.
     * @return De statusproperty via van een locatietoestemming.
     */
    public String toonAanmeldingResultaat(Locatietoestemming aanmelding){
        return m_ParentControleInstance.toonAanmeldingResultaat(aanmelding);
    }

    /**
     * Deze Domein-functie maakt een probleem via deze Locatiehouder.
     * @return Het aangemaakt probleem via deze Locatiehouder.
     */
    public Probleem maakProbleem(Laadpaal defecteLaadpaal, String probleemBeschrijving){
        return m_ParentControleInstance.maakProbleem(defecteLaadpaal, probleemBeschrijving, m_SchermViewer);
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */

    /* //----------------// SECTIE: Domein-Properties //----------------// */
    /**
     * Deze domein-attribuut setter vertegenwoordigt de controle-instantie in deze boundary-instantie.
     */
    public void setControleInstance(ControleLocatiehouder value){
        this.m_ParentControleInstance = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de controle-instantie in deze boundary-instantie.
     */
    public ControleLocatiehouder getControleInstance(){
        return this.m_ParentControleInstance;
    }

    /* //----------------// SECTIE: Technische-Properties //----------------// */
}