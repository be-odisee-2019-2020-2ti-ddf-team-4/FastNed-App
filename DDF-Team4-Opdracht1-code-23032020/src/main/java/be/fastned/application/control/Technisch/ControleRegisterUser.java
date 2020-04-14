package be.fastned.application.control.Technisch;

import be.fastned.application.boundary.Technisch.SchermRegisterUser;
import be.fastned.application.domain.PersoonAbstracties.EnumPersoon;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;
import be.fastned.application.domain.PersoonAbstracties.PersoonFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TiboVG
 * @version 2.0
 * @created 15-Mar-2020 14:24:54
 */
@Component("b_ControleRegisterUserInst")
public class ControleRegisterUser extends ControleBase {

    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private SchermRegisterUser m_SchermLaadklant = null;

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
    public ControleRegisterUser(){
        m_SchermLaadklant = new SchermRegisterUser(this);
    }

    /* //----------------// -#####----------------#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####----------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie retourneert een aangemaakte persoon.
     * @return De aangemaakte persoon.
     */
    public Persoon register(EnumPersoon type, String emailAdres, String gebruikersnaam, String wachtwoord, String voornaam, String naam, String geslacht, String gsm){
        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "Persoon Type");
                put(1, "Emailadres");
                put(2, "Gebruikersnaam");
                put(3, "Wachtwoord");
                put(4, "Voornaam");
                put(5, "Familienaam");
                put(6, "Geslacht");
                put(7, "Gsm Nummer");
            }};

            // Check parentAfspraak-/defecteLaadpaal-/probleemBeschrijving-argument via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{type, emailAdres, gebruikersnaam, wachtwoord, voornaam, naam, geslacht, gsm}, false);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                return PersoonFactory.createPersoon(type, emailAdres, gebruikersnaam, wachtwoord, voornaam, naam, geslacht, gsm);
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
            tf_ToonFoutmelding("methode register in controleRegisterUser", ex.getMessage(), true);
            return null;
        }
    }
    /**
     * Deze Domein-functie retourneert een aangemaakte persoon. (extra param adres/bedrijfsnaam/BTWNummer)
     * @return De aangemaakte persoon. (extra param adres/bedrijfsnaam/BTWNummer)
     */
    public Persoon register ( EnumPersoon type, String emailAdres, String gebruikersnaam, String wachtwoord, String adres, String bedrijfsNaam, String btwNummer, String voornaam, String naam, String geslacht, String gsm){
        try{
            // Setup technische helper-variabelen
            StringBuilder samengesteldeError = new StringBuilder();
            Map<Integer, String> indexToLabel = new HashMap<Integer, String>() {{
                put(0, "Persoon Type");
                put(1, "Emailadres");
                put(2, "Gebruikersnaam");
                put(3, "Wachtwoord");
                put(4, "Adres");
                put(5, "Bedrijfsnaam");
                put(6, "BTW Nummer");
                put(7, "Voornaam");
                put(8, "Familienaam");
                put(9, "Geslacht");
                put(10, "Gsm Nummer");
            }};

            // Check parentAfspraak-/defecteLaadpaal-/probleemBeschrijving-argument via helper functie.
            ArrayList<Integer> violationIndexes = tf_checkAgainstNullOrEmpty(new Object[]{type, emailAdres, gebruikersnaam, wachtwoord, adres, bedrijfsNaam, btwNummer, voornaam, naam, geslacht, gsm}, false);

            // Afhandeling: er zijn geen problemen.
            if (violationIndexes != null){
                return (Persoon) PersoonFactory.createPersoon(type, emailAdres, gebruikersnaam, wachtwoord, adres, bedrijfsNaam, btwNummer, voornaam, naam, geslacht, gsm);
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
            tf_ToonFoutmelding("methode register in controleRegisterUser", ex.getMessage(), true);
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
    public void setSchermRegisterUser(SchermRegisterUser value){
        this.m_SchermLaadklant = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de scherm-instantie in deze controle-instantie.
     */
    public SchermRegisterUser getSchermRegisterUser(){
        return this.m_SchermLaadklant;
    }

    /* //----------------// SECTIE: Technische-Properties //----------------// */
}
