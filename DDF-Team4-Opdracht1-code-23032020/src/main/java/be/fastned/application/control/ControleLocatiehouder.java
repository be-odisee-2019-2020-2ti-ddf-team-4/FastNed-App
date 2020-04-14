package be.fastned.application.control;

import be.fastned.application.boundary.SchermLocatiehouder;
import be.fastned.application.control.Base.ControleBaseImpl;
import be.fastned.application.domain.AndereEntiteiten.Laadpaal;
import be.fastned.application.domain.AndereEntiteiten.Locatietoestemming;
import be.fastned.application.domain.AndereEntiteiten.Probleem;
import be.fastned.application.domain.PersoonEntiteiten.Locatiehouder;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonProfessional;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static be.fastned.application.control.Base.ControleBaseImpl.BEAN_CONTROLELOCATIEHOUDER;

/**
 * @author TiboVG
 * @version 6.0
 */

public class ControleLocatiehouder extends ControleBaseImpl {

    /* //----------------// -#########--------------------------------#########- //----------------// */
    /* //----------------// -#########- &|& INSTANTIE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########--------------------------------#########- //----------------// */

    /* //----------------\\ # ------------------------------ # //----------------\\ */
    /* //----------------\\ # Instantie Technisch Variabelen # //----------------\\ */
    /* //----------------\\ # ------------------------------ # //----------------\\ */
    private SchermLocatiehouder schermLocatiehouder = null;
    private Locatiehouder actieveGebruiker = null;
    // TODO ActieveGebruiker

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
    public ControleLocatiehouder(){
        schermLocatiehouder = new SchermLocatiehouder(this);
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
    public PersoonProfessional identificeer(String adres, String bedrijfsNaam, String btwNummer, String voornaam, String naam, String geslacht, String gsm){
        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "Adres");
                put(1, "Bedrijfsnaam");
                put(2, "BTW Nummer");
                put(3, "Voornaam");
                put(4, "Familienaam");
                put(5, "Geslacht");
                put(6, "Gsm Nummer");
            }};

            // Check verschillende gegevens via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{adres, bedrijfsNaam, btwNummer, voornaam, naam, geslacht, gsm}, true);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                return actieveGebruiker.identificeer(adres, bedrijfsNaam, btwNummer, voornaam, naam, geslacht, gsm);
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
            tf_ToonFoutmelding("methode identificeer in controleLocatiehouder", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie maakt een locatietoestemming via deze locatiehouder na argumentcontroles.
     */
    public Locatietoestemming maakLocatieAan(Integer aantalLaadpalen, String typeLaadpaal){
        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "Aantal laadpalen");
                put(1, "Type laadpaal");
            }};

            // Check parentAfspraak-/defecteLaadpaal-/probleemBeschrijving-argument via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{aantalLaadpalen, typeLaadpaal}, false);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                return ((Locatiehouder)m_ActieveGebruiker).maakLocatietoestemming(aantalLaadpalen, typeLaadpaal);
            }
            // Afhandeling: er zijn problemen opgetreden.
            else{
                // Append elk label per probleem (int index) aan een string.
                violationIndexes.forEach(violIndex -> samengesteldeError.append(String.format("[%s] ", indexToLabel.get(violIndex))));
                // Throw uiteindelijke error.
                throw new Exception(String.format("Locatietoestemming is niet aangemaakt vanwege een lege of null-waarde voor %s", samengesteldeError));
            }
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode maakLocatieAan in controleLocatiehouder", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie toont het resultaat van een aanmelding na argumentcontroles.
     */
    public String toonAanmeldingResultaat(Locatietoestemming aanmelding){

        try{
            if (aanmelding != null){
                return aanmelding.getStatus();
            }
            else
                throw new Exception("Aanmelding-Resultaat is niet opgevraagd vanwege een lege of null-waarde voor aanmelding.");
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode toonAanmeldingResultaat in controleLocatiehouder", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie maakt een probleem via deze Locatiehouder na argumentcontroles.
     */
    public Probleem maakProbleem(Laadpaal defecteLaadpaal, String probleemBeschrijving, Persoon probMelder){

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
                Probleem probleem = new Probleem(defecteLaadpaal, probleemBeschrijving);
                ((Locatiehouder)m_ActieveGebruiker).getActieveProblemen().add(probleem);
                return probleem;
            }
            // Afhandeling: er zijn problemen opgetreden.
            else{
                // Append elk label per probleem (int index) aan een string.
                violationIndexes.forEach(violIndex -> samengesteldeError.append(String.format("[%s] ", indexToLabel.get(violIndex))));
                // Throw uiteindelijke error.
                throw new Exception(String.format("Probleem is niet aangemaakt vanwege een lege of null-waarde voor %s", samengesteldeError));
            }
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode maakProbleem in controleLocatiehouder", ex.getMessage(), true);
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
    public void setSchermLocatiehouder(SchermLocatiehouder value){
        this.schermLocatiehouder = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de scherm-instantie in deze controle-instantie.
     */
    public SchermLocatiehouder getSchermLocatiehouder(){
        return this.schermLocatiehouder;
    }

    /* //----------------\\ # --------------- # //----------------\\ */
    /* //----------------\\ # Functies Domein # //----------------\\ */
    /* //----------------\\ # --------------- # //----------------\\ */
}