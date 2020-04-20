package be.fastned.application.service;

import be.fastned.application.dao.form.EntryAfspraakRepository;
import be.fastned.application.domain.OtherImpl.Contract;
import be.fastned.application.domain.OtherImpl.Laadpaal;
import be.fastned.application.domain.Personen.Installateur;
import be.fastned.application.domain.Technisch.Bezoek;
import be.fastned.application.domain.form.EntryAfspraak;
import be.fastned.application.formdata.EntryDataAfspraak;
import org.springframework.beans.factory.annotation.Autowired;

public class PlannerServiceImpl {
    @Autowired
    EntryAfspraakRepository entryAfspraakRepository;

    /**
     * Instantiates and initializes new EntryData-object
     * to be used in the form
     * @return
     */
    public EntryDataAfspraak prepareNewEntryData() {

        EntryAfspraak lastEntry = entryAfspraakRepository.findFirstByOrderByIdDesc();
        return prepareEntryData(lastEntry, true);
    }

    /**
     * Prepares an EntryData-object based on an Entry-object
     * @param theEntry the Entry-object
     * @param timeShift indicates if the startTime needs to be shifted from previous timeTo
     * @return
     */
    private EntryDataAfspraak prepareEntryData(EntryAfspraak theEntry, boolean timeShift) {

        EntryDataAfspraak entryData = new EntryDataAfspraak();

        if (theEntry != null) {

            // TODO: Null-checks niet nodig bij non-nullable types
            // TODO: Versimpel codeduplicatie naar methode --> gemeenschappelijke interface met .getId() implementeren in domeinObj
            // Pick up an entry objective provided there was one
            Contract lastContract = theEntry.getContract();

            String ContractId = ((lastContract==null) ? null : lastContract.getId());
            entryData.setContractId(ContractId);

            // Pick up an entry objective provided there was one
            Installateur lastInstallateur = theEntry.getInstallateur();

            String installateurId = ((lastInstallateur==null) ? null : lastInstallateur.getId());
            entryData.setInstallateurId(installateurId);

            // Pick up an entry objective provided there was one
            Laadpaal lastLaadpaal = theEntry.getLaadpaal();

            String laadpaalId = ((lastLaadpaal==null) ? null : lastLaadpaal.getId());
            entryData.setLaadpaalId(laadpaalId);

            // TODO: .getId() is nu enkel een lijn in de interface --> Kan niet Werken!
            // Pick up an entry objective provided there was one
            Bezoek lastBezoek = theEntry.getBezoek();

            String bezoekId = ((lastBezoek==null) ? null : lastBezoek.getId());
            entryData.setBezoekId(bezoekId);

            // Pick up an entry objective provided there was one
            String lastStatus = theEntry.getStatus();

            String status = ((lastStatus==null) ? null : lastStatus);
            entryData.setStatus(status);
        }
        else {
            entryData.setInstallateurId(null);
            entryData.setContractId(null);
            entryData.setBezoekId(null);
            entryData.setLaadpaalId(null);
            entryData.setStatus(null);
        }
        return entryData;
    }
}
