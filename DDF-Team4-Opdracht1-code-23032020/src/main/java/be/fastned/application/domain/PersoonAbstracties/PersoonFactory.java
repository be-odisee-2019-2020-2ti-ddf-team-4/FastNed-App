package be.fastned.application.domain.PersoonAbstracties;

import be.fastned.application.domain.Personen.Installateur;
import be.fastned.application.domain.Personen.Laadklant;
import be.fastned.application.domain.Personen.Locatiehouder;
import be.fastned.application.domain.Personen.Planner;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonProfessional;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Component;

@Component("persoonFactoryInst")
public class PersoonFactory {

    public static Persoon createPersoon(EnumPersoon type){
        if (type.getValue() == "installateur"){
            return new Installateur(true);
        }
        else if (type.getValue() == "planner"){
            return new Planner(true);
        }
        else if (type.getValue() == "laadklant"){
            return new Laadklant(true);
        }
        else if (type.getValue() == "locatiehouder"){
            return new Locatiehouder(true);
        }
        else throw new NotYetImplementedException("EnumType nog niet gëimplementeerd in methode createPersoon in PersoonFactory!");
    }

    public static Persoon createPersoon(EnumPersoon type, String emailadres, String gebruikersnaam, String wachtwoord){
        if (type.getValue() == "installateur"){
            return new Installateur(emailadres, gebruikersnaam, wachtwoord);
        }
        else if (type.getValue() == "planner"){
            return new Planner(emailadres, gebruikersnaam, wachtwoord);
        }
        else if (type.getValue() == "laadklant"){
            return new Laadklant(emailadres, gebruikersnaam, wachtwoord);
        }
        if (type.getValue() == "locatiehouder"){
            return new Locatiehouder(emailadres, gebruikersnaam, wachtwoord);
        }
        else throw new NotYetImplementedException("EnumType nog niet gëimplementeerd in methode createPersoon in PersoonFactory!");
    }

    public static Persoon createPersoon(EnumPersoon type, String emailadres, String gebruikersnaam, String wachtwoord, String naam, String voornaam, String geslacht, String gsm){
        if (type.getValue() == "installateur"){
            return new Installateur(emailadres, gebruikersnaam, wachtwoord, naam, voornaam, geslacht, gsm);
        }
        else if (type.getValue() == "planner"){
            return new Planner(emailadres, gebruikersnaam, wachtwoord, naam, voornaam, geslacht, gsm);
        }
        else if (type.getValue() == "laadklant"){
            return new Laadklant(emailadres, gebruikersnaam, wachtwoord, naam, voornaam, geslacht, gsm);
        }
        else throw new NotYetImplementedException("EnumType nog niet gëimplementeerd in methode createPersoon in PersoonFactory!");
    }
    public static PersoonProfessional createPersoon(EnumPersoon type, String emailadres, String gebruikersnaam, String wachtwoord, String adres, String bedrijfsNaam, String btwNummer, String naam, String voornaam, String geslacht, String gsm){
        if (type.getValue() == "locatiehouder"){
            return new Locatiehouder(emailadres, gebruikersnaam, wachtwoord, adres, bedrijfsNaam, btwNummer, naam, voornaam, geslacht, gsm);
        }
        else throw new NotYetImplementedException("EnumType nog niet gëimplementeerd in methode createPersoon in PersoonFactory!");
    }
}
