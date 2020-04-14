package be.fastned.application.control;

import be.fastned.application.boundary.SchermPlanner;
import be.fastned.application.control.Technisch.ControleBaseExtended;
import be.fastned.application.domain.*;
import be.fastned.application.domain.Personen.Installateur;
import be.fastned.application.domain.Personen.Planner;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonDefault;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TiboVG
 * @version 2.0
 * @created 15-Mar-2020 14:24:52
 */
//@Component("b_ControlePlannerDef")
public class ControlePlanner extends ControleBaseExtended {

    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private SchermPlanner m_SchermPlanner = null;

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
    public ControlePlanner(Persoon b_ActieveGebruiker){
        m_ActieveGebruiker = b_ActieveGebruiker;
        m_SchermPlanner = new SchermPlanner(this);
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
            tf_ToonFoutmelding("methode identificeer in controlePlanner", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie zoekt alle locatietoestemmingen na argumentcontroles.
     * @return Een lijst van alle locatietoestemmingen na argumentcontroles.
     */
    public ArrayList<Locatietoestemming> zoekLocatieToestemmingen(){
        try{
            if (((Planner) m_ActieveGebruiker).toonLocatieToestemmingen() != null){
                return ((Planner) m_ActieveGebruiker).toonLocatieToestemmingen();
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
     * @return De aangemaakt afspraak via deze planner na argumentcontroles.
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
     * Deze Domein-functie maakt een afspraak via deze planner. (Lijst laadpalen ipv laadpaal)
     * @return De aangemaakt afspraak via deze planner.
     */
//    public Afspraak maakAfspraak(ArrayList<Laadpaal> laadpalen, Installateur installateur, Contract contract){
//        try {
//            // Setup technische helper-variabelen
//            StringBuilder samengesteldeError = new StringBuilder();
//            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
//                put(0, "Laadpalen");
//                put(1, "Installateur");
//                put(1, "Contract");
//            }};
//
//            // Check parentAfspraak-/defecteLaadpaal-/probleemBeschrijving-argument via helper functie.
//            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{laadpalen, installateur, contract}, false);
//
//            // Afhandeling: er zijn geen problemen.
//            if (violationIndexes != null){
//                return ((Planner)m_ActieveGebruiker).maakAfspraak(laadpalen, installateur, contract);
//            }
//            // Afhandeling: er zijn problemen opgetreden.
//            else{
//                // Append elk label per probleem (int index) aan een string.
//                violationIndexes.forEach(violIndex -> samengesteldeError.append(String.format("[%s] ", indexToLabel.get(violIndex))));
//                // Throw uiteindelijke error.
//                throw new Exception(String.format("Afspraak is niet aangemaakt vanwege een lege of null-waarde voor %s", samengesteldeError));
//            }
//        }
//        catch (Exception ex){
//            tf_ToonFoutmelding("methode maakAfspraak in controlePlanner", ex.getMessage(), true);
//            return null;
//        }
//    }

    /**
     * Deze Domein-functie zoekt installatieprobleem op basis van een afspraak.
     * @return Het probleem binnen een afspraak via deze planner.
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
     * @return Het probleem binnen een afspraak via deze planner.
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
     * @return Het geüpdatete probleem.
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
     * @return Het geüpdatete/gesloten probleem.
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
     * @return De geüpdatete locatietoestemming.
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
     * @return Het aangemaakte contract binnen een afspraak via deze planner.
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
                return ((Planner)m_ActieveGebruiker).maakContract(contractDatum, installatieDatum, parentAfspraak);
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

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */

    /* //----------------// SECTIE: Domein-Properties //----------------// */

    /**
     * Deze domein-attribuut setter vertegenwoordigt de scherm-instantie in deze controle-instantie.
     */
    public void setSchermPlanner(SchermPlanner value){
        this.m_SchermPlanner = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de scherm-instantie in deze controle-instantie.
     */
    public SchermPlanner getSchermPlanner(){
        return this.m_SchermPlanner;
    }

    /* //----------------// SECTIE: Technische-Properties //----------------// */
}