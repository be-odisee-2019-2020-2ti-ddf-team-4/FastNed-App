package be.fastned.application.service.Interfaces;

import be.fastned.application.domain.AndereEntiteiten.Oplossing;

public interface PersistenceService {
    // Een afspraak managen
    /*public Afspraak voegAfspraakToe(Laadpaal laadpaal, Installateur installateur, Contract contract);
    public Afspraak zoekAfspraakMetId (long id);
    public void updateAfspraakStatus (Afspraak toBeUpdated, String status);*/

    Oplossing voegOplossingToe(String oplossing, String probleem);
//    Probleem zoekOplossingMetId(String s);
//    ArrayList<AbsoluteBase> zoekOplossingAlles();
}