package be.fastned.application.service;

import be.fastned.application.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.time.Month;

public class AppRunner {
    private static DefaultScenario ScenarioInst = null;
    private static Locatiehouder TestLocatiehouder = null;
    private static Installateur TestInstallateur = null;
    private static FastNedAppImpl FastNedAppServiceImpl = null;
    private static PersistenceService persistenceService = null;
    private static AnnotationConfigApplicationContext applicationContextInst;

    @Autowired
    public AppRunner(Locatiehouder testLocatiehouder, Installateur testInstallateur, FastNedAppImpl FastNedAppServiceImplInst){
        TestLocatiehouder = testLocatiehouder;
        TestInstallateur = testInstallateur;
        FastNedAppServiceImpl = FastNedAppServiceImplInst;
    }

    public static void main(String[] args) {


        System.out.println("De applicatie start op..");
        System.out.println("Systeemconfiguratie start..");
        System.out.println();

        try (AnnotationConfigApplicationContext applicationContext =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            System.out.println("Applicatiecontext geïnitialiseerd");
            System.out.println();
            System.out.println("Systeemconfiguratie is afgerond..");

            applicationContextInst = applicationContext;
            ScenarioInst = (DefaultScenario) applicationContextInst.getBean("defaultScenarioInst");

            try{
                demoPersistentieTransactie();
            }
            catch(Exception ex){
                System.out.println("Bug opgevangen in het persistentie-/transactie-voorbeeld in de Main-methode (AppRunner) met exception: " + ex.getMessage());
                System.out.println();
            }

            try{
                Persoon persoon = (Persoon) applicationContext.getBean("testInstallateur");
                String test = persoon.getNaam();
                ScenarioInst.runScenario(applicationContext);
            }
            catch(Exception ex){
                System.out.println("Bug opgevangen in het Dependendency-voorbeeld in de Main-methode (AppRunner) met exception: " + ex.getMessage());
                System.out.println();
                throw ex;
            }
            closeApplication();
        }
        catch(Exception ex){
            System.out.println("Bug opgevangen in de Main-methode (AppRunner) met exception: " + ex.getMessage());
            System.out.println();
        }
    }

    public static void closeApplication(){
        System.out.println("De applicatie sluit af..");
        Runtime.getRuntime().exit(0);
    }

    private static void demoPersistentieTransactie(){
        persistenceService = (PersistenceService) applicationContextInst.getBean("persistenceService");
        System.out.println();
        System.out.println("De Persistentie en Transactie demo start..");

        // Creeëren van een afspraak via persistentie & transactie
        Laadpaal laadpaal = new Laadpaal(TestLocatiehouder, "A");
        Contract contract = new Contract(LocalDateTime.now(), LocalDateTime.of(2020, Month.JULY, 10, 12, 00, 00));
        Afspraak createdAfspraak = persistenceService.voegAfspraakToe(laadpaal, TestInstallateur, contract);

        // Aanpassen van een attribuut van een afspraak
        persistenceService.updateAfspraakStatus(createdAfspraak, "Aangepast");

        // Opzoeken van net aangemaakte afspraak
        Afspraak zoekAfspraak = persistenceService.zoekAfspraakMetId(1);

        System.out.println(String.format("Aangemaakte afspraak door %s op %s bij %s", createdAfspraak.getInstallateur().getNaam(), createdAfspraak.getContract().getInstallatieDatum(), createdAfspraak.getLaadpaal().getLocatieEigenaar().getNaam()));
        System.out.println("De Persistentie en Transactie demo is beëindigt..");
        System.out.println();
    }
}