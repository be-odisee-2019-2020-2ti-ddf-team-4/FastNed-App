package be.fastned.application;

import be.fastned.application.dao.*;
import be.fastned.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;
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

        // Dummy-User objecten (versimpelen argumenten aan persoon-constructor meegeven - Personen maken zelf User-objecten aan!)
        User pieter = new User(0, "pieterPotter", "{bcrypt}$2a$10$2o9Frax4HHJLEMMb5iKs9ONs8zEmGv51IRIURY8PkBk7GsCxy4ixO", "ROLE_PLANNER", "beschr", "Enabled", "Pieter", "Pienter", null);
        User mohammed = new User(0, "mohammed21", "{bcrypt}$2a$10$vwKk.OxTjqkzVudfuIuUauxmIrcx8Miq6vFgmLF6sgRcu7viIxgGO", "ROLE_PLANNER", "beschr", "Enabled", "Mohammed", "Sahil", null);
        User john = new User(0, "imperialJohn", "{bcrypt}$2a$10$dTj8pIUJCTDi3kr.VnNRye1E7vmG7Yitx3IFFYrqAoEWwHWTJ1Zbu", "ROLE_INSTALLATEUR", "beschr", "Enabled", "John", "Doe", null);
        User blake = new User(0, "blakey", "{bcrypt}$2a$10$6OnHGe1AvHhR0vTRED7Obeh02YnQlUBEXENHoXsZ7v5EFigcVWrTm", "ROLE_INSTALLATEUR", "beschr", "Enabled", "Blake", "Lively", null);
        User marie = new User(0, "marietje", "{bcrypt}$2a$10$6OnHGe1AvHhR0vTRED7Obeh02YnQlUBEXENHoXsZ7v5EFigcVWrTm", "ROLE_INSTALLATEUR", "beschr", "Enabled", "Marie", "De Bakker", null);
        User sasuke = new User(0, "sasukeUchiha", "{bcrypt}$2a$10$6OnHGe1AvHhR0vTRED7Obeh02YnQlUBEXENHoXsZ7v5EFigcVWrTm", "ROLE_PLANNER", "beschr", "Enabled", "Sasuke", "Uchiha", null);
        User tibo = new User(0, "tibovg", "{bcrypt}$2a$10$6OnHGe1AvHhR0vTRED7Obeh02YnQlUBEXENHoXsZ7v5EFigcVWrTm", "ROLE_ADMIN", "beschr", "Enabled", "Tibo", "Van Gindertaelen", null);
        User kakashi = new User(0, "sensei", "{bcrypt}$2a$10$6OnHGe1AvHhR0vTRED7Obeh02YnQlUBEXENHoXsZ7v5EFigcVWrTm", "ROLE_LOCATIEHOUDER", "beschr", "Enabled", "Kakashi", "Hatake", null);
        User hanz = new User(0, "hanzdeprofessioneel", "{bcrypt}$2a$10$6OnHGe1AvHhR0vTRED7Obeh02YnQlUBEXENHoXsZ7v5EFigcVWrTm", "ROLE_LOCATIEHOUDER", "beschr", "Enabled", "Hans", "Vandenbogaerde", null);
        User nielz = new User(0, "nielzatron", "{bcrypt}$2a$10$6OnHGe1AvHhR0vTRED7Obeh02YnQlUBEXENHoXsZ7v5EFigcVWrTm", "ROLE_LOCATIEHOUDER", "beschr", "Enabled", "Niels", "Dejonghe", null);

        // Installateurs
        List<Installateur> installateurs = Arrays.asList(
                new Installateur(11, "john.doe@gmail.com", john.getLastName(), john.getFirstName(), "man", "0497618166",
                        john.getUsername(), john.getPassword(), john.getRole() ,john.getBeschrijving(), john.getStatus()),
                new Installateur(12, "blake.lively@skynet.us", blake.getLastName(), blake.getFirstName(), "vrouw", "0497616166",
                        blake.getUsername(), blake.getPassword(), blake.getRole() ,blake.getBeschrijving(), blake.getStatus()),
                new Installateur(13, "sasuke.uchiha@konohama.com", sasuke.getLastName(), sasuke.getFirstName(), "man", "0497518166",
                        sasuke.getUsername(), sasuke.getPassword(), sasuke.getRole() ,sasuke.getBeschrijving(), sasuke.getStatus()),
                new Installateur(14, "mamamia@konohama.com", "De bakker", marie.getFirstName(), marie.getFirstName(), "0497518156",
                        marie.getUsername(), marie.getPassword(), marie.getRole() ,marie.getBeschrijving(), marie.getStatus())
        );

        for (Installateur installateur : installateurs) {
            installateurRepository.save(installateur);
        };

        // Planners
        List<Planner> planners = Arrays.asList(
                new Planner(22, "tibo.demaster@gmail.be", tibo.getLastName(), tibo.getFirstName(), "DE man", "0497616166",
                        tibo.getUsername(), tibo.getPassword(), tibo.getRole() ,tibo.getBeschrijving(), tibo.getStatus()),
                new Planner(22, "pieter.demets@skynet.us", pieter.getLastName(), pieter.getFirstName(), "man", "0497616166",
                        pieter.getUsername(), pieter.getPassword(), pieter.getRole() ,pieter.getBeschrijving(), pieter.getStatus()),
                new Planner(23, "mohammed.deslager@konohama.com", mohammed.getLastName(), mohammed.getFirstName(), "man", "0497518166",
                        mohammed.getUsername(), mohammed.getPassword(), mohammed.getRole() ,mohammed.getBeschrijving(), mohammed.getStatus())
        );

        for (Planner planner : planners) {
            plannerRepository.save(planner);
        };

        // Locatiehouders
        List<Locatiehouder> locatiehouders = Arrays.asList(
                new Locatiehouder(31, "john.doe@gmail.com", kakashi.getLastName(), kakashi.getFirstName(), "man", "0497618166",  "bedrijfsnaam", "btwNummer", "adres",
                        kakashi.getUsername(), kakashi.getPassword(), kakashi.getRole() ,kakashi.getBeschrijving(), kakashi.getStatus()),
                new Locatiehouder(32, "blake.lively@skynet.us", hanz.getLastName(), hanz.getFirstName(), "vrouw", "0497616166", "bedrijfsnaam", "btwNummer", "adres",
                        hanz.getUsername(), hanz.getPassword(), hanz.getRole() ,hanz.getBeschrijving(), hanz.getStatus()),
                new Locatiehouder(33, "sasuke.uchiha@konohama.com", nielz.getLastName(), nielz.getFirstName(), "man", "0497518166","bedrijfsnaam", "btwNummer", "adres",
                        nielz.getUsername(), nielz.getPassword(), nielz.getRole() ,nielz.getBeschrijving(), nielz.getStatus())
        );

        for (Locatiehouder locatiehouder : locatiehouders) {
            locatiehouderRepository.save(locatiehouder);
        };

        // Documentaties
        List<Documentatie> documentaties = Arrays.asList(
                new Documentatie(41, "A", "installatie", "X installeer je op Y manier"),
                new Documentatie(42, "B", "installatie", "X installeer je op Y manier"),
                new Documentatie(43, "A", "reparatie", "X fix je op Y manier"),
                new Documentatie(44, "B", "reparatie", "X fix je op Y manier")
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
                new Contract(1, LocalDateTime.now(), LocalDateTime.now().plusDays(5)),
                new Contract(2, LocalDateTime.now(), LocalDateTime.now().plusDays(5)),
                new Contract(3, LocalDateTime.now(), LocalDateTime.now().plusDays(5)),
                new Contract(4, LocalDateTime.now(), LocalDateTime.now().plusDays(5))
        );

        for (Contract contract : contracten) {
            contractRepository.save(contract);
        };

        // Bezoeken
        List<Bezoek> bezoeken = Arrays.asList(
                new Bezoek(1, probleemRepository.findById(1), documentatieRepository.findById(1), "Installatie", "alles OK verlopen", LocalDateTime.now(), LocalDateTime.now().plusDays(5)),
                new Bezoek(2, probleemRepository.findById(2), documentatieRepository.findById(2), "Installatie", "alles OK verlopen", LocalDateTime.now(), LocalDateTime.now().plusDays(5)),
                new Bezoek(3, probleemRepository.findById(3), documentatieRepository.findById(3), "Reparatie", "alles OK verlopen", LocalDateTime.now(), LocalDateTime.now().plusDays(5)),
                new Bezoek(4, null, documentatieRepository.findById(1), "Reparatie", "alles OK verlopen", LocalDateTime.now(), LocalDateTime.now().plusDays(5))
        );

        for (Bezoek bezoek : bezoeken) {
            bezoekRepository.save(bezoek);
        };

        // Afspraken
        List<Afspraak> afspraken = Arrays.asList(
                new Afspraak(51, installateurRepository.findById(11), laadpaalRepository.findById(1), contractRepository.findById(1), bezoekRepository.findById(1), "net aangemaakt"),
                new Afspraak(52, installateurRepository.findById(12), laadpaalRepository.findById(1), contractRepository.findById(2), bezoekRepository.findById(2), "net aangemaakt"),
                new Afspraak(53, installateurRepository.findById(13), laadpaalRepository.findById(2), contractRepository.findById(3), bezoekRepository.findById(3), "net aangemaakt"),
                new Afspraak(54, installateurRepository.findById(14), laadpaalRepository.findById(2), contractRepository.findById(4), bezoekRepository.findById(4), "net aangemaakt")
        );

        for (Afspraak afspraak : afspraken) {
            // TODO: (Niels) Error: Een vereiste subklasse "Locatiehouder" was gevraagd, in de plaats kwam "Installateur". (Uncomment & Shift+F10 om te beginnen)
            afspraakRepository.save(afspraak);
        };

    }
}
