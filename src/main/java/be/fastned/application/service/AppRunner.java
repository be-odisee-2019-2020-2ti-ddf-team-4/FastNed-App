package be.fastned.application.service;

import be.fastned.application.domain.PersoonEntiteiten.Persoon;
import be.fastned.application.service.Interfaces.AppService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:52
 */

public class AppRunner {
    private static AnnotationConfigApplicationContext appContext = null;
    private static AppService appService = null;
    private static AppService appServiceTest = null;

    private static Persoon actieveGebruiker;

    public static void main(String[] args) {
        appContext = initieerContext();
        appService = AppServiceImpl.getInstance();
        appService.testPersistentie();

        // Afsluiten van het systeem.
        closeApplication();
    }

    private static AnnotationConfigApplicationContext initieerContext(){
        System.out.println("   !Systeemconfiguratie Start!   \n");
        AnnotationConfigApplicationContext appContextLocal = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("\n   !Systeemconfiguratie Succesvol Beëindigd!   \n");
        return appContextLocal;
    }

    private static void closeApplication(){
        System.out.println("De applicatie sluit af..");
        Runtime.getRuntime().exit(0);
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

    /**
     * Deze domein-attribuut getter vertegenwoordigt het reparatiedocumentatie-attribuut.
     */
    public static AnnotationConfigApplicationContext getAppContext(){
        return appContext;
    }
}