package be.fastned.application.code.control;

import be.fastned.application.code.boundary.SchermLaadklant;
import be.fastned.application.code.control.Base.ControleBaseImpl;
import be.fastned.application.code.domain.Laadpaal;
import be.fastned.application.code.domain.Laadsessie;
import be.fastned.application.code.domain.Probleem;
import be.fastned.application.code.domain.Personen.Laadklant;
import be.fastned.application.code.domain.Personen.PersoonDefault;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TiboVG
 * @version 6.0
 */

public class ControleLaadklant extends ControleBaseImpl {

    /* //----------------// -#########--------------------------------#########- //----------------// */
    /* //----------------// -#########- &|& INSTANTIE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########--------------------------------#########- //----------------// */

    /* //----------------\\ # ------------------------------ # //----------------\\ */
    /* //----------------\\ # Instantie Base Variabelen # //----------------\\ */
    /* //----------------\\ # ------------------------------ # //----------------\\ */
    private SchermLaadklant schermLaadklant = null;
    private Laadklant actieveGebruiker = null;

    /* //----------------\\ # ------------------------------ # //----------------\\ */
    /* //----------------\\ # Instantie Domein Variabelen # //----------------\\ */
    /* //----------------\\ # ------------------------------ # //----------------\\ */

    /* //----------------// -#########-----------------------------#########- //----------------// */
    /* //----------------// -#########- &|& KLASSE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########-----------------------------#########- //----------------// */

    /* //----------------\\ # --------------------------- # //----------------\\ */
    /* //----------------\\ # Klasse Base Variabelen # //----------------\\ */
    /* //----------------\\ # --------------------------- # //----------------\\ */

    /* //----------------\\ # -------------------------- # //----------------\\ */
    /* //----------------\\ # Functies Domein Variabelen # //----------------\\ */
    /* //----------------\\ # -------------------------- # //----------------\\ */

    /* //----------------// -#########------------------------#########- //----------------// */
    /* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
    /* //----------------// -#########------------------------#########- //----------------// */
    /**
     * Default Constructor voor deze klasse.
     */
    public ControleLaadklant(){
        schermLaadklant = new SchermLaadklant(this);
    }

    /* //----------------// -#########--------------------#########- //----------------// */
    /* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
    /* //----------------// -#########--------------------#########- //----------------// */

    /* //----------------\\ # ------------------ # //----------------\\ */
    /* //----------------\\ # Functies Base # //----------------\\ */
    /* //----------------\\ # ------------------ # //----------------\\ */

    /* //----------------\\ # --------------- # //----------------\\ */
    /* //----------------\\ # Functies Domein # //----------------\\ */
    /* //----------------\\ # --------------- # //----------------\\ */
    /**
     * Deze Domein-functie updated de actieve persoon met persoons-/gebruikersgegevens na argumentencontroles na argumentcontroles.
     */
    public PersoonDefault identificeer(String voornaam, String naam, String geslacht, String gsm){
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

            // Check verschillende gegevens via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{voornaam, naam, geslacht, gsm}, true);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                return actieveGebruiker.identificeer(voornaam, naam, geslacht, gsm);
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

    /* //----------------// -#########----------------------#########- //----------------// */
    /* //----------------// -#########- &|& PROPERTIES &|& -#########- //----------------// */
    /* //----------------// -#########----------------------#########- //----------------// */

    /* //----------------\\ # ------------------ # //----------------\\ */
    /* //----------------\\ # Functies Base # //----------------\\ */
    /* //----------------\\ # ------------------ # //----------------\\ */

    /**
     * Deze domein-attribuut setter vertegenwoordigt de scherm-instantie in deze controle-instantie.
     */
    public void setSchermLaadklant(SchermLaadklant value){
        this.schermLaadklant = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de scherm-instantie in deze controle-instantie.
     */
    public SchermLaadklant getSchermLaadklant(){
        return this.schermLaadklant;
    }

    /* //----------------\\ # --------------- # //----------------\\ */
    /* //----------------\\ # Functies Domein # //----------------\\ */
    /* //----------------\\ # --------------- # //----------------\\ */
}