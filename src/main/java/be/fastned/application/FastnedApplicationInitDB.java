package be.fastned.application;

import be.fastned.application.dao.*;
import be.fastned.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Database initializer that populates the database with some
 * initial data.
 *
 * This component is started only when app.db-init property is set to true
 */
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class FastnedApplicationInitDB implements CommandLineRunner {

    @Autowired
    AfspraakRepository afspraakRepository;
    @Autowired
    InstallateurRepository installateurRepository;
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
    LocatiehouderRepository locatiehouderRepository;
    @Autowired
    DocumentatieRepository documentatieRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PlannerRepository plannerRepository;
    @Override
    public void run(String... args) throws Exception {

        // Problemen
        List<Probleem> problemen = Arrays.asList(
                new Probleem(1, "laadpaal is geschaafd", "net aangemaakt", null),
                new Probleem(2, "laadpaal is gestolen", "net aangemaakt", null),
                new Probleem(3, "laadpaal is ontploft", "net aangemaakt", null)
        );
        for (Probleem probleem : problemen) {
            probleemRepository.save(probleem);
        };

        List<User> users = Arrays.asList(
                new User(1, "pieterPotter", "{bcrypt}$2a$10$abpfm2ctiTdTxT1.ehufwuvjFls2EMkmtXP7UKQOQSBfZRoF/ol2S", "ROLE_PLANNER", "beschr", "Enabled", "Pieter", "Pienter"),
                new User(2, "mohammed", "{bcrypt}$2a$10$fauMfqKeKFcHNMtdfXV5uuGm0RhP3S/.5sVAIWe139Ek5Mq7k5sMS", "ROLE_PLANNER", "beschr", "Enabled", "Mohammed", "Sahil"),
                new User(3, "john", "{bcrypt}$2a$10$Vs148aoZGcelQo7yk19E4OaD2bGD4v7GGs3UrDZO8QI60EnKtgBrG", "ROLE_INSTALLATEUR", "beschr", "Enabled", "John", "Doe"),
                new User(4, "blake", "{bcrypt}$2a$10$rJr2799QYnpGEBJU1TRxg.gzCG0aQuY3MnSIyxUQzptxRkQgS5MTG", "ROLE_INSTALLATEUR", "beschr", "Enabled", "Blake", "Lively"),
                new User(5, "sasuke", "{bcrypt}$2a$10$6ZIovt9KSJIBxYFjT1v5Z.o4eWFtSfBvvoTTv1MEq2V/knzkV40Bq", "ROLE_PLANNER", "beschr", "Enabled", "Sasuke", "Uchiha"),
                new User(6, "tibo", "{bcrypt}$2a$10$3Gk0gbWpHBpncGGblsGqH.fxNxrjPVe6nqJp.hmxY66HpU66p7S3G", "ROLE_ADMIN", "beschr", "Enabled", "Tibo", "Van Gindertaelen"),
                new User(7, "kakashi", "{bcrypt}$2a$10$Lvu/YbSNgortFgTvoQvnWObqHleJhzpdJ0hYE.Uz6xYHr/7K7tQEm", "ROLE_LOCATIEHOUDER", "beschr", "Enabled", "Kakashi", "Hatake"),
                new User(8, "hans", "{bcrypt}$2a$10$QwQ6BhO1p30EPB.EZ2kbrex/tpGoWYCCrbadffTPrWpwJ0KlEGA36", "ROLE_LOCATIEHOUDER", "beschr", "Enabled", "Hans", "Vandenbogaerde"),
                new User(9, "niels", "{bcrypt}$2a$10$tCRGYvO.S7D25rDTH3pXvuCH/aPmiI.UmeKbtjTMFpL0dEnkbEn5S", "ROLE_LOCATIEHOUDER", "beschr", "Enabled", "Niels", "Dejonghe")
        );
        for (User user : users) {
            userRepository.save(user);
        };

        // Planners
        List<Planner> planners = Arrays.asList(
                new Planner(5, "tibo.demaster@gmail.be", userRepository.findById(6).getLastName(), userRepository.findById(6).getFirstName(), "DE man", "0497616166",
                        userRepository.findById(6)),
                new Planner(6, "pieter.demets@skynet.us", userRepository.findById(1).getLastName(), userRepository.findById(1).getFirstName(), "man", "0497616166",
                        userRepository.findById(1)),
                new Planner(7, "mohammed.deslager@konohama.com", userRepository.findById(2).getLastName(), userRepository.findById(2).getFirstName(), "man", "0497518166",
                        userRepository.findById(2)),
                new Planner(8, "hans.depro@docent.odisee.be", userRepository.findById(5).getLastName(), userRepository.findById(5).getFirstName(), "man", "0497518166",
                        userRepository.findById(5))
        );

        for (Planner planner : planners) {
            plannerRepository.save(planner);
        };

        // Locatiehouders
        List<Locatiehouder> locatiehouders = Arrays.asList(
                new Locatiehouder(9, "john.doe@gmail.com", userRepository.findById(3).getLastName(), userRepository.findById(3).getFirstName(), "man", "0497618166",  "bedrijfsnaam", "btwNummer", "adres",
                        userRepository.findById(3)),
                new Locatiehouder(10, "blake.lively@skynet.us", userRepository.findById(4).getLastName(), userRepository.findById(4).getFirstName(), "vrouw", "0497616166", "bedrijfsnaam", "btwNummer", "adres",
                        userRepository.findById(4))
        );

        for (Locatiehouder locatiehouder : locatiehouders) {
            locatiehouderRepository.save(locatiehouder);
        };

        // Installateurs
        List<Installateur> installateurs = Arrays.asList(
                new Installateur(1, "john.doe@gmail.com", userRepository.findById(1).getLastName(), userRepository.findById(1).getFirstName(), "man", "0497618166",
                        userRepository.findById(1)),
                new Installateur(2, "blake.lively@skynet.us", userRepository.findById(1).getLastName(), userRepository.findById(1).getFirstName(), "vrouw", "0497616166",
                        userRepository.findById(1)),
                new Installateur(3, "sasuke.uchiha@konohama.com", userRepository.findById(1).getLastName(), userRepository.findById(1).getFirstName(), "man", "0497518166",
                        userRepository.findById(1)),
                new Installateur(4, "mamamia@konohama.com", "De bakker", userRepository.findById(1).getFirstName(), userRepository.findById(1).getFirstName(), "0497518156",
                        userRepository.findById(1))
        );

        for (Installateur installateur : installateurs) {
            installateurRepository.save(installateur);
        };

        // Documentaties
        List<Documentatie> documentaties = Arrays.asList(
                new Documentatie(1, "A", "installatie", "X installeer je op Y manier"),
                new Documentatie(2, "B", "installatie", "X installeer je op Y manier"),
                new Documentatie(3, "A", "reparatie", "X fix je op Y manier"),
                new Documentatie(4, "B", "reparatie", "X fix je op Y manier")
        );

        for (Documentatie documentatie : documentaties) {
            documentatieRepository.save(documentatie);
        };

        // Laadpalen
        List<Laadpaal> laadpalen = Arrays.asList(
                new Laadpaal(1, locatiehouderRepository.findById(1), documentatieRepository.findById(1), documentatieRepository.findById(3), "A", "net aangemaakt"),
                new Laadpaal(2, locatiehouderRepository.findById(2), documentatieRepository.findById(2), documentatieRepository.findById(4), "B", "net aangemaakt")
        );

        for (Laadpaal laadpaal : laadpalen) {
            laadpaalRepository.save(laadpaal);
        };

        // Contracten
        List<Contract> contracten = Arrays.asList(
                new Contract(1, new Date(2020, 15,4), new Date(2020, 15,10)),
                new Contract(2, new Date(2020, 15,4), new Date(2020, 15,10)),
                new Contract(3, new Date(2020, 15,4), new Date(2020, 15,10)),
                new Contract(4, new Date(2020, 15,4), new Date(2020, 15,10))
        );

        for (Contract contract : contracten) {
            contractRepository.save(contract);
        };

        // Bezoeken
        List<Bezoek> bezoeken = Arrays.asList(
                new Bezoek(1, probleemRepository.findById(1), documentatieRepository.findById(1), "Installatie", "alles OK verlopen", new Date(2020, 15,10), new Date(2020, 15,10)),
                new Bezoek(2, probleemRepository.findById(2), documentatieRepository.findById(2), "Installatie", "alles OK verlopen", new Date(2020, 15,10), new Date(2020, 15,10)),
                new Bezoek(3, probleemRepository.findById(3), documentatieRepository.findById(3), "Reparatie", "alles OK verlopen", new Date(2020, 15,10), new Date(2020, 15,10)),
                new Bezoek(4, null, documentatieRepository.findById(1), "Reparatie", "alles OK verlopen", new Date(2020, 15,10), new Date(2020, 15,10))
        );

        for (Bezoek bezoek : bezoeken) {
            bezoekRepository.save(bezoek);
        };

        // Afspraken
        List<Afspraak> afspraken = Arrays.asList(
                new Afspraak(1,"Installatie", installateurRepository.findById(1), laadpaalRepository.findById(1), contractRepository.findById(1), bezoekRepository.findById(1), "net aangemaakt"),
                new Afspraak(2, "Reparatie",installateurRepository.findById(2), laadpaalRepository.findById(1), contractRepository.findById(2), bezoekRepository.findById(2), "net aangemaakt"),
                new Afspraak(3,"Reparatie", installateurRepository.findById(3), laadpaalRepository.findById(2), contractRepository.findById(3), bezoekRepository.findById(3), "net aangemaakt"),
                new Afspraak(4, "Installatie",installateurRepository.findById(4), laadpaalRepository.findById(2), contractRepository.findById(4), bezoekRepository.findById(4), "net aangemaakt")
        );

        for (Afspraak afspraak : afspraken) {
            // TODO: (Niels) Error: Een vereiste subklasse "Locatiehouder" was gevraagd, in de plaats kwam "Installateur". (Uncomment & Shift+F10 om te beginnen)
            afspraakRepository.save(afspraak);
        };
    }
}
