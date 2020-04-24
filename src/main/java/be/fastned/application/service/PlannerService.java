package be.fastned.application.service;

import be.fastned.application.domain.*;
import be.fastned.application.formdata.AfspraakData;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

public interface PlannerService {

    List<Installateur> getAvailableInstallateurs();
    List<Contract> getAvailableContracten();

    List<Afspraak> getAvailableAfspraken();

    AfspraakData prepareNewAfspraakData();
    AfspraakData prepareNewAfspraakData(long id);
    AfspraakData prepareNewAfspraakData(Afspraak base);

    AfspraakData updateAfspraak(AfspraakData base);
    void deleteAfspraak(long id);
    public String getAuthenticatedFullname();
    String processEntry(@Valid AfspraakData afspraakData);

    Installateur getInstallateurById(long id);
    Bezoek getBezoekById(long id);
    Laadpaal getLaadpaalById(long id);
    Contract getContractById(long id);
    Afspraak getAfspraakById(long id);
}
