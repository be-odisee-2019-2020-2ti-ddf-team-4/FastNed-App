package be.fastned.application.domain;

import org.springframework.stereotype.Component;

@Component("persoonFactoryInst")
public class PersoonFactory {

    public static Persoon createPersoon(String gebruikersnaam, String wachtwoord, String type, String naam, String voornaam, String geslacht, String emailadres, String gsm){
        if (type == "Installateur"){
            return new Installateur(gebruikersnaam, wachtwoord, naam, voornaam, geslacht, emailadres, gsm);
        }
        else if (type == "Planner"){
            return new Planner(gebruikersnaam, wachtwoord, naam, voornaam, geslacht, emailadres, gsm);
        }
        else if (type == "Laadklant"){
            return new Laadklant(gebruikersnaam, wachtwoord, naam, voornaam, geslacht, emailadres, gsm);
        }
        else return null;
    }
    public static Persoon createPersoon(String gebruikersnaam, String wachtwoord, String type, String adres, String bedrijfsNaam, String BTWNummer, String naam, String voornaam, String geslacht, String emailadres, String gsm){
        if (type == "Locatiehouder"){
            return new Locatiehouder(gebruikersnaam, wachtwoord, adres, bedrijfsNaam, BTWNummer, naam, voornaam, geslacht, emailadres, gsm);
        }
        else return null;
    }
    public static Persoon createPersoon(String type){
        if (type == "Installateur"){
            return new Installateur();
        }
        else if (type == "Planner"){
            return new Planner();
        }
        else if (type == "Laadklant"){
            return new Laadklant();
        }
        else if (type == "Locatiehouder"){
            return new Locatiehouder();
        }
        else return null;
    }
}
