package be.odisee.travelbasepakketten.service;

import be.odisee.travelbasepakketten.domain.Pakket;
import be.odisee.travelbasepakketten.domain.PakketActiviteit;
import be.odisee.travelbasepakketten.formdata.PakketFormulier;

import java.util.List;

public interface PakketService {
    PakketFormulier getEmptyPakketFormulier();
    List<PakketFormulier> getAllPakketFormuliers();
    PakketFormulier getPakketFormulier(Long id);
    PakketFormulier updatePakketFormulier(PakketFormulier newPakketFormulier);
    PakketFormulier createPakketFormulier(PakketFormulier pakketFormulier);
    Boolean deletePakketFormulier(Long id);
}
