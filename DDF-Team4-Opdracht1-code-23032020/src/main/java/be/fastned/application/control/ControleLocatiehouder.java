package be.fastned.application.control;

import be.fastned.application.boundary.SchermLocatiehouder;
import be.fastned.application.control.Technisch.ControleBaseExtended;
import be.fastned.application.domain.*;
import be.fastned.application.domain.Personen.Locatiehouder;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonProfessional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TiboVG
 * @version 2.0
 * @created 15-Mar-2020 14:24:54
 */
//@Component("b_ControleLocatiehouderDef")
public class ControleLocatiehouder extends ControleBaseExtended {
    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private SchermLocatiehouder m_SchermLocatiehouder = null;

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
    public ControleLocatiehouder(Persoon b_ActieveGebruiker){
        m_ActieveGebruiker = b_ActieveGebruiker;
        m_SchermLocatiehouder = new SchermLocatiehouder(this);
    }

    /* //----------------// -#####----------------#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####----------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie updated de actieve persoon met persoons-/gebruikersgegevens na argumentencontroles na argumentcontroles.
     * @return Het aangevulde persoon-object van de actieve gebruiker na argumentencontroles na argumentcontroles.
     */
    public PersoonProfessional identificeer(PersoonProfessional gegevensOwner, String adres, String bedrijfsNaam, String btwNummer, String voornaam, String naam, String geslacht, String gsm){
        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "gegevensOwner(parameter)");
                put(1, "Adres");
                put(2, "Bedrijfsnaam");
                put(3, "BTW Nummer");
                put(4, "Voornaam");
                put(5, "Familienaam");
                put(6, "Geslacht");
                put(8, "Gsm Nummer");
            }};

            // Check persoon-argument.
            if (gegevensOwner != null )
                throw new Exception("Te updaten persoon is null.");
            // Check verschillende gegevens via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{adres, bedrijfsNaam, btwNummer, voornaam, naam, geslacht, gsm}, true);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                return gegevensOwner.identificeer(adres, bedrijfsNaam, btwNummer, voornaam, naam, geslacht, gsm);
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
     * @return De aangemaakt locatietoestemming via deze locatiehouder na argumentcontroles.
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
     * @return De statusproperty via van een locatietoestemming na argumentcontroles.
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
     * @return Het aangemaakt probleem via deze Locatiehouder na argumentcontroles.
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

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */

    /* //----------------// SECTIE: Domein-Properties //----------------// */

    /**
     * Deze domein-attribuut setter vertegenwoordigt de scherm-instantie in deze controle-instantie.
     */
    public void setSchermLocatiehouder(SchermLocatiehouder value){
        this.m_SchermLocatiehouder = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de scherm-instantie in deze controle-instantie.
     */
    public SchermLocatiehouder getSchermLocatiehouder(){
        return this.m_SchermLocatiehouder;
    }

    /* //----------------// SECTIE: Technische-Properties //----------------// */
}