package be.fastned.application.domain.Andere;

import be.fastned.application.domain.Personen.PersoonFactory;
import be.fastned.application.domain.Technisch.EnumPersoon;
import be.fastned.application.domain.Personen.Persoon;
import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 6.0
 */
public class Register {
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    /**
     * Collectie van actieve & nieuwe Personen. (data-bron voor schermen) */
    public static ArrayList<Persoon> s_ActivePersonen = new ArrayList<Persoon>();
    /**
     * Collectie van verlopen & afgehandelde Personen. (repository voor rollback) */
    public static ArrayList<Persoon> s_ArchivedPersoonPersoon = new ArrayList<Persoon>();

    /* //----------------// -#####- |--------------| -#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####- |--------------| -#####- //----------------// */

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /**
     * Deze Domein-functie registreert een gebruiker in dit systeem */
    public static void registerUser(EnumPersoon type, String emailadres, String gebruikersnaam, String wachtwoord){
        Persoon persoon = PersoonFactory.createPersoon(type, emailadres, gebruikersnaam, wachtwoord);
        persoon.setGebruikersnaam(gebruikersnaam);
        persoon.setWachtwoord(wachtwoord);
        s_ActivePersonen.add(persoon);
    }

    /**
     * Deze Domein-functie schrijft een deze instantie over van de Active-ArrayList naar de Archived-ArrayList.
     * Dit via klasse "ArrayList" via naamgeving "s_ArchivedKlasseItemKlasse" of dit zonder "s_". */
    public void archiveer(){
        this.s_ActivePersonen.remove(this);
    }
}
