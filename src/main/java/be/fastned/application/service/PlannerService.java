package be.fastned.application.service;

import be.fastned.application.domain.*;
import be.fastned.application.formdata.AfspraakData;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

public interface PlannerService {

    List<Installateur> getAvailableInstallateurs();
    List<Contract> getAvailableContracten();
    List<Laadpaal> getAvailableLaadpalen();

    List<Locatietoestemming> getAvailableLocatietoestemmingen();

    List<Probleem> getAvailableProblemen();

    List<Afspraak> getAvailableAfspraken();

    AfspraakData prepareNewAfspraakData();

    String processEntry(@Valid AfspraakData afspraakData);

    AfspraakData prepareAfspraakDataToEdit(long id);



    Afspraak searchById(int id);

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
