package be.fastned.application.controllers;

import be.fastned.application.domain.Afspraak;
import be.fastned.application.formdata.AfspraakData;
import be.fastned.application.service.PlannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
<<<<<<< HEAD
@RequestMapping("/afspraak.html")
=======
@RequestMapping({"/fastned/afspraak", "/fastNed/update"})
>>>>>>> master
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
<<<<<<< HEAD
        prepareForm(afspraak, model);
=======
        prepareModel(afspraak, model, "Create");
>>>>>>> master
        return "afspraak";
    }

    /**
     * Zet afspraakData + andere formulier-data op het model waaruit een view data maakt.
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
=======
    private void prepareModel(AfspraakData afspraakData, Model model, String CRUDType) {
        switch (CRUDType){
            case "Create":
                model.addAttribute("availableInstallateurs", plannerService.getAvailableInstallateurs());
                model.addAttribute("availableLaadpalen", plannerService.getAvailableContracten());
                model.addAttribute("availableContracten", plannerService.getAvailableContracten());
                model.addAttribute("afspraakData", afspraakData);
                break;
            case "Update":
                break;
            case "Delete":
                break;
            case "Read":
                break;
        }
>>>>>>> master
    }

    /**
     * Een GET-request ontvangt via /fastNed/afspraak/X een view (returnwaarde) met model.
     */
    @GetMapping("/edit")
    public String afspraakEditCreateForm(AfspraakData afspraakData, Model model) {
        prepareModel(afspraakData, model, "Create");
        plannerService.updateAfspraak(afspraakData);
        return "afspraak";
    }
    @GetMapping("/update")
    public String afspraakUpdateCreateForm(AfspraakData afspraakData, Model model) {
        prepareModel(afspraakData, model, "Update");
        plannerService.updateAfspraak(afspraakData);
        return "afspraak";
    }
    @GetMapping("/delete")
    public String afspraakDeleteCreateForm(AfspraakData afspraakData, Model model) {
        prepareModel(afspraakData, model, "Delete");
        plannerService.updateAfspraak(afspraakData);
        return "afspraak";
    }
    @GetMapping("/read")
    public String afspraakReadCreateForm(AfspraakData afspraakData, Model model) {
        prepareModel(afspraakData, model, "Read");
        plannerService.updateAfspraak(afspraakData);
        return "afspraak";
    }

    /**
     * Een POST-request ontvangt via /fastNed/afspraak/X een view (returnwaarde) met model.
     */
    @PostMapping(params = "submit")
    public String afspraakExecuteEdit(AfspraakData afspraakData, Model model) {
        System.out.println("TEST ");
        //prepareModel(afspraakData, model, "Update");
        //plannerService.updateAfspraak(afspraakData);
        return "afspraak";
    }
//    @PostMapping("/delete")
//    public String afspraakExecuteDelete(AfspraakData afspraakData, Model model) {
//        prepareModel(afspraakData, model, "Delete");
//        plannerService.updateAfspraak(afspraakData);
//        return "afspraak";
//    }
//    @PostMapping("/read")
//    public String afspraakExecuteRead(AfspraakData afspraakData, Model model) {
//        prepareModel(afspraakData, model, "Read");
//        plannerService.updateAfspraak(afspraakData);
//        return "afspraak";
//    }

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

<<<<<<< HEAD
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

    @RequestMapping(value = {"/afspraakInfo.html"},method = RequestMethod.GET)
    public String afspraakDetail(@RequestParam("id") long id, ModelMap model){
        Afspraak afspraak = plannerService.searchAfsrpaakById(id);
        model.addAttribute("afspraak", afspraak);
        return "/afspraakInfo";
    }



}

=======
        prepareModel(afspraakData, model, "Create");
        model.addAttribute("message", message);
        return "afspraak";
    }

//    @GetMapping("/fastNed/update?")
//    public String entryDEditFormTest(Model model) {
//
//        AfspraakData entryData = plannerService.prepareNewAfspraakData();
//        prepareForm(entryData, model);
//        // TODO: (Tibo) Niet zeker of dit nog iets te maken heeft met onze afspraak tov het voorbeeld zijn "Entry"
//        model.addAttribute("message", "Update of Verwijder deze afspraak a.u.b. - of Cancel");
//        return "update";
//    }

//    /**
//     * Prepare form for update or delete
//     * @param id - the id of the entry to be updated or deleted
//     * @param model
//     * @return
//     */
//    @GetMapping("/edit")
//    public String entryEditForm(@RequestParam("id") long id, Model model) {
//
//        AfspraakData entryData = plannerService.prepareAfspraakDataToEdit(id);
//        prepareModel(entryData, model);
//        // TODO: (Tibo) Niet zeker of dit nog iets te maken heeft met onze afspraak tov het voorbeeld zijn "Entry"
//        model.addAttribute("message", "Update of Verwijder deze afspraak a.u.b. - of Cancel");
//        return "afspraak";
//    }
//
//    /**
//     * Delete the entry and prepare for creation of a new one
//     * @return
//     */
//    @PostMapping(params = "delete")
//    public String deleteEntry(AfspraakData afspraakData, Model model) {
//
//        plannerService.deleteAfspraak(afspraakData.getId());
//        AfspraakData afspraakDataNieuw = plannerService.prepareNewAfspraakData();
//        prepareModel(afspraakDataNieuw, model);
//        model.addAttribute("message", String.format("Afspraak succesvol verwijderd met id \"%s\"", afspraakData.getId()));
//        return "afspraak";
//    }
//
//    /**
//     * If user does not want to update or delete, he will be redirect to create
//     * @return
//     */
//    @PostMapping(params = "cancel")
//    public String redirectToCreate() {
//        return "redirect:/fastNed";
//    }
}
>>>>>>> master
