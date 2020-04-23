package be.fastned.application.controllers;

import be.fastned.application.domain.Afspraak;
import be.fastned.application.formdata.AfspraakData;
import be.fastned.application.service.PlannerService;
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
public class PlannerController {

    @Autowired
    private PlannerService plannerService;

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
        switch (CRUDType){
            case "create":
                model.addAttribute("availableInstallateurs", plannerService.getAvailableInstallateurs());
                model.addAttribute("availableLaadpalen", plannerService.getAvailableContracten());
                model.addAttribute("availableContracten", plannerService.getAvailableContracten());
                model.addAttribute("afspraakData", afspraakData);
                model.addAttribute("action", "create");
                break;
            case "update":
                model.addAttribute("availableInstallateurs", plannerService.getAvailableInstallateurs());
                model.addAttribute("availableLaadpalen", plannerService.getAvailableContracten());
                model.addAttribute("availableContracten", plannerService.getAvailableContracten());
                model.addAttribute("afspraakData", afspraakData);
                model.addAttribute("action", "update");
                break;
            case "delete":
                break;
            case "read":
                model.addAttribute("availableAfspraken", plannerService.getAvailableAfspraken());
                model.addAttribute("afspraakData", afspraakData);
                model.addAttribute("action", "read");
                break;
            case "showItem":
                model.addAttribute("afspraakId", afspraakData.getId());
                Afspraak tussenstap = plannerService.getAfspraakById(afspraakData.getId());
                model.addAttribute("childInstallateur", plannerService.getInstallateurById(tussenstap.getInstallateur().getId()).getVoornaam());
                model.addAttribute("childLaadpaal", plannerService.getLaadpaalById(tussenstap.getLaadpaal().getId()).getId());
                model.addAttribute("childContract", plannerService.getContractById(tussenstap.getContract().getId()).getId());
                model.addAttribute("childBezoek", plannerService.getBezoekById(tussenstap.getBezoek().getId()).getId());
                model.addAttribute("childStatus", tussenstap.getStatus());
                model.addAttribute("action", "showItem");
                break;
        }
    }

    /**
     * Een GET-request ontvangt via /fastNed/afspraak/X een view (returnwaarde) met model.
     */
    @GetMapping("/read")
    public String afspraakCreateAfspraakSelectieForm(AfspraakData afspraakData, Model model) {
        prepareModel(afspraakData, model, "read");
        return "afspraak";
    }
    @GetMapping("/showItem")
    public String afspraakCreateShowAfspraakForm(AfspraakData afspraakData, Model model, @RequestParam("id") long id) {
        AfspraakData afspraakDataNew = plannerService.prepareNewAfspraakData(id);
        prepareModel(afspraakDataNew, model, "showItem");
        return "afspraak";
    }
    @PostMapping("/showUpdate")
    public String afspraakCreateUpdateForm(AfspraakData afspraakData, Model model, @RequestParam("id") long id) {
        AfspraakData afspraakDataNew = plannerService.prepareNewAfspraakData(plannerService.getAfspraakById(afspraakData.getId()));
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
        plannerService.deleteAfspraak(afspraakData.getId());
        return "redirect:/fastned/afspraak";
    }
    /**
     * Verwerk het formulier
     * @param afspraakData De data voor te te-verwerken afspraak uit het formulier.
     */
    @PostMapping(params = "action")
    public String processEntry(@Valid AfspraakData afspraakData, Errors errors, Model model) {

        System.out.println(String.format(Integer.toString(plannerService.getAvailableAfspraken().size())));
        String message="";

        try {
            // Are there any input validation errors detected by JSR 380 bean validation?
            if (errors.hasErrors() ) {
                message = "Correct input errors, please";
                throw new IllegalArgumentException();
            }

            // Now that the input seems to be OK, let's create a new entry or update/delete an existing entry
            message = plannerService.processEntry(afspraakData);

            System.out.println(String.format(Integer.toString(plannerService.getAvailableAfspraken().size())));

            // Prepare form for new data-entry
            afspraakData = plannerService.prepareNewAfspraakData();

        } catch (IllegalArgumentException e) {
            // Nothing special needs to be done
        }

        prepareModel(afspraakData, model, "create");
        model.addAttribute("message", message);
        return "afspraak";
    }
}