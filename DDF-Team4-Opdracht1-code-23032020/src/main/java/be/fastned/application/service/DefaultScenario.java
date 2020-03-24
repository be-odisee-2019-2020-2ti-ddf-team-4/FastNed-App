package be.fastned.application.service;

import be.fastned.application.boundary.SchermLaadklant;
import be.fastned.application.domain.Laadpaal;
import be.fastned.application.domain.Locatiehouder;
import be.fastned.application.domain.Persoon;
import be.fastned.application.domain.Probleem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component("defaultScenarioInst")
public class DefaultScenario implements Scenario {
    @Autowired
    Locatiehouder testLocatiehouder;
    @Autowired
    SchermLaadklant schermLaadklantInst;

    private static AnnotationConfigApplicationContext applicationContext;

    public void runScenario(AnnotationConfigApplicationContext applicationContextInst) {
        applicationContext = applicationContextInst;
        System.out.println();
        System.out.println("De Dependency Injection demo (Scenario) start..");
        Laadpaal laadpaal = new Laadpaal(testLocatiehouder, "A");
        Probleem output = schermLaadklantInst.meldProbleem(laadpaal, "Compleet Offline");

        String besch = output.getBeschrijving();
        String naam = schermLaadklantInst.getLaadklant().getNaam();

        System.out.println(String.format("Aangemaakt Probleem: %s via %s" , besch, naam ));
        System.out.println("De Dependency Injection demo (Scenario) is beëindigt..");
        System.out.println();
    }
}