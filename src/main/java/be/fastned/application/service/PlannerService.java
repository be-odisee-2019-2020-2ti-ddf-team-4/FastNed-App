package be.fastned.application.service;

import be.fastned.application.domain.Installateur;
import be.fastned.application.domain.Locatietoestemming;
import be.fastned.application.domain.Probleem;
import be.fastned.application.formdata.AfspraakData;

import javax.validation.Valid;
import java.util.List;

public interface PlannerService {

    List<Installateur> getAvailableInstallateurs();

    List<Locatietoestemming> getAvailableLocatietoestemmingen();

    List<Probleem> getAvailableProblemen();

    AfspraakData prepareNewAfspraakData();

    String processEntry(@Valid AfspraakData afspraakData);

    AfspraakData prepareAfspraakDataToEdit(long id);

    void deleteAfspraak(long id);

//    public Object getObjectives();
//

//

//
//    public List<EntryAfspraak> getEntriesFromDate(LocalDate theDate);
//
//    public Duration getTotalDuration(List<EntryAfspraak> entries);
//
//    public AfspraakData prepareEntryDataToEdit(String id);
//
//    public void deleteEntry(String id);
}