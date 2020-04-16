package be.fastned.application.service;

import be.fastned.application.dao.Personen.InstallateurDao;
import be.fastned.application.dao.Personen.PersoonDao;
import be.fastned.application.domain.Personen.Installateur;
import be.fastned.application.service.Interfaces.AppService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:52
 */

@Service

public class AppServiceImpl implements AppService {

    private static AppServiceImpl uniqueInstance;
    private static AnnotationConfigApplicationContext appContext;

    /**
     * Default private Constructor voor deze klasse.
     */
    private AppServiceImpl() {}
    public static AppServiceImpl getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new AppServiceImpl();
        }
        appContext = AppRunner.getAppContext();
        return uniqueInstance;
    }

    public void testPersistentie(){
        System.out.println("\nPersisteren oplossing en probleem als demo start..");

        //PersoonImpl testPersoon = new PersoonImpl("Mr. Testudo", "test.vg@gmail.com", "ww");
        PersoonDao persoonDao = (PersoonDao)appContext.getBean("persoonDao");

        Installateur testInstallateur = new Installateur("Mr. Testudo 2", "test.vg2@gmail.com", "ww2");
        InstallateurDao installateurDao = (InstallateurDao)appContext.getBean("installateurDao");

        boolean doCreate = true,
                doUpdate = false;

        if (doCreate) {
            installateurDao.createItem(testInstallateur);
        }

        if (doUpdate){
            testInstallateur.setGebruikersnaam("Updated");
            installateurDao.updateItem(testInstallateur);
        }

        System.out.println("Persisteren oplossing en probleem als demo beëndigd..\n");
    }
}