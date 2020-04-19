package be.fastned.application.service;

import be.fastned.application.dao.Interfaces.AfspraakDao;
import be.fastned.application.dao.Interfaces.PersoonDao;
import be.fastned.application.domain.Afspraak;
import be.fastned.application.domain.Contract;
import be.fastned.application.domain.Laadpaal;
import be.fastned.application.domain.Personen.Installateur;
import be.fastned.application.domain.Personen.Locatiehouder;
import be.fastned.application.service.Interfaces.AppService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        System.out.println("\n[!!!]  Persisteren oplossing en probleem als demo start..");

//        Installateur installateurTest = new Installateur("Mr. Testudo 2", "test.vg2@gmail.com", "ww2");
//        InstallateurDao installateurDao = (InstallateurDao)appContext.getBean("installateurDao");
//
//        PersoonImpl persoonTest = installateurTest;
        PersoonDao persoonDao = (PersoonDao)appContext.getBean("persoonDao");
        AfspraakDao afspraakDao = (AfspraakDao)appContext.getBean("afspraakDao");

        boolean doCreate = false,
                doUpdate = false;

        if (doCreate) {
            for (int i = 0; i < 2; i++) {

                Locatiehouder locatiehouderTest = new Locatiehouder("Mr. Testudo 2", "test.vg2@gmail.com", "ww2");
                Laadpaal laadpaalTest = new Laadpaal(locatiehouderTest,"A");
                Installateur installateurTest = new Installateur("Mr. Testudo 2", "test.vg2@gmail.com", "ww2");
                Contract contractTest = new Contract(LocalDateTime.now(),LocalDateTime.now());
                Afspraak afspraakTest = new Afspraak(laadpaalTest, installateurTest, contractTest);
                afspraakDao.createItem(afspraakTest);
            }
        }

        if (doUpdate){
            // Do update
        }

        System.out.println("[!!!]  Persisteren oplossing en probleem als demo beëndigd..\n");
    }
}