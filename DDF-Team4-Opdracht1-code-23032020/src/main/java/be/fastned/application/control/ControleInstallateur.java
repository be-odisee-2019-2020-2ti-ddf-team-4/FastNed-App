package be.fastned.application.control;

import be.fastned.application.boundary.SchermInstallateur;
import be.fastned.application.control.Technisch.ControleBaseExtended;
import be.fastned.application.domain.*;

import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author TiboVG
 * @version 2.0
 * @created 15-Mar-2020 14:24:52
 */
//@Component("b_ControleInstallateurDef")
public class ControleInstallateur extends ControleBaseExtended {
    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private SchermInstallateur m_SchermInstallateur = null;

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
    public ControleInstallateur(Persoon b_ActieveGebruiker){
        System.out.println("TEST");
        m_ActieveGebruiker = b_ActieveGebruiker;
        m_SchermInstallateur = new SchermInstallateur(this);
    }

    /* //----------------// -#####----------------#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####----------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie updated de actieve persoon met persoons-/gebruikersgegevens na argumentencontroles.
     * @return Het aangevulde persoon-object van de actieve gebruiker na argumentencontroles.
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
            tf_ToonFoutmelding("methode identificeer in controleInstallateur", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie zoekt installatiedocumentatie op basis van een laadpaal na argumentcontroles.
     * @return De installatiedocumentatie uit de documentatierepository binnen een laadpaal na argumentcontroles.
     */
    public String zoekInstallatieDoc(Laadpaal laadpaal){
        try{
            // Check laadpaal-argument.
            if (laadpaal != null){
                return laadpaal.getInstallatieDoc();
            }
            else
                throw new Exception("Installatiedocumentatie is niet opgehaald vanwege een lege of null-waarde voor laadpaal.");
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode zoekInstallatieDoc in controleInstallateur", ex.getMessage(), true);
            return null;
        }
    }
    /**
     * Deze Domein-functie zoekt reparatiedocumentatie op basis van een laadpaal na argumentcontroles.
     * @return De reparatiedocumentatie uit de documentatierepository binnen een laadpaal na argumentcontroles.
     */
    public String zoekReparatieDoc(Laadpaal laadpaal){
        // Check laadpaal-argument.
        try{
            if (laadpaal != null){
                return laadpaal.getReparatieDoc();
            }
            else
                throw new Exception("Reparatiedocumentatie is niet opgehaald vanwege een lege of null-waarde voor laadpaal.");
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode zoekReparatieDoc in controleInstallateur", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie maakt een probleem vanuit een installatie binnen een afspraak na argumentcontroles.
     * @return Het aangemaakt probleem vanuit een installatie binnen een afspraak na argumentcontroles.
     */
    public Probleem maakInstallatieProbleem(Afspraak parentAfspraak, Laadpaal defecteLaadpaal, String probleemBeschrijving){
        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "parentAfspraak(parameter)");
                put(1, "Laadpaal");
                put(2, "Beschrijving van het probleem");
            }};

