package be.fastned.application.boundary;

import be.fastned.application.boundary.Technisch.SchermBase;
import be.fastned.application.control.ControleLaadklant;
import be.fastned.application.domain.AndereEntiteiten.Laadpaal;
import be.fastned.application.domain.AndereEntiteiten.Laadsessie;
import be.fastned.application.domain.AndereEntiteiten.Probleem;
import be.fastned.application.domain.PersoonEntiteiten.PersoonDefault;

/**
 * @author TiboVG
 * @version 2.0
 * @created 15-Mar-2020 14:24:55
 */
public class SchermLaadklant extends SchermBase {
    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private ControleLaadklant m_ParentControleInstance = null;

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
    public SchermLaadklant(ControleLaadklant parentControleLaadklant){
        m_ParentControleInstance = parentControleLaadklant;
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
     * Deze Domein-functie berekent de verwachtte laadtijd op basis van een laadsessie.
     * @return De berekende laadtijd als double (kommagetal).
     */
    public double berekenLaadtijd(Laadsessie laadsessie){
        return m_ParentControleInstance.berekenLaadtijd(laadsessie);
    }

    /**
     * Deze Domein-functie maakt een probleem via deze laadklant.
     * @return Het aangemaakt probleem via deze laadklant.
     */
    public Probleem maakProbleem(Laadpaal laadpaal, String beschrijving){
        return m_ParentControleInstance.maakProbleem(laadpaal, beschrijving);
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####------------------#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Properties //----------------// */
    /**
     * Deze domein-attribuut setter vertegenwoordigt de controle-instantie in deze boundary-instantie.
     */
    public void setControleInstance(ControleLaadklant value){
        this.m_ParentControleInstance = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de controle-instantie in deze boundary-instantie.
     */
    public ControleLaadklant getControleInstance(){
        return this.m_ParentControleInstance;
    }

    /* //----------------// SECTIE: Technische-Properties //----------------// */
}