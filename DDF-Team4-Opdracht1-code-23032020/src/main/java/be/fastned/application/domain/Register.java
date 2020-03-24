package be.fastned.application.domain;

import be.fastned.application.domain.custom.ArrayListExtended;

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
    public static ArrayListExtended<Persoon, Persoon> s_ActivePersonen = new ArrayListExtended<Persoon, Persoon>();
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
    public static void registerUser(String gebruikersnaam, String wachtwoord, String accountType){
        Persoon persoon = PersoonFactory.createPersoon(accountType);
        persoon.setGebruikersnaam(gebruikersnaam);
        persoon.setWachtwoord(wachtwoord);
        s_ActivePersonen.add(persoon);
    }

    /**
     * Deze Domein-functie schrijft een deze instantie over van de Active-ArrayList naar de Archived-ArrayList.
     * Dit via klasse "ArrayListExtended" via naamgeving "s_ArchivedKlasseItemKlasse" of dit zonder "s_". */
    public void archiveer(){
        this.s_ActivePersonen.removeWrapped(Register.class, Register.class, true);
    }
}
