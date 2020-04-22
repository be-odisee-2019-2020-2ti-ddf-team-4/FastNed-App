package be.fastned.application.service;

import be.fastned.application.dao.*;
import be.fastned.application.domain.*;
import be.fastned.application.formdata.AfspraakData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlannerServiceImpl implements PlannerService {

    @Autowired
    AfspraakRepository afspraakRepository;
    @Autowired
    InstallateurRepository installateurRepository;
    @Autowired
    LocatietoestemmingRepository locatietoestemmingRepository;
    @Autowired
    ProbleemRepository probleemRepository;
    @Autowired
    LaadpaalRepository laadpaalRepository;
    @Autowired
    ContractRepository contractRepository;
    @Autowired
    BezoekRepository bezoekRepository;

    public List<Installateur> getAvailableInstallateurs(){
        return (List<Installateur>) installateurRepository.findAll();
    }


    public List<Contract> getAvailableContracten() {
        return (List<Contract>) contractRepository.findAll();
    }


    public List<Laadpaal> getAvailableLaadpalen() {
        return (List<Laadpaal>) laadpaalRepository.findAll();
    }

    public List<Locatietoestemming> getAvailableLocatietoestemmingen(){
        return (List<Locatietoestemming>) locatietoestemmingRepository.findAll();
    }

    public List<Probleem> getAvailableProblemen(){
        return (List<Probleem>) probleemRepository.findAll();
    }


    public List<Afspraak> getAvailableAfspraken() {
        return (List<Afspraak>) afspraakRepository.findAll();
    }

    /**
     * Instantieert- en initialiseert een nieuw AfpsraakData object voor gebruik bij een formulier.
     * @return Een nieuw AfpsraakData-object met mogelijke aanpassingen vs een leeg object.
     */
    public AfspraakData prepareNewAfspraakData() {
        return prepareAfspraakData(afspraakRepository.findFirstByOrderByIdDesc());
    }

    /**
     * Bereidt een nieuw AfpsraakData-object voor op basis van een bestaand AfpsraakData-object. (uit DB)
     * @param laatsteAfspraak De laatst gevonden afspraak in de DB.
     * @return Een nieuw AfpsraakData-object met mogelijke aanpassingen vs een leeg object.
     */
    private AfspraakData prepareAfspraakData(Afspraak laatsteAfspraak) {

        AfspraakData afspraakData = new AfspraakData();

        afspraakData.setInstallateurId(0);
        afspraakData.setContractId(0);
        afspraakData.setBezoekId(0);
        afspraakData.setLaadpaalId(0);
        afspraakData.setStatus(null);

        return afspraakData;
    }

    @Override
    public String processEntry(AfspraakData afspraakData) {

        Afspraak afspraak;

        // Dit kon een update zijn (zie prepareNewAfspraakData)
        if (afspraakData.getId() == 0) {

            Installateur installateur = installateurRepository.findById(afspraakData.getInstallateurId());
            Laadpaal laadpaal = laadpaalRepository.findById(afspraakData.getLaadpaalId());
            Contract contract = contractRepository.findById(afspraakData.getContractId());
            Bezoek bezoek = bezoekRepository.findById(afspraakData.getBezoekId());
            String status = afspraakData.getStatus();

            afspraak = new Afspraak(0,installateur, laadpaal, contract, bezoek, status);
        }
        else {
            afspraak = afspraakRepository.findById( afspraakData.getId());
        }
        // Save the newly created entry
        afspraakRepository.save(afspraak);
        return String.format("Afspraak (id = \"%s\") is verwerkt!", afspraak.getId());
    }

    @Override
    public AfspraakData prepareAfspraakDataToEdit(long id) {

        Afspraak deAfspraak = afspraakRepository.findById(id);
        AfspraakData deAfspraakData = prepareAfspraakData(deAfspraak);
        return deAfspraakData;
    }

    @Override
    public void deleteAfspraak(long id) {
        Afspraak afspraak = afspraakRepository.findById(id);
        afspraakRepository.delete(afspraak);
    }
}