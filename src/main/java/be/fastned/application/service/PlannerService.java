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

    List<Afspraak> getAvailableAfspraken();

    

    AfspraakData prepareNewAfspraakData();
    AfspraakData prepareNewAfspraakData(long id);
    AfspraakData prepareNewAfspraakData(Afspraak base);

    AfspraakData updateAfspraak(AfspraakData base);
    String processEntry(@Valid AfspraakData afspraakData);

    AfspraakData prepareAfspraakDataToEdit(long id);
    public String getAuthenticatedFullname();
    void deleteAfspraak(long id);

    

    Installateur getInstallateurById(long id);
    Bezoek getBezoekById(long id);
    Laadpaal getLaadpaalById(long id);
    Contract getContractById(long id);
    Afspraak getAfspraakById(long id);
}
