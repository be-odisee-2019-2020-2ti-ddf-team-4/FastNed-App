package be.fastned.application.code.service;

import be.fastned.application.code.domain.Oplossing;
import be.fastned.application.code.domain.Probleem;
import be.fastned.application.code.service.Interfaces.PersistenceService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
public class PersistenceServiceImpl implements PersistenceService {

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Oplossing voegOplossingToe(String oplossing, String probleem) {
        Probleem aangemaaktProbleem = new Probleem(null, probleem);
        Oplossing aangemaakteOplossing = new Oplossing(oplossing);
        aangemaaktProbleem.setOplossing(aangemaakteOplossing);
        return aangemaakteOplossing;
    }
}