            // Check parentAfspraak-/defecteLaadpaal-/probleemBeschrijving-argument via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{parentAfspraak, defecteLaadpaal, probleemBeschrijving}, false);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                parentAfspraak.getInstallatie().setProbleem(new Probleem(defecteLaadpaal, probleemBeschrijving));
                return parentAfspraak.getInstallatie().getProbleem();
            }
            // Afhandeling: er zijn problemen opgetreden.
            else{
                // Append elk label per probleem (int index) aan een string.
                violationIndexes.forEach(violIndex -> samengesteldeError.append(String.format("[%s] ", indexToLabel.get(violIndex))));
                // Throw uiteindelijke error.
                throw new Exception(String.format("Installatie-Probleem is niet aangemaakt vanwege een lege of null-waarde voor %s", samengesteldeError));
            }
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode maakInstallatieProbleem in controleInstallateur", ex.getMessage(), true);
            return null;
        }
    }
    /**
     * Deze Domein-functie maakt een probleem vanuit een reparatie binnen een afspraak na argumentcontroles.
     * @return Het aangemaakt probleem vanuit een reparatie binnen een afspraak na argumentcontroles.
     */
    public Probleem maakReparatieProbleem(Afspraak parentAfspraak, Laadpaal defecteLaadpaal, String probleemBeschrijving){
        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "parentAfspraak(parameter)");
                put(1, "Laadpaal");
                put(2, "Beschrijving van het probleem");
            }};

            // Check parentAfspraak-/defecteLaadpaal-/probleemBeschrijving-argument via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{parentAfspraak, defecteLaadpaal, probleemBeschrijving}, false);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                parentAfspraak.getReparatie().setProbleem(new Probleem(defecteLaadpaal, probleemBeschrijving));
                return parentAfspraak.getReparatie().getProbleem();
            }
            // Afhandeling: er zijn problemen opgetreden.
            else{
                // Append elk label per probleem (int index) aan een string.
                violationIndexes.forEach(violIndex -> samengesteldeError.append(String.format("[%s] ", indexToLabel.get(violIndex))));
                // Throw uiteindelijke error.
                throw new Exception(String.format("Reparatie-Probleem is niet aangemaakt vanwege een lege of null-waarde voor %s", samengesteldeError));
            }
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode maakReparatieProbleem in controleInstallateur", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie maakt een oplossing vanuit een installatie binnen een probleem in een afspraak na argumentcontroles.
     * @return De aangemaakte oplossing vanuit een installatie binnen een probleem in een afspraak na argumentcontroles.
     */
    public Oplossing maakInstallatieOplossing(Afspraak parentAfspraak, String oplossingBeschrijving){

        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "parentAfspraak(parameter)");
                put(1, "Beschrijving van de oplossing");
            }};

            // Check parentAfspraak-/defecteLaadpaal-/probleemBeschrijving-argument via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{parentAfspraak, parentAfspraak, oplossingBeschrijving}, false);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                // TODO Fix null
                parentAfspraak.getInstallatie().getProbleem().setOplossing(new Oplossing(oplossingBeschrijving));
                return parentAfspraak.getInstallatie().getProbleem().getOplossing();
            }
            // Afhandeling: er zijn problemen opgetreden.
            else{
                // Append elk label per probleem (int index) aan een string.
                violationIndexes.forEach(violIndex -> samengesteldeError.append(String.format("[%s] ", indexToLabel.get(violIndex))));
                // Throw uiteindelijke error.
                throw new Exception(String.format("Installatie-Oplossing is niet aangemaakt vanwege een lege of null-waarde voor %s", samengesteldeError));
            }
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode maakInstallatieOplossing in controleInstallateur", ex.getMessage(), true);
            return null;
        }
    }
    /**
     * Deze Domein-functie maakt een oplossing vanuit een reparatie binnen een probleem in een afspraak na argumentcontroles.
     * @return De aangemaakte oplossing vanuit een reparatie binnen een probleem in een afspraak na argumentcontroles.
     */
    public Oplossing maakReparatieOplossing(Afspraak parentAfspraak, String oplossingBeschrijving){
        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "parentAfspraak(parameter)");
                put(1, "Beschrijving van de oplossing");
            }};

            // Check parentAfspraak-/defecteLaadpaal-/probleemBeschrijving-argument via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{parentAfspraak, parentAfspraak, oplossingBeschrijving}, false);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                // TODO Fix null
                parentAfspraak.getReparatie().getProbleem().setOplossing(new Oplossing(oplossingBeschrijving));
                return parentAfspraak.getReparatie().getProbleem().getOplossing();
            }
            // Afhandeling: er zijn problemen opgetreden.
            else{
                // Append elk label per probleem (int index) aan een string.
                violationIndexes.forEach(violIndex -> samengesteldeError.append(String.format("[%s] ", indexToLabel.get(violIndex))));
                // Throw uiteindelijke error.
                throw new Exception(String.format("Reparatie-Oplossing is niet aangemaakt vanwege een lege of null-waarde voor %s", samengesteldeError));
            }
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode maakReparatieOplossing in controleInstallateur", ex.getMessage(), true);
            return null;
        }
    }

    /**
     * Deze Domein-functie updated een afspraak-status binnen een afspraak.
     * @return De geüpdatete afspraak.
     */
    public Afspraak updateAfspraakStatus(Afspraak afspraak, String nieuweStatus){
        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "Afspraak");
                put(1, "De nieuwe status");
            }};

            // Check parentAfspraak-/defecteLaadpaal-/probleemBeschrijving-argument via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{afspraak, nieuweStatus}, false);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                afspraak.setStatus(nieuweStatus);
                return afspraak;
            }
            // Afhandeling: er zijn problemen opgetreden.
            else{
                // Append elk label per probleem (int index) aan een string.
                violationIndexes.forEach(violIndex -> samengesteldeError.append(String.format("[%s] ", indexToLabel.get(violIndex))));
                // Throw uiteindelijke error.
                throw new Exception(String.format("Afspraak-Status is niet geüpdated vanwege een lege of null-waarde voor %s", samengesteldeError));
            }
        }
        catch (Exception ex){
            tf_ToonFoutmelding("methode updateAfspraakStatus in controleInstallateur", ex.getMessage(), true);
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
    public void setSchermInstallateur(SchermInstallateur value){
        this.m_SchermInstallateur = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de scherm-instantie in deze controle-instantie.
     */
    public SchermInstallateur getSchermInstallateur(){
        return this.m_SchermInstallateur;
    }

    /* //----------------// SECTIE: Technische-Properties //----------------// */
}