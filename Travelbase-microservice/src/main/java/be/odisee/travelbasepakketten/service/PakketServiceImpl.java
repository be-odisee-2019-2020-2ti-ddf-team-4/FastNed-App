package be.odisee.travelbasepakketten.service;

import be.odisee.travelbasepakketten.dao.PakketActiviteitRepository;
import be.odisee.travelbasepakketten.dao.PakketRepository;
import be.odisee.travelbasepakketten.domain.Pakket;
import be.odisee.travelbasepakketten.domain.PakketActiviteit;
import be.odisee.travelbasepakketten.formdata.PakketFormulier;
import org.apache.http.MessageConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class PakketServiceImpl implements PakketService {

    @Autowired
    PakketRepository pakketRepository;

    @Autowired
    PakketActiviteitRepository pakketActiviteitRepository;

    @Override
    public PakketFormulier getEmptyPakketFormulier() {
        return new PakketFormulier(new Pakket(0), new ArrayList<>());
    }

    @Override // DONE
    public List<PakketFormulier> getAllPakketFormuliers() {
        List<PakketFormulier> pakketFormuliers = new ArrayList<PakketFormulier>();

        for (Pakket pakket : pakketRepository.findAll()) {
            pakketFormuliers.add(getPakketFormulier(pakket.getId()));
        }

        return pakketFormuliers;
    }

    @Override // DONE
    public PakketFormulier getPakketFormulier(Long pakketId) {
        // Data ophalen
        Pakket pakket = pakketRepository.findPakketById(pakketId);
        List<PakketActiviteit> pakketActiviteits = pakketActiviteitRepository.findPakketActiviteitsByPakket(pakket);

        List<Long> activiteitIds = new ArrayList<>();
        for (PakketActiviteit pakketActiviteit : pakketActiviteits) {
            activiteitIds.add(pakketActiviteit.getActiviteitId());
        }

        // nieuw PakketFormulier samenstellen
        PakketFormulier pakketFormulier = new PakketFormulier(pakket, activiteitIds);

        return pakketFormulier;
    }

    @Override // DONE
    public PakketFormulier updatePakketFormulier(PakketFormulier newPakketFormulier) { // DONE
        Long pakketId = newPakketFormulier.getPakket().getId();
        Pakket pakket = pakketRepository.findPakketById(pakketId);

        pakket.setNaam(newPakketFormulier.getPakket().getNaam());
        pakket.setDescription(newPakketFormulier.getPakket().getDescription());
        pakketRepository.save(pakket);


        List<PakketActiviteit> pakketActiviteits = pakketActiviteitRepository.findPakketActiviteitsByPakket(pakket);
        pakketActiviteitRepository.deleteAll(pakketActiviteits);

        for (Long pakketActiviteitId: newPakketFormulier.getActiviteitIds()) {
            PakketActiviteit pakketActiviteit = new PakketActiviteit(0, pakket, pakketActiviteitId);
            pakketActiviteitRepository.save(pakketActiviteit);
        }

        return newPakketFormulier;
    }

    @Override // DONE
    public PakketFormulier createPakketFormulier(PakketFormulier pakketFormulier) { // parameter naar PakketFormulier
        Pakket pakket = pakketFormulier.getPakket();
        pakketRepository.save(pakket);

        for (Long pakketActiviteitId: pakketFormulier.getActiviteitIds()) {
            pakketActiviteitRepository.save(new PakketActiviteit(0, pakket, pakketActiviteitId));
        }
        return pakketFormulier;
    }

    @Override // DONE
    public Boolean deletePakketFormulier(Long id) {
        Pakket pakket = pakketRepository.findPakketById(id);

        List<PakketActiviteit> pakketActiviteits = pakketActiviteitRepository.findPakketActiviteitsByPakket(pakket);
        pakketActiviteitRepository.deleteAll(pakketActiviteits);

        pakketRepository.delete(pakket);

        return true;
    }

}
