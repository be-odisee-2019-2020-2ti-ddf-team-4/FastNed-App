package be.fastned.application.controllers;

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
@RequestMapping("/fastNed")
public class PlannerController {

    @Autowired
    private PlannerService plannerService;

    /**
     * Prepare form for create
     */
    @GetMapping
    public String entryCreateForm(Model model) {
        AfspraakData afspraak = plannerService.prepareNewAfspraakData();
        prepareForm(afspraak, model);
        return "entryAfspraak";
    }

    /**
     * Prepares the form with data for projects- and objectives comboboxes
     */
    private void prepareForm(AfspraakData afspraakData, Model model) {
        model.addAttribute("availableInstallateurs", plannerService.getAvailableInstallateurs());
        model.addAttribute("uncheckedLocatietoestemmingen", plannerService.getAvailableLocatietoestemmingen());
        model.addAttribute("uncheckedProblemen", plannerService.getAvailableProblemen());

        model.addAttribute("afspraakData", afspraakData);
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
        // TODO: Niet zeker of dit nog iets te maken heeft met onze afspraak tov het voorbeeld zijn "Entry"
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
        model.addAttribute("message", String.format("Afspraak succesvol verwijderd met id \"%s\"", afspraakData.getid()));
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
}