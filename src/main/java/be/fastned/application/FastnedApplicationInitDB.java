package be.fastned.application;

import be.fastned.application.dao.*;
import be.fastned.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Database initializer that populates the database with some initial data.
 *
 * This component is started only when app.db-init property is set to true.
 */
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class FastnedApplicationInitDB implements CommandLineRunner {

    // CRUD-repo's: Personen
    @Autowired
    PlannerRepository plannerRepository;
    @Autowired
    InstallateurRepository installateurRepository;
    @Autowired
    LocatiehouderRepository locatiehouderRepository;
    @Autowired
    UserRepository userRepository;

    // CRUD-repo's: Andere Entiteiten
    @Autowired
    AfspraakRepository afspraakRepository;
    @Autowired
    LocatietoestemmingRepository locatietoestemmingRepository;
    @Autowired
    ProbleemRepository probleemRepository;
    @Autowired
    LaadpaalRepository laadpaalRepository;
    @Autowired
    ContractRepository contractRepository;
    @Autowired
    BezoekRepository bezoekRepository;
    @Autowired
    DocumentatieRepository documentatieRepository;

    @Override
    public void run(String... args) throws Exception {

        // Problemen
        List<Probleem> problemen = Arrays.asList(
                new Probleem(1, "laadpaal is geschaafd", "net aangemaakt", null),
                new Probleem(2, "laadpaal is gestolen", "net aangemaakt", null),
                new Probleem(3, "laadpaal is ontploft", "net aangemaakt", null)
        );

        // Users
        List<User> users = Arrays.asList(
                new User(1, "pieterPotter", "{bcrypt}$2a$10$abpfm2ctiTdTxT1.ehufwuvjFls2EMkmtXP7UKQOQSBfZRoF/ol2S",
                        "ROLE_PLANNER", "beschr", "Enabled", "Pieter", "Pienter"),
                new User(2, "mohammed", "{bcrypt}$2a$10$fauMfqKeKFcHNMtdfXV5uuGm0RhP3S/.5sVAIWe139Ek5Mq7k5sMS",
                        "ROLE_PLANNER", "beschr", "Enabled", "Mohammed", "Sahil"),
                new User(3, "john", "{bcrypt}$2a$10$Vs148aoZGcelQo7yk19E4OaD2bGD4v7GGs3UrDZO8QI60EnKtgBrG",
                        "ROLE_INSTALLATEUR", "beschr", "Enabled", "John", "Doe"),
                new User(4, "blake", "{bcrypt}$2a$10$rJr2799QYnpGEBJU1TRxg.gzCG0aQuY3MnSIyxUQzptxRkQgS5MTG",
                        "ROLE_INSTALLATEUR", "beschr", "Enabled", "Blake", "Lively"),
                new User(5, "sasuke", "{bcrypt}$2a$10$6ZIovt9KSJIBxYFjT1v5Z.o4eWFtSfBvvoTTv1MEq2V/knzkV40Bq",
                        "ROLE_PLANNER", "beschr", "Enabled", "Sasuke", "Uchiha"),
                new User(6, "tibo", "{bcrypt}$2a$10$3Gk0gbWpHBpncGGblsGqH.fxNxrjPVe6nqJp.hmxY66HpU66p7S3G",
                        "ROLE_ADMIN", "beschr", "Enabled", "Tibo", "Van Gindertaelen"),
                new User(7, "kakashi", "{bcrypt}$2a$10$Lvu/YbSNgortFgTvoQvnWObqHleJhzpdJ0hYE.Uz6xYHr/7K7tQEm",
                        "ROLE_LOCATIEHOUDER", "beschr", "Enabled", "Kakashi", "Hatake"),
                new User(8, "hans", "{bcrypt}$2a$10$QwQ6BhO1p30EPB.EZ2kbrex/tpGoWYCCrbadffTPrWpwJ0KlEGA36",
                        "ROLE_LOCATIEHOUDER", "beschr", "Enabled", "Hans", "Vandenbogaerde"),
                new User(9, "niels", "{bcrypt}$2a$10$tCRGYvO.S7D25rDTH3pXvuCH/aPmiI.UmeKbtjTMFpL0dEnkbEn5S",
                        "ROLE_LOCATIEHOUDER", "beschr", "Enabled", "Niels", "Dejonghe")
        );

        // Installateurs
        List<Installateur> installateurs = Arrays.asList(
                new Installateur(1, "john.doe@gmail.com", users.get(2).getLastName(), users.get(2).getFirstName(),
                        "man", "0497618166", users.get(2)),
                new Installateur(2, "blake.lively@skynet.us", users.get(3).getLastName(), users.get(3).getFirstName(),
                        "vrouw", "0497616166", users.get(3))
        );

        // Planners
        List<Planner> planners = Arrays.asList(
                new Planner(3, "tibo.demaster@gmail.be", users.get(5).getLastName(), users.get(5).getFirstName(),
                        "DE man", "0497616166", users.get(5)),
                new Planner(4, "pieter.demets@skynet.us", users.get(0).getLastName(), users.get(0).getFirstName(),
                        "man", "0497616166", users.get(0)),
                new Planner(5, "mohammed.deslager@konohama.com", users.get(1).getLastName(), users.get(1).getFirstName(),
                        "man", "0497518166", users.get(1)),
                new Planner(6, "hans.depro@docent.odisee.be", users.get(4).getLastName(), users.get(4).getFirstName(),
                        "man", "0497518166", users.get(4))
        );

        // Locatiehouders
        List<Locatiehouder> locatiehouders = Arrays.asList(
                new Locatiehouder(7, "kakashi.hatake@gmail.com", users.get(6).getLastName(), users.get(6).getFirstName(),
                        "man", "0497618166",  "bedrijfsnaam", "btwNummer", "adres", users.get(6)),
                new Locatiehouder(8, "hans.depro@docent.odisee.be", users.get(7).getLastName(), users.get(7).getFirstName(),
                        "man", "0497616166", "bedrijfsnaam", "btwNummer", "adres", users.get(7)),
                new Locatiehouder(9, "niels.dejonghe@skynet.be", users.get(8).getLastName(), users.get(8).getFirstName(),
                        "man", "0497616166", "bedrijfsnaam", "btwNummer", "adres", users.get(8))
        );

        // Documentaties
        List<Documentatie> documentaties = Arrays.asList(
                new Documentatie(1, "A", "installatie", "X installeer je op Y manier"),
                new Documentatie(2, "B", "installatie", "X installeer je op Y manier"),
                new Documentatie(3, "A", "reparatie", "X fix je op Y manier"),
                new Documentatie(4, "B", "reparatie", "X fix je op Y manier")
        );

        // Laadpalen
        List<Laadpaal> laadpalen = Arrays.asList(
                new Laadpaal(1, locatiehouders.get(0), documentaties.get(0), documentaties.get(2), "A", "net aangemaakt"),
                new Laadpaal(2, locatiehouders.get(1), documentaties.get(1), documentaties.get(3), "B", "net aangemaakt")
        );

        // Contracten
        List<Contract> contracten = Arrays.asList(
                new Contract(1, new Date(2020, 15,4), new Date(2020, 15,10)),
                new Contract(2, new Date(2020, 15,4), new Date(2020, 15,10)),
                new Contract(3, new Date(2020, 15,4), new Date(2020, 15,10)),
                new Contract(4, new Date(2020, 15,4), new Date(2020, 15,10))
        );

        // Bezoeken
        List<Bezoek> bezoeken = Arrays.asList(
                new Bezoek(1, problemen.get(0), documentaties.get(0), "Installatie", "alles OK verlopen",
                        new Date(2020, 15,10), new Date(2020, 15,10)),
                new Bezoek(2, problemen.get(1), documentaties.get(1), "Installatie", "alles OK verlopen",
                        new Date(2020, 15,10), new Date(2020, 15,10)),
                new Bezoek(3, problemen.get(2), documentaties.get(2), "Reparatie", "alles OK verlopen",
                        new Date(2020, 15,10), new Date(2020, 15,10)),
                new Bezoek(4, null, documentaties.get(0), "Reparatie", "alles OK verlopen",
                        new Date(2020, 15,10), new Date(2020, 15,10))
        );

        // Afspraken
        List<Afspraak> afspraken = Arrays.asList(
                new Afspraak(1,"Installatie", installateurs.get(0), laadpalen.get(0), contracten.get(0), bezoeken.get(0), "net aangemaakt"),
                new Afspraak(2, "Reparatie",installateurs.get(1), laadpalen.get(0), contracten.get(1), bezoeken.get(1), "net aangemaakt")
        );

        // Because I can't figure out how to match these at runtime -> see "Type Erasure"
        // Warning: The order of these pairs matters to one entity being present inside another!
        iterateAndSaveCollItems(
                new Object[][]{
                        {problemen, probleemRepository},
                        {users, userRepository},
                        {installateurs, installateurRepository},
                        {planners, plannerRepository},
                        {locatiehouders, locatiehouderRepository},
                        {documentaties, documentatieRepository},
                        {laadpalen, laadpaalRepository},
                        {contracten, contractRepository},
                        {bezoeken, bezoekRepository},
                        {afspraken, afspraakRepository},
        });
    }

    //---// ! Helper Methods ! //---//

    // Standardised way of iterating an entity-collection for the purpose of saving every item in it to a CRUD repo (convenient/efficient room for expansion here)
    private void iterateAndSaveCollItems(Object[][] initCollAndItsRepo) throws Exception {
        try{
            for (Object[] collAndRepo : initCollAndItsRepo) {
                ((List) collAndRepo[0]).forEach(item -> ((CrudRepository) collAndRepo[1]).save(item));
            }
        }
        catch(Exception ex) {
            String nameOfThisMethod = new Throwable()
                    .getStackTrace()[0]
                    .getMethodName();
            logInitDBError(nameOfThisMethod, ex);
            throw throwInitDBError("Iteration of an entity collection and saving to a CRUD repository has gone wrong");
        }
    }

    // Standardised way of logging an error for this class (convenient/efficient room for expansion here)
    private void logInitDBError(String nameOfThisMethod, Exception ex){
        String prefix = "Errors occured in method \"%s\"\n";
        if (ex.getMessage() == null || ex.getMessage() == "")
            System.out.println(String.format("%sNo error message! This is the error itself %s", prefix, nameOfThisMethod, ex));
        else
            System.out.println(String.format("%sError message: %s", nameOfThisMethod, ex.getMessage()));
    }

    // Standardised way of supplying the throwing of an error for this class (convenient/efficient room for expansion here)
    private Exception throwInitDBError(String errorDescr){
        return new Exception(errorDescr);
    }
}