package be.fastned.application.controllers;

import be.fastned.application.domain.Afspraak;
import be.fastned.application.formdata.AfspraakData;
import be.fastned.application.service.PlannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

@Slf4j
@Controller
<<<<<<< HEAD
@RequestMapping("/fastned")
=======
@RequestMapping("/fastned/afspraak")
>>>>>>> master
public class PlannerController {

    @Autowired
    private PlannerService plannerService;

    /**
     * Prepare form for create
     */
    @GetMapping
    public String entryCreateForm(Model model) {
        AfspraakData afspraak = plannerService.prepareNewAfspraakData();
<<<<<<< HEAD
        prepareForm(afspraak, model);
=======
        prepareModel(afspraak, model, "create");
>>>>>>> master
        return "afspraak";
    }

    /**
     * Prepares the form with data for projects- and objectives comboboxes
     */
<<<<<<< HEAD
    private void prepareForm(AfspraakData afspraakData, Model model) {
        model.addAttribute("availableInstallateurs", plannerService.getAvailableInstallateurs());
        model.addAttribute("availableLaadpalen", plannerService.getAvailableContracten());
        model.addAttribute("availableContracten", plannerService.getAvailableContracten());
        model.addAttribute("uncheckedLocatietoestemmingen", plannerService.getAvailableLocatietoestemmingen());
        model.addAttribute("uncheckedProblemen", plannerService.getAvailableProblemen());
        model.addAttribute("afspraken", plannerService.getAvailableAfspraken());
        model.addAttribute("afspraakData", afspraakData);
        model.addAttribute("fullname", plannerService.getAuthenticatedFullname());
    }


    /**
     * @param afspraakData to be taken over, except for timeFrom and timeTo, to be set to now
     */
    @PostMapping()
    public String doSomethingPostMapping(AfspraakData afspraakData, Model model) {

        // Do something postmapping met de afspraak en het daarbijhorende model...

        prepareForm(afspraakData, model);
        return "afspraak";
    }

=======
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
>>>>>>> master
    /**
     * Verwerk het formulier
     * @param afspraakData De data voor te te-verwerken afspraak uit het formulier.
     */
    @PostMapping(params = "submit")
    public String processEntry(@Valid AfspraakData afspraakData, Errors errors, Model model) {

        String message="";

        try {
            // Are there any input validation errors detected by JSR 380 bean validation?
            if (errors.hasErrors() ) {
                message = "Correct input errors, please";
                throw new IllegalArgumentException();
            }

            /*
            // Check how many projects have been selected for this entry
            // long numberNonzero = Arrays.stream( entryData.getProjectIds()).filter(x -> x>0).count();
            // There should have been one and only one project selected, if not throw an exception
            if (numberNonzero != 1) {
                message = "Unacceptable, there must be 1 and only 1 project";
                throw new IllegalArgumentException();
            }*/

            // Now that the input seems to be OK, let's create a new entry or update/delete an existing entry
            message = plannerService.processEntry(afspraakData);

            // Prepare form for new data-entry
            afspraakData = plannerService.prepareNewAfspraakData();

        } catch (IllegalArgumentException e) {
            // Nothing special needs to be done
        }
<<<<<<< HEAD
        prepareForm(afspraakData, model);
        model.addAttribute("message", message);
        return "afspraak";
    }

    /**
     * Prepare form for update or delete
     * @param id - the id of the entry to be updated or deleted
     * @param model
     * @return
     */
    @GetMapping("/edit")
    public String entryEditForm(@RequestParam("id") long id, Model model) {

        AfspraakData entryData = plannerService.prepareAfspraakDataToEdit(id);
        prepareForm(entryData, model);
        // TODO: (Tibo) Niet zeker of dit nog iets te maken heeft met onze afspraak tov het voorbeeld zijn "Entry"
        model.addAttribute("message", "Update of Verwijder deze afspraak a.u.b. - of Cancel");
        return "afspraak";
    }

    /**
     * Delete the entry and prepare for creation of a new one
     * @return
     */
    @PostMapping(params = "delete")
    public String deleteEntry(AfspraakData afspraakData, Model model) {

        plannerService.deleteAfspraak(afspraakData.getId());
        AfspraakData afspraakDataNieuw = plannerService.prepareNewAfspraakData();
        prepareForm(afspraakDataNieuw, model);
        model.addAttribute("message", String.format("Afspraak succesvol verwijderd met id \"%s\"", afspraakData.getId()));
        return "afspraak";
    }

    /**
     * If user does not want to update or delete, he will be redirect to create
     * @return
     */
    @PostMapping(params = "cancel")
    public String redirectToCreate() {
        return "redirect:/fastNed";
    }
=======

        prepareModel(afspraakData, model, "create");
        model.addAttribute("message", message);
        return "afspraak";
    }
>>>>>>> master
}