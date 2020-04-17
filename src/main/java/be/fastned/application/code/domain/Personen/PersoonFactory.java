package be.fastned.application.code.domain.Personen;

import be.fastned.application.code.domain.Technisch.EnumPersoon;
import org.springframework.stereotype.Component;

@Component("persoonFactoryInst")
public class PersoonFactory {

    public static Persoon createPersoon(EnumPersoon type, String gebruikersnaam, String emailadres, String wachtwoord, String naam, String voornaam, String geslacht, String gsm){
        if (type == EnumPersoon.INSTALLATEUR){
            return new Installateur(gebruikersnaam, wachtwoord, naam, voornaam, geslacht, emailadres, gsm);
        }
        else if (type == EnumPersoon.PLANNER){
            return new Planner(gebruikersnaam, wachtwoord, naam, voornaam, geslacht, emailadres, gsm);
        }
        else if (type == EnumPersoon.LAADKLANT){
            return new Laadklant(gebruikersnaam, wachtwoord, naam, voornaam, geslacht, emailadres, gsm);
        }
        else return null;
    }
    public static Persoon createPersoon(EnumPersoon type, String gebruikersnaam, String emailadres, String wachtwoord, String adres, String bedrijfsNaam, String BTWNummer, String naam, String voornaam, String geslacht, String gsm){
        if (type == EnumPersoon.LOCATIEHOUDER){
            return new Locatiehouder(gebruikersnaam, wachtwoord, adres, bedrijfsNaam, BTWNummer, naam, voornaam, geslacht, emailadres, gsm);
        }
        else return null;
    }

    public static Persoon createPersoon(EnumPersoon type, String gebruikersnaam, String emailadres, String wachtwoord){
        if (type == EnumPersoon.INSTALLATEUR){
            return new Installateur(gebruikersnaam, emailadres, wachtwoord);
        }
        else if (type == EnumPersoon.PLANNER){
            return new Planner(gebruikersnaam, emailadres, wachtwoord);
        }
        else if (type == EnumPersoon.LAADKLANT){
            return new Laadklant(gebruikersnaam, emailadres, wachtwoord);
        }
        else if (type == EnumPersoon.LOCATIEHOUDER){
            return new Locatiehouder(gebruikersnaam, emailadres, wachtwoord);
        }
        else return null;
    }

    public static Persoon createPersoon(EnumPersoon type){
        if (type == EnumPersoon.INSTALLATEUR){
            return new Installateur();
        }
        else if (type == EnumPersoon.PLANNER){
            return new Planner();
        }
        else if (type == EnumPersoon.LAADKLANT){
            return new Laadklant();
        }
        else if (type == EnumPersoon.LOCATIEHOUDER){
            return new Locatiehouder();
        }
        else return null;
    }
}
