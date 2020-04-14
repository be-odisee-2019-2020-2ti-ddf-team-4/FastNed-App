package be.fastned.application.service;

import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;
import be.fastned.application.service.Interfaces.AppService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.Column;
import javax.persistence.Transient;

public class AppRunner {
    private static AppService s_AppService = null;
    public static AnnotationConfigApplicationContext s_ApplicationContext = null;
    private static Persoon actieveGebruiker;

    public static void main(String[] args) {
        s_ApplicationContext = initieerContext();
        s_AppService = AppServiceImpl.getInstance();
        s_AppService.persisteerDemoAfspraak();

        // Isoleren van appContext-initiatie errors en verdere errors bij appContext-gebruik.
        /*try{
            // Context SUCCESVOL Initialiseren!
            s_ApplicationContext = initieerContext();
            s_AppService = AppServiceImpl.getInstance();

            // Context SUCCESVOL Gebruiken!
            try {
                s_AppService.persisteerDemoAfspraak();
                // ...
            }

            // Context-Gebruik is MISLUKT!
            catch(Exception ex){
                System.out.println("\nBug (NIET context init) opgevangen in de Main-methode (AppRunner) met exception:\n" + ex);
                throw new Exception("fout opgooien");
            }

        // Context Initialiseren is MISLUKT!
        /*catch (Exception ex){
            // Omitten van reeds getoonde fouten!
            if (ex.getMessage() != "fout opgooien"){
                System.out.println("\nBug (WEL context init) opgevangen in de Main-methode (AppRunner) met exception:\n" + ex);
                System.out.println("\n!Systeemconfiguratie Problematisch Beëindigd! -> Afsluiten Applicatie\n");
            }
        }*/

        // Afsluiten van het systeem.
        closeApplication();
    }

    /**
     * Deze domein-attribuut getter vertegenwoordigt het reparatiedocumentatie-attribuut.
     */
    public static Persoon getActieveGebruiker(){
        return actieveGebruiker;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt het reparatiedocumentatie-attribuut.
     */
    public static void setActieveGebruiker(Persoon value){
        actieveGebruiker = value;
    }

    public static AnnotationConfigApplicationContext getAppContext(){
        return applicationContext;
    }

    private static  AnnotationConfigApplicationContext applicationContext = null;
    private static AnnotationConfigApplicationContext initieerContext(){
        System.out.println("!Systeemconfiguratie Start!\n");
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("\n!Systeemconfiguratie Succesvol Beëindigd! -> Verder Verloop Applicatie\n");
        return applicationContext;
    }

    private static void closeApplication(){
        System.out.println("De applicatie sluit af..");
        Runtime.getRuntime().exit(0);
    }
}