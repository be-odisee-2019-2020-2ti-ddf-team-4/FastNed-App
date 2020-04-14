package be.fastned.application.domain.AndereEntiteiten;

import be.fastned.application.domain.PersoonAbstracties.EnumPersoon;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;
import be.fastned.application.domain.PersoonAbstracties.PersoonFactory;


import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
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
