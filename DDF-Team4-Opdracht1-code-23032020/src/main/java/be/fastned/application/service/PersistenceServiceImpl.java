package be.fastned.application.service;

import be.fastned.application.dao.Interfaces.ProbleemDao;
import be.fastned.application.domain.AndereEntiteiten.Oplossing;
import be.fastned.application.domain.AndereEntiteiten.Probleem;
import be.fastned.application.service.Interfaces.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
public class PersistenceServiceImpl implements PersistenceService {

    @Autowired
    private ProbleemDao probleemDao;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Oplossing voegOplossingToe(String oplossing, String probleem) {
        //System.out.println("TEST");
        Probleem aangemaaktProbleem = new Probleem(null, probleem);
        Oplossing aangemaakteOplossing = new Oplossing(oplossing);
        aangemaaktProbleem.setOplossing(aangemaakteOplossing);
        //probleemDao.createProbleem(aangemaaktProbleem);
        return aangemaakteOplossing;
    }

//    public Probleem zoekOplossingMetId(String id) {
//        return (Probleem) probleemDao.getItemById(id);
//    }
//
//    public ArrayList<AbsoluteBase> zoekOplossingAlles() {
//        return probleemDao.getAllItems();
//    }
}



