package be.fastned.application.control;

import be.fastned.application.boundary.SchermLaadklant;
import be.fastned.application.control.Base.ControleBaseImpl;
import be.fastned.application.domain.AndereEntiteiten.Laadpaal;
import be.fastned.application.domain.AndereEntiteiten.Laadsessie;
import be.fastned.application.domain.AndereEntiteiten.Probleem;
import be.fastned.application.domain.PersoonEntiteiten.Laadklant;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonDefault;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static be.fastned.application.control.Base.ControleBaseImpl.BEAN_CONTROLELAADKLANT;

/**
 * @author TiboVG
 * @version 6.0
 */

@Component(BEAN_CONTROLELAADKLANT)
public class ControleLaadklant extends ControleBaseImpl {

    /* //----------------// -#########--------------------------------#########- //----------------// */
    /* //----------------// -#########- &|& INSTANTIE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########--------------------------------#########- //----------------// */

    /* //----------------\\ # ------------------------------ # //----------------\\ */
    /* //----------------\\ # Instantie Technisch Variabelen # //----------------\\ */
    /* //----------------\\ # ------------------------------ # //----------------\\ */
    private SchermLaadklant schermLaadklant = null;
    private Laadklant actieveGebruiker = (Laadklant)BEAN_ACTIEVE_GEBRUIKER;

    /* //----------------\\ # ------------------------------ # //----------------\\ */
    /* //----------------\\ # Instantie Domein Variabelen # //----------------\\ */
    /* //----------------\\ # ------------------------------ # //----------------\\ */

    /* //----------------// -#########-----------------------------#########- //----------------// */
    /* //----------------// -#########- &|& KLASSE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########-----------------------------#########- //----------------// */

    /* //----------------\\ # --------------------------- # //----------------\\ */
    /* //----------------\\ # Klasse Technisch Variabelen # //----------------\\ */
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
    /* //----------------\\ # Functies Technisch # //----------------\\ */
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
    /* //----------------\\ # Functies Technisch # //----------------\\ */
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