package be.fastned.application.control;

import be.fastned.application.boundary.SchermLaadklant;
import be.fastned.application.control.Technisch.ControleBaseExtended;
import be.fastned.application.domain.*;
import be.fastned.application.domain.Personen.Laadklant;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonDefault;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TiboVG
 * @version 2.0
 * @created 15-Mar-2020 14:24:54
 */
//@Component("b_ControleLaadklantDef")
public class ControleLaadklant extends ControleBaseExtended {
    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private SchermLaadklant m_SchermLaadklant = null;

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
    public ControleLaadklant(Persoon b_ActieveGebruiker){
        m_ActieveGebruiker = b_ActieveGebruiker;
        m_SchermLaadklant = new SchermLaadklant(this);
    }

    /* //----------------// -#####----------------#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####----------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie updated de actieve persoon met persoons-/gebruikersgegevens na argumentencontroles na argumentcontroles.
     * @return Het aangevulde persoon-object van de actieve gebruiker na argumentencontroles na argumentcontroles.
     */
    public PersoonDefault identificeer(PersoonDefault gegevensOwner, String voornaam, String naam, String geslacht, String gsm){
        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "gegevensOwner(parameter)");
                put(1, "Voornaam");
                put(2, "Familienaam");
                put(3, "Geslacht");
                put(5, "Gsm Nummer");
            }};

            // Check persoon-argument.
            if (gegevensOwner != null )
                throw new Exception("Te updaten persoon is null.");
            // Check verschillende gegevens via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{voornaam, naam, geslacht, gsm}, true);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                return gegevensOwner.identificeer(voornaam, naam, geslacht, gsm);
            }
            // Afhandeling: er zijn problemen opgetreden.
            else{
                // Append elk label per probleem (int index) aan een string.
                violationIndexes.forEach(violIndex -> samengesteldeError.append(String.format("[%s] ", indexToLabel.get(violIndex))));
                // Throw uiteindelijke error.
                throw new Exception(String.format("Persoon is niet geïdentificeerd vanwege een lege of null-waarde voor %s", samengesteldeError));
            }
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode identificeer in controleLaadklant", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie berekent de verwachtte laadtijd op basis van een laadsessie na argumentcontroles.
     * @return De berekende laadtijd als double (kommagetal) na argumentcontroles.
     */
    public Double berekenLaadtijd(Laadsessie laadsessie){
        try{
            // Check laadsessie-argument.
            if (laadsessie != null){
                return laadsessie.berekenLaadtijd();
            }
            else
                throw new Exception("Berekende Laadtijd is niet opgevraagd vanwege een lege of null-waarde voor laadsessie.");
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode berekenLaadtijd in controleLaadklant", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie maakt een probleem via deze laadklant na argumentcontroles.
     * @return Het aangemaakt probleem via deze laadklant na argumentcontroles.
     */
    public Probleem maakProbleem(Laadpaal defecteLaadpaal, String probleemBeschrijving){
        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "Laadpaal");
                put(1, "Beschrijving van het probleem");
            }};

            // Check parentAfspraak-/defecteLaadpaal-/probleemBeschrijving-argument via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{defecteLaadpaal, probleemBeschrijving}, false);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                return ((Laadklant)m_ActieveGebruiker).maakProbleem(defecteLaadpaal, probleemBeschrijving);
            }
            // Afhandeling: er zijn problemen opgetreden.
            else{
                // Append elk label per probleem (int index) aan een string.
                violationIndexes.forEach(violIndex -> samengesteldeError.append(String.format("[%s] ", indexToLabel.get(violIndex))));
                // Throw uiteindelijke error.
                throw new Exception(String.format("Probleem is niet aangemaakt door een lege of null-waarde voor %s", samengesteldeError));
            }
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode maakProbleem in controleLaadklant", ex.getMessage(), true);
            return null;
        }
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */

    /* //----------------// SECTIE: Domein-Properties //----------------// */

    /**
     * Deze domein-attribuut setter vertegenwoordigt de scherm-instantie in deze controle-instantie.
     */
    public void setSchermLaadklant(SchermLaadklant value){
        this.m_SchermLaadklant = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de scherm-instantie in deze controle-instantie.
     */
    public SchermLaadklant getSchermLaadklant(){
        return this.m_SchermLaadklant;
    }

    /* //----------------// SECTIE: Technische-Properties //----------------// */
}