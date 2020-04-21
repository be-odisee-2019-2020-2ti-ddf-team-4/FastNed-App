package be.fastned.application;

import be.fastned.application.dao.*;
import be.fastned.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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

    @Override
    public void run(String... args) throws Exception {

        // Problemen
        List<Probleem> problemen = Arrays.asList(
                new Probleem("laadpaal is geschaafd", "net aangemaakt", null),
                new Probleem("laadpaal is gestolen", "net aangemaakt", null),
                new Probleem("laadpaal is ontploft", "net aangemaakt", null)
        );

        for (Probleem probleem : problemen) {
            probleemRepository.save(probleem);
        };

        // Installateurs
        List<Installateur> installateurs = Arrays.asList(
                new Installateur("john.doe@gmail.com", "Doe", "John", "man", "0497618166", "JohnnyDoey", "ww"),
                new Installateur("blake.lively@skynet.us", "Lively", "Blake", "vrouw", "0497616166", "Blakey", "ww"),
                new Installateur("sasuke.uchiha@konohama.com", "Uchiha", "Sasuke", "man", "0497518166", "Sharingan69", "ww")
        );

        for (Installateur installateur : installateurs) {
            installateurRepository.save(installateur);
        };

        // Locatiehouders
        List<Locatiehouder> locatiehouders = Arrays.asList(
                new Locatiehouder("john.doe@gmail.com", "Doe", "John", "man", "0497618166", "JohnnyDoey", "ww", "bedrijfsnaam", "btwNummer", "adres"),
                new Locatiehouder("blake.lively@skynet.us", "Lively", "Blake", "vrouw", "0497616166", "Blakey", "ww", "bedrijfsnaam", "btwNummer", "adres"),
                new Locatiehouder("sasuke.uchiha@konohama.com", "Uchiha", "Sasuke", "man", "0497518166", "Sharingan69", "ww", "bedrijfsnaam", "btwNummer", "adres")
        );

        for (Locatiehouder locatiehouder : locatiehouders) {
            locatiehouderRepository.save(locatiehouder);
        };

        // Documentaties
        List<Documentatie> documentaties = Arrays.asList(
                new Documentatie( "A", "installatie", "X installeer je op Y manier"),
                new Documentatie( "B", "installatie", "X installeer je op Y manier"),
                new Documentatie( "A", "reparatie", "X fix je op Y manier"),
                new Documentatie( "B", "reparatie", "X fix je op Y manier")
        );

        for (Documentatie documentatie : documentaties) {
            documentatieRepository.save(documentatie);
        };

        // Laadpalen
        List<Laadpaal> laadpalen = Arrays.asList(
                new Laadpaal(locatiehouderRepository.findById(1), documentatieRepository.findById(1), documentatieRepository.findById(3), "A", "net aangemaakt"),
                new Laadpaal(locatiehouderRepository.findById(2), documentatieRepository.findById(2), documentatieRepository.findById(4), "B", "net aangemaakt")
        );

        for (Laadpaal laadpaal : laadpalen) {
            laadpaalRepository.save(laadpaal);
        };

        // Contracten
        List<Contract> contracten = Arrays.asList(
                new Contract(LocalDateTime.now(), LocalDateTime.now().plusDays(5)),
                new Contract(LocalDateTime.now(), LocalDateTime.now().plusDays(5)),
                new Contract(LocalDateTime.now(), LocalDateTime.now().plusDays(5)),
                new Contract(LocalDateTime.now(), LocalDateTime.now().plusDays(5))
                );

        for (Contract contract : contracten) {
            contractRepository.save(contract);
        };

        // Bezoeken
        List<Bezoek> bezoeken = Arrays.asList(
                new Bezoek(probleemRepository.findById(1), documentatieRepository.findById(1), "Installatie", "alles OK verlopen", LocalDateTime.now(), LocalDateTime.now().plusDays(5)),
                new Bezoek( probleemRepository.findById(2), documentatieRepository.findById(2), "Installatie", "alles OK verlopen", LocalDateTime.now(), LocalDateTime.now().plusDays(5)),
                new Bezoek( probleemRepository.findById(3), documentatieRepository.findById(3), "Reparatie", "alles OK verlopen", LocalDateTime.now(), LocalDateTime.now().plusDays(5)),
                new Bezoek( null, documentatieRepository.findById(1), "Reparatie", "alles OK verlopen", LocalDateTime.now(), LocalDateTime.now().plusDays(5))
                );

        for (Bezoek bezoek : bezoeken) {
            bezoekRepository.save(bezoek);
        };

        // Afspraken
        List<Afspraak> afspraken = Arrays.asList(
                new Afspraak( installateurRepository.findById(1), laadpaalRepository.findById(1), contractRepository.findById(1), bezoekRepository.findById(1), "net aangemaakt"),
                new Afspraak( installateurRepository.findById(2), laadpaalRepository.findById(1), contractRepository.findById(2), bezoekRepository.findById(2), "net aangemaakt"),
                new Afspraak( installateurRepository.findById(3), laadpaalRepository.findById(2), contractRepository.findById(3), bezoekRepository.findById(3), "net aangemaakt"),
                new Afspraak( installateurRepository.findById(4), laadpaalRepository.findById(2), contractRepository.findById(4), bezoekRepository.findById(4), "net aangemaakt")
                );

        for (Afspraak afspraak : afspraken) {
            afspraakRepository.save(afspraak);
        };
    }
}
