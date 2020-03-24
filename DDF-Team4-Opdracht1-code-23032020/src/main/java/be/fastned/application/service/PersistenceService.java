package be.fastned.application.service;

import be.fastned.application.domain.Afspraak;
import be.fastned.application.domain.Contract;
import be.fastned.application.domain.Installateur;
import be.fastned.application.domain.Laadpaal;

public interface PersistenceService {
    public Afspraak voegAfspraakToe(Laadpaal laadpaal, Installateur installateur, Contract contract);

    public Afspraak zoekAfspraakMetId (long id);

    public void updateAfspraakStatus (Afspraak toBeUpdated, String status);
}
