package be.fastned.application.domain.PersoonAbstracties;

import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonDefault;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */
public abstract class PersoonDefaultImpl extends PersoonImpl implements PersoonDefault, Persoon {
    /* //----------------// -#####-----------------------------#####- //----------------// */
    /* //----------------// -#####- | ! VERDUIDELIJKINGEN ! | -#####- //----------------// */
    /* //----------------// -#####-----------------------------#####- //----------------// */
	/*
		De collecties van type "ArrayList"? -> (HELP01)
		Wat is een "hf_..."-functie? -> (HELP03)
	*/

    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Properties //----------------// */

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    /* //----------------// -#####- |--------------| -#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####- |--------------| -#####- //----------------// */
    /**
     * Default Constructor voor deze klasse. */
    public PersoonDefaultImpl(){
        super();
    }

    public PersoonDefaultImpl(String emailadres, String gebruikersnaam, String wachtwoord ){
        super(emailadres, gebruikersnaam, wachtwoord);
    }

    public PersoonDefaultImpl(String emailadres, String gebruikersnaam, String wachtwoord, String naam, String voornaam, String geslacht, String gsm ){
        super(emailadres, gebruikersnaam, wachtwoord, naam, voornaam, geslacht, gsm);
    }

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */
    public PersoonDefault identificeer(String voornaam, String naam, String geslacht, String gsm) {
        this.setVoorNaam(voornaam);
        this.setNaam(naam);
        this.setGeslacht(geslacht);
        this.setGsm(gsm);
        return this;
    }
}