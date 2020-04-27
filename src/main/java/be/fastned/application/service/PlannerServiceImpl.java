package be.fastned.application.service;

import be.fastned.application.dao.*;
import be.fastned.application.domain.*;
import be.fastned.application.formdata.AfspraakData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
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
    @Autowired
    UserRepository userRepository;

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
        return prepareAfspraakData();
    }

    public AfspraakData prepareNewAfspraakData(long id) {
        return prepareAfspraakData(id);
    }
    public AfspraakData prepareNewAfspraakData(Afspraak base) {
        return prepareAfspraakData(base);
    }

    /**
     * Bereidt een nieuw AfpsraakData-object voor op basis van een bestaand AfpsraakData-object. (uit DB)
     * @return Een nieuw AfpsraakData-object met mogelijke aanpassingen vs een leeg object.
     */
    private AfspraakData prepareAfspraakData() {

        AfspraakData afspraakData = new AfspraakData();

        afspraakData.setType(null);
        afspraakData.setInstallateurId(0);
        afspraakData.setContractId(0);
        afspraakData.setBezoekId(0);
        afspraakData.setLaadpaalId(0);
        afspraakData.setStatus(null);

        return afspraakData;
    }
    private AfspraakData prepareAfspraakData(long id) {

        AfspraakData afspraakData = new AfspraakData();

        afspraakData.setType(null);
        afspraakData.setId(id);
        afspraakData.setInstallateurId(0);
        afspraakData.setContractId(0);
        afspraakData.setBezoekId(0);
        afspraakData.setLaadpaalId(0);
        afspraakData.setStatus(null);

        return afspraakData;
    }
    private AfspraakData prepareAfspraakData(Afspraak afspraak) {

        AfspraakData afspraakData = new AfspraakData();

        afspraakData.setType(afspraak.getType());
        afspraakData.setInstallateurId(afspraak.getId());
        afspraakData.setContractId(afspraak.getContract().getId());
//        afspraakData.setBezoekId(afspraak.getBezoek().getId());
        afspraakData.setLaadpaalId(afspraak.getLaadpaal().getId());
        afspraakData.setStatus(afspraak.getStatus());

        return afspraakData;
    }

    public void deleteAfspraak(long id) {
        Afspraak afspraak = afspraakRepository.findById(id);
        afspraakRepository.delete(afspraak);
    }

    public AfspraakData updateAfspraak(AfspraakData afspraakData) {

        // TODO: Update enkel wat geüpdated moet worden, niet alles
        Afspraak afspraakUpdated = new Afspraak(
                afspraakData.getId(),

                afspraakData.getType(),
                installateurRepository.findById(afspraakData.getInstallateurId()),
                laadpaalRepository.findById(afspraakData.getLaadpaalId()),
                contractRepository.findById(afspraakData.getContractId()),
                bezoekRepository.findById(afspraakData.getBezoekId()),
                afspraakData.getStatus()
        );
        afspraakRepository.save(afspraakUpdated);
        return afspraakData;
    }

    public Afspraak getAfspraakById(long id){
        return afspraakRepository.findById(id);
    }

    @Override
    public String processEntry(AfspraakData afspraakData) {

        Afspraak afspraak;

        if (afspraakData.getId() == 0) {

            String type = afspraakData.getType();
            Installateur installateur = installateurRepository.findById(afspraakData.getInstallateurId());
            Laadpaal laadpaal = laadpaalRepository.findById(afspraakData.getLaadpaalId());
            Contract contract = contractRepository.findById(afspraakData.getContractId());
            Bezoek bezoek = bezoekRepository.findById(afspraakData.getBezoekId());
            String status = afspraakData.getStatus();

            afspraak = new Afspraak(0, type,installateur, laadpaal, contract, bezoek, status);
        }
        else {
            afspraak = afspraakRepository.findById( afspraakData.getId());
        }
        // Save the newly created entry
        afspraakRepository.save(afspraak);
        return String.format("Afspraak (id = \"%s\") is verwerkt!", afspraak.getId());
    }

    public AfspraakData prepareAfspraakDataToEdit(long id) {

        Afspraak deAfspraak = afspraakRepository.findById(id);
        AfspraakData deAfspraakData = prepareAfspraakData(deAfspraak);
        return deAfspraakData;
    }



    public Installateur getInstallateurById(long id){
        return installateurRepository.findById(id);
    }
    public Bezoek getBezoekById(long id){
        return bezoekRepository.findById(id);
    }
    public Laadpaal getLaadpaalById(long id){
        return laadpaalRepository.findById(id);
    }
    public Contract getContractById(long id){
        return contractRepository.findById(id);
    }


    private String getAuthenticatedUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }
    private User findAuthenticatedUser() {

        String username = getAuthenticatedUsername();
        return userRepository.findByUsername(username);
    }
    @Override
    public String getAuthenticatedFullname() {
        User theUser = findAuthenticatedUser();
        return theUser.getFirstName() +' '+ theUser.getLastName();
    }
}