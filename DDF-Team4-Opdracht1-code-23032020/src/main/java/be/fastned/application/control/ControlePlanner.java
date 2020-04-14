package be.fastned.application.control;

import be.fastned.application.boundary.SchermPlanner;
import be.fastned.application.control.Base.ControleBaseImpl;
import be.fastned.application.domain.AndereEntiteiten.*;
import be.fastned.application.domain.PersoonEntiteiten.Installateur;
import be.fastned.application.domain.PersoonEntiteiten.Planner;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonDefault;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static be.fastned.application.control.Base.ControleBaseImpl.BEAN_CONTROLEPLANNER;

/**
 * @author TiboVG
 * @version 6.0
 */

public class ControlePlanner extends ControleBaseImpl {

    /* //----------------// -#########--------------------------------#########- //----------------// */
    /* //----------------// -#########- &|& INSTANTIE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########--------------------------------#########- //----------------// */

    /* //----------------\\ # ------------------------------ # //----------------\\ */
    /* //----------------\\ # Instantie Technisch Variabelen # //----------------\\ */
    /* //----------------\\ # ------------------------------ # //----------------\\ */
    private SchermPlanner schermPlanner = null;
    private Planner actieveGebruiker = null;

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
    public ControlePlanner(){
        schermPlanner = new SchermPlanner(this);
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
                put(0, "Voornaam");
                put(1, "Familienaam");
                put(2, "Geslacht");
                put(3, "Gsm Nummer");
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
            tf_ToonFoutmelding("Methode identificeer in controlePlanner", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie zoekt alle locatietoestemmingen na argumentcontroles.
     */
    public ArrayList<Locatietoestemming> zoekLocatieToestemmingen(){
        try{
            if ((actieveGebruiker).toonLocatieToestemmingen() != null){
                return (actieveGebruiker).toonLocatieToestemmingen();
            }
            else
                throw new Exception("Locatietoestemming is niet opgevraagd vanwege een lege of null-waarde voor de lijst van locatietoestemmingen.");
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode zoekLocatieToestemmingen in controlePlanner", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie maakt een afspraak via deze planner na argumentcontroles.
     */
    public Afspraak maakAfspraak(Laadpaal laadpaal, Installateur installateur, Contract contract){
        try {
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "Laadpaal");
                put(1, "Installateur");
                put(1, "Contract");
            }};

            // Check parentAfspraak-/defecteLaadpaal-/probleemBeschrijving-argument via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{laadpaal, installateur, contract}, false);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                return ((Planner)m_ActieveGebruiker).maakAfspraak(laadpaal, installateur, contract);
            }
            // Afhandeling: er zijn problemen opgetreden.
            else{
                // Append elk label per probleem (int index) aan een string.
                violationIndexes.forEach(violIndex -> samengesteldeError.append(String.format("[%s] ", indexToLabel.get(violIndex))));
                // Throw uiteindelijke error.
                throw new Exception(String.format("Afspraak is niet aangemaakt vanwege een lege of null-waarde voor %s", samengesteldeError));
            }
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode maakAfspraak in controlePlanner", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie zoekt installatieprobleem op basis van een afspraak.
     */
    public Probleem zoekInstallatieProbleem(Afspraak afspraak){
        try{
            if (afspraak != null){
                return afspraak.getInstallatie().getProbleem();
            }
            else
                throw new Exception("Installatie-Probleem is niet opgevraagd vanwege een lege of null-waarde voor afspraak.");
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode zoekInstallatieProbleem in controlePlanner", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie zoekt reparatieprobleem op basis van een afspraak.
     */
    public Probleem zoekReparatieProbleem(Afspraak afspraak){
        try{
            if (afspraak != null){
                return afspraak.getReparatie().getProbleem();
            }
            else
                throw new Exception("Reparatie-Probleem is niet opgevraagd vanwege een lege of null-waarde voor afspraak.");
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode zoekReparatieProbleem in controlePlanner", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie updated een status van een probleem via deze planner.
     */
    public Probleem evalueerProbleem(Probleem probleem, String status){
        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "Probleem");
                put(1, "Status");
            }};

            // Check parentAfspraak-/defecteLaadpaal-/probleemBeschrijving-argument via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{probleem, status}, false);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                probleem.setStatus(status);
                return probleem;
            }
            // Afhandeling: er zijn problemen opgetreden.
            else{
                // Append elk label per probleem (int index) aan een string.
                violationIndexes.forEach(violIndex -> samengesteldeError.append(String.format("[%s] ", indexToLabel.get(violIndex))));
                // Throw uiteindelijke error.
                throw new Exception(String.format("Probleem-Status is niet gëupdated vanwege een lege of null-waarde voor %s", samengesteldeError));
            }
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode evalueerProbleem in controlePlanner", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie updated een status (automatisch) van een probleem via deze planner.
     */
    public void sluitProbleem(Probleem probleem){
        try{
            if (probleem != null){
                probleem.setStatus("probleem gesloten");
            }
            else
                throw new Exception("Probleem-Status is niet gesloten vanwege een lege of null-waarde voor probleem.");
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode sluitProbleem in controlePlanner", ex.getMessage(), true);
        }
    }

    /**
     * Deze Domein-functie updated een status van een locatietoestemming via deze planner.
     */
    public Locatietoestemming evalueerAanmelding(Locatietoestemming aanmelding, String status){
        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "Locatieaanmelding");
                put(1, "Status");
            }};

            // Check parentAfspraak-/defecteLaadpaal-/probleemBeschrijving-argument via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{aanmelding, status}, false);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                aanmelding.setStatus(status);
                return aanmelding;
            }
            // Afhandeling: er zijn problemen opgetreden.
            else{
                // Append elk label per probleem (int index) aan een string.
                violationIndexes.forEach(violIndex -> samengesteldeError.append(String.format("[%s] ", indexToLabel.get(violIndex))));
                // Throw uiteindelijke error.
                throw new Exception(String.format("Locatietoestemming-Status is niet gëupdated vanwege een lege of null-waarde voor %s", samengesteldeError));
            }
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode evalueerAanmelding in controlePlanner", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie maakt een contract via deze planner binnen een afspraak.
     */
    public Contract maakContract(LocalDateTime contractDatum, LocalDateTime installatieDatum, Afspraak parentAfspraak){
        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "Contractdatum");
                put(1, "Installatiedatum");
                put(1, "Afspraak");
            }};

            // Check parentAfspraak-/defecteLaadpaal-/probleemBeschrijving-argument via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{contractDatum, installatieDatum, parentAfspraak}, false);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                return (actieveGebruiker).maakContract(contractDatum, installatieDatum, parentAfspraak);
            }
            // Afhandeling: er zijn problemen opgetreden.
            else{
                // Append elk label per probleem (int index) aan een string.
                violationIndexes.forEach(violIndex -> samengesteldeError.append(String.format("[%s] ", indexToLabel.get(violIndex))));
                // Throw uiteindelijke error.
                throw new Exception(String.format("Contract is niet aangemaakt vanwege een lege of null-waarde voor %s", samengesteldeError));
            }
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode maakContract in controlePlanner", ex.getMessage(), true);
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
     * Deze domein-attribuut setter vertegenwoordigt de scherm-instantie in deze instantie.
     */
    public void setSchermPlanner(SchermPlanner value){
        this.schermPlanner = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de scherm-instantie in deze instantie.
     */
    public SchermPlanner getSchermPlanner(){
        return this.schermPlanner;
    }

    /* //----------------\\ # --------------- # //----------------\\ */
    /* //----------------\\ # Functies Domein # //----------------\\ */
    /* //----------------\\ # --------------- # //----------------\\ */
}