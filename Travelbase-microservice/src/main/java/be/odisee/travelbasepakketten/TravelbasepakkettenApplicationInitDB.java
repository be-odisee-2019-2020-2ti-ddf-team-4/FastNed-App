package be.odisee.travelbasepakketten;

import be.odisee.travelbasepakketten.dao.PakketActiviteitRepository;
import be.odisee.travelbasepakketten.dao.PakketRepository;
import be.odisee.travelbasepakketten.domain.Pakket;
import be.odisee.travelbasepakketten.domain.PakketActiviteit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class TravelbasepakkettenApplicationInitDB implements CommandLineRunner {

    @Autowired
    PakketRepository pakketRepository;

    @Autowired
    PakketActiviteitRepository pakketActiviteitRepository;

    @Override
    public void run(String... args) throws Exception {
        Pakket pakket1 = new Pakket(0);
        pakket1.setNaam("Brussel");
        pakket1.setDescription("Op reis naar Brussel");

        Pakket pakket2 = new Pakket(0);
        pakket2.setNaam("Oostende");
        pakket2.setDescription("Met de trein naar Oostende!");

        Pakket pakket3 = new Pakket(0);
        pakket3.setNaam("Gent");
        pakket3.setDescription("noar hent");

        pakketRepository.save(pakket1);
        pakketRepository.save(pakket2);
        pakketRepository.save(pakket3);

        pakketActiviteitRepository.save(new PakketActiviteit(0, pakket1, 1));
        pakketActiviteitRepository.save(new PakketActiviteit(0, pakket1, 3));
        pakketActiviteitRepository.save(new PakketActiviteit(0, pakket2, 2));
        pakketActiviteitRepository.save(new PakketActiviteit(0, pakket2, 3));
        pakketActiviteitRepository.save(new PakketActiviteit(0, pakket3, 1));
    }
}
