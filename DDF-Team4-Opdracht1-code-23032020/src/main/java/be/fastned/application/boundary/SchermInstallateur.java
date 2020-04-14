package be.fastned.application.boundary;

import be.fastned.application.boundary.Technisch.SchermBase;
import be.fastned.application.control.ControleInstallateur;

import be.fastned.application.domain.AndereEntiteiten.Afspraak;
import be.fastned.application.domain.AndereEntiteiten.Laadpaal;
import be.fastned.application.domain.AndereEntiteiten.Oplossing;
import be.fastned.application.domain.AndereEntiteiten.Probleem;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonDefault;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author TiboVG
 * @version 2.0
 * @created 15-Mar-2020 14:24:54
 */
public class SchermInstallateur extends SchermBase {

    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private ControleInstallateur m_ParentControleInstance = null;

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
    @Autowired
    public SchermInstallateur(ControleInstallateur parentControleInstallateur){
        m_ParentControleInstance = parentControleInstallateur;
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
    public PersoonDefault identificeer(PersoonDefault gegevensOwner, String voornaam, String naam, String geslacht, String gsm){
        return m_ParentControleInstance.identificeer(gegevensOwner, voornaam, naam, geslacht, gsm);
    }

    /**
     * Deze Domein-functie zoekt installatiedocumentatie op basis van een laadpaal.
     * @return De installatiedocumentatie uit de documentatierepository binnen een laadpaal.
     */
    public String zoekInstallatieDoc(Laadpaal laadpaal){
        return m_ParentControleInstance.zoekInstallatieDoc(laadpaal);
    }
    /**
     * Deze Domein-functie zoekt reparatiedocumentatie op basis van een laadpaal.
     * @return De reparatiedocumentatie uit de documentatierepository binnen een laadpaal.
     */
    public String zoekReparatieDoc(Laadpaal laadpaal){
        return m_ParentControleInstance.zoekReparatieDoc(laadpaal);
    }

    /**
     * Deze Domein-functie maakt een probleem vanuit een installatie binnen een afspraak.
     * @return Het aangemaakt probleem vanuit een installatie binnen een afspraak.
     */
    public Probleem maakInstallatieProbleem(Afspraak parentAfspraak, Laadpaal defecteLaadpaal, String probleemBeschrijving){
        return m_ParentControleInstance.maakInstallatieProbleem(parentAfspraak, defecteLaadpaal, probleemBeschrijving);
    }
    /**
     * Deze Domein-functie maakt een probleem vanuit een reparatie binnen een afspraak.
     * @return Het aangemaakt probleem vanuit een reparatie binnen een afspraak.
     */
    public Probleem maakReparatieProbleem(Afspraak parentAfspraak, Laadpaal defecteLaadpaal, String probleemBeschrijving){
        return m_ParentControleInstance.maakReparatieProbleem(parentAfspraak, defecteLaadpaal, probleemBeschrijving);
    }

    /**
     * Deze Domein-functie maakt een oplossing vanuit een installatie binnen een probleem in een afspraak.
     * @return De aangemaakte oplossing vanuit een installatie binnen een probleem in een afspraak.
     */
    public Oplossing maakInstallatieOplossing(Afspraak parentAfspraak, String oplossingBeschrijving){
        return m_ParentControleInstance.maakInstallatieOplossing(parentAfspraak, oplossingBeschrijving);
    }
    /**
     * Deze Domein-functie maakt een oplossing vanuit een reparatie binnen een probleem in een afspraak.
     * @return De aangemaakte oplossing vanuit een reparatie binnen een probleem in een afspraak.
     */
    public Oplossing maakReparatieOplossing(Afspraak parentAfspraak, String OplossingBeschrijving){
        return m_ParentControleInstance.maakReparatieOplossing(parentAfspraak, OplossingBeschrijving);
    }

    /**
     * Deze Domein-functie opdated een afspraak-status binnen een afspraak.
     * @return De geüpdatete afspraak.
     */
    public Afspraak updateAfspraakStatus(Afspraak afspraak, String nieuweStatus){
        return m_ParentControleInstance.updateAfspraakStatus(afspraak, nieuweStatus);
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####------------------#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Properties //----------------// */
    /**
     * Deze domein-attribuut setter vertegenwoordigt de controle-instantie in deze boundary-instantie.
     */
    public void setControleInstance(ControleInstallateur value){
        this.m_ParentControleInstance = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de controle-instantie in deze boundary-instantie.
     */
    public ControleInstallateur getControleInstance(){
        return this.m_ParentControleInstance;
    }

    /* //----------------// SECTIE: Technische-Properties //----------------// */
}