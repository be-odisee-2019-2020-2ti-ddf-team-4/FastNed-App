package be.fastned.application.service;

import be.fastned.application.domain.AbsoluteBase;
import be.fastned.application.domain.Oplossing;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;
import be.fastned.application.domain.Probleem;
import be.fastned.application.service.Interfaces.AppService;
import be.fastned.application.service.Interfaces.PersistenceService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:52
 */
@Service
public class AppServiceImpl implements AppService {
    public Persoon m_HuidigePersoon;
    public Object m_GetoondObject;

    private static AppServiceImpl s_UniqueInstance;
    private static AnnotationConfigApplicationContext s_ApplicationContext;

    /**
     * Default private Constructor voor deze klasse. */
    private AppServiceImpl() {}
    public static AppServiceImpl getInstance(){
        if (s_UniqueInstance == null){
            s_UniqueInstance = new AppServiceImpl();
        }
        s_ApplicationContext = AppRunner.s_ApplicationContext;
        return s_UniqueInstance;
    }

    public void persisteerDemoAfspraak(){
        PersistenceService persistenceService = (PersistenceService) s_ApplicationContext.getBean("persistenceService");
        System.out.println();
        System.out.println("Persisteren oplossing en probleem als demo start..");

        System.out.println("Persisteren oplossing en probleem als demo beëndigd..");
    }

    public void demoAOP(){

    }

    public void demoScenarioMaakAfspraak(){

    }

    public void demoPersistentieTransactie(){

    }

    /**
     * Deze domein-attribuut setter vertegenwoordigt de HuidigePersoon. */
    public void setHuidigePersoon(Persoon value){
        this.m_HuidigePersoon = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de HuidigePersoon. */
    public Persoon getHuidigePersoon(){
        return this.m_HuidigePersoon;
    }

    /* //----------------// PROPERTY: GetoondObject //----------------// */
    /**
     * Deze domein-attribuut setter vertegenwoordigt de GetoondObject. */
    public void setGetoondObject(Object value){
        this.m_GetoondObject = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de GetoondObject. */
    public Object getGetoondObject(){
        return this.m_GetoondObject;
    }
}