package be.fastned.application.boundary;

import be.fastned.application.domain.Persoon;
import be.fastned.application.control.ControleRegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("schermRegisterUserInst")
public class SchermRegisterUser {
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    @Autowired
    private ControleRegisterUser controleRegisterUserInst;

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
    public SchermRegisterUser(){

    }

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /**
     * Deze Domein-functie retourneert deze met-persoongegevens-aangevulde persoon. */
    public Persoon register(String gebruikersnaam, String wachtwoord, String type, String voornaam, String naam, String geslacht, String emailAdres, String gsm){
        return controleRegisterUserInst.register(gebruikersnaam, wachtwoord, type, voornaam, naam, geslacht, emailAdres, gsm);
    }
    public Persoon register(String gebruikersnaam, String wachtwoord, String adres, String bedrijfsNaam, String BTWNummer, String type, String voornaam, String naam, String geslacht, String emailAdres, String gsm){
        return controleRegisterUserInst.register(gebruikersnaam, wachtwoord, type, adres, bedrijfsNaam, BTWNummer, voornaam, naam, geslacht, emailAdres, gsm);
    }

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */
}
