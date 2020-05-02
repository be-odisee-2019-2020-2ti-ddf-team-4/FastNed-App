package be.fastned.application.controllers;

import be.fastned.application.domain.Afspraak;
import be.fastned.application.formdata.AfspraakData;
import be.fastned.application.service.AfspraakService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/fastned/afspraak")
public class AfspraakController {

    @Autowired
    private AfspraakService plannerService;

    /**
     * Een standaard GET-request ontvangt via /fastNed/afspraak een view (returnwaarde) met model.
     * @param model Een systeem-gegeven parameter van het type Model dat data van service naar view overdraagt.
     * @return De naam van de view die getoond moet worden.
     */
    @GetMapping()
    public String afspraakCreateForm(Model model) {
        AfspraakData afspraak = plannerService.prepareNewAfspraakData();
        prepareModel(afspraak, model, "create");
        return "afspraak";
    }

    /**
     * Zet afspraakData + andere formulier-data op het model waaruit een view data maakt.
     */
    private void prepareModel(AfspraakData afspraakData, Model model, String CRUDType) {
        model.addAttribute("fullname", plannerService.getAuthenticatedFullname());
        model.addAttribute("afspraken", plannerService.getAvailableAfspraken());
        switch (CRUDType){
            case "create":
                model.addAttribute("availableInstallateurs", plannerService.getAvailableInstallateurs());
                model.addAttribute("availableLaadpalen", plannerService.getAvailableContracten());
                model.addAttribute("availableContracten", plannerService.getAvailableContracten());
                model.addAttribute("afspraakData", afspraakData);
                model.addAttribute("action", "create");
                break;
            case "update":
                model.addAttribute("afspraakId", afspraakData.getId());
                model.addAttribute("availableInstallateurs", plannerService.getAvailableInstallateurs());
                model.addAttribute("availableLaadpalen", plannerService.getAvailableContracten());
                model.addAttribute("availableContracten", plannerService.getAvailableContracten());
                model.addAttribute("afspraakData", afspraakData);
                model.addAttribute("action", "update");
                break;
            case "delete":
                break;
            case "afspraakSelectie":
                model.addAttribute("availableAfspraken", plannerService.getAvailableAfspraken());
                model.addAttribute("afspraakData", afspraakData);
                model.addAttribute("action", "selectAfspraakDetail");
                break;
            case "afspraakDetail":
                model.addAttribute("afspraakId", afspraakData.getId());

                Afspraak tussenstap = plannerService.getAfspraakById(afspraakData.getId());
                model.addAttribute("childInstallateur", plannerService.getInstallateurById(tussenstap.getInstallateur().getId()).getVoornaam());
                model.addAttribute("childLaadpaal", plannerService.getLaadpaalById(tussenstap.getLaadpaal().getId()).getId());
                model.addAttribute("childContract", plannerService.getContractById(tussenstap.getContract().getId()).getId());
//                model.addAttribute("childBezoek", plannerService.getBezoekById(tussenstap.getBezoek().getId()).getId());
                model.addAttribute("childStatus", tussenstap.getStatus());
                model.addAttribute("childType", tussenstap.getType());
                model.addAttribute("action", "afspraakDetail");
                break;
        }
    }

    @GetMapping("/afspraakSelectie")
    public String afspraakCreateAfspraakSelectieForm(AfspraakData afspraakData, Model model) {
        prepareModel(afspraakData, model, "afspraakSelectie");
        return "afspraak";
    }

    @PostMapping("/create")
    public String processEntry(@Valid AfspraakData afspraakData, Errors errors, Model model) {

        System.out.println(String.format(Integer.toString(plannerService.getAvailableAfspraken().size())));
        String message="";

        try {
            if (errors.hasErrors() ) {
                message = "Correct input errors, please";
                throw new IllegalArgumentException();
            }
            else{
                message = plannerService.processEntry(afspraakData);
                afspraakData = plannerService.prepareNewAfspraakData();
            }
        }
        catch (IllegalArgumentException ex) {
            System.out.println("Mistakes were made and intercepted when creating an \"Afspraak\"");
            System.out.println(String.format("Interception error goes as follows: %s", ex.getMessage()));
        }

        prepareModel(afspraakData, model, "create");
        model.addAttribute("message", message);
        return "afspraak";
    }

    @GetMapping("/afspraakDetail")
    public String afspraakCreateShowAfspraakForm(AfspraakData afspraakData, Model model, @RequestParam("id") long id) {
        AfspraakData afspraakDataNew = plannerService.prepareNewAfspraakData(id);
        prepareModel(afspraakDataNew, model, "afspraakDetail");
        return "afspraak";
    }
    @GetMapping("/update")
    public String afspraakCreateUpdateForm(AfspraakData afspraakData, Model model, @RequestParam("id") long id) {
        AfspraakData afspraakDataNew = plannerService.prepareNewAfspraakData(plannerService.getAfspraakById(afspraakData.getId()));
        afspraakDataNew.setId(id);
        prepareModel(afspraakDataNew, model, "update");
        return "afspraak";
    }
    @PostMapping("/update")
    public String afspraakUpdateAfspraak(AfspraakData afspraakData, Model model, @RequestParam("id") long id) {
        plannerService.updateAfspraak(afspraakData);
        return "redirect:/fastned/afspraak";
    }
    @PostMapping("/delete")
    public String afspraakDeleteAfspraak(AfspraakData afspraakData, Model model, @RequestParam("id") long id) {
        plannerService.deleteAfspraak(id);
        return "redirect:/fastned/afspraak";
    }
}