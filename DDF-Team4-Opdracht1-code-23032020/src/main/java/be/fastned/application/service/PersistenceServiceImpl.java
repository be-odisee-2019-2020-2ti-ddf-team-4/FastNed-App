package be.fastned.application.service;

import be.fastned.application.dao.AfspraakDao;
import be.fastned.application.domain.Afspraak;
import be.fastned.application.domain.Contract;
import be.fastned.application.domain.Installateur;
import be.fastned.application.domain.Laadpaal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
public class PersistenceServiceImpl implements PersistenceService {

    private AfspraakDao afspraakDao;

    @Autowired
    public void setAfspraakDao(AfspraakDao afspraakDao) {
        this.afspraakDao = afspraakDao;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Afspraak voegAfspraakToe(Laadpaal laadpaal, Installateur installateur, Contract contract) {
        Afspraak nieuweAfspraak = new Afspraak(laadpaal, installateur, contract);
        return afspraakDao.addAfspraak(nieuweAfspraak);
    }

    public Afspraak zoekAfspraakMetId(long id) {
        return afspraakDao.getAfspraakById(id);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public void updateAfspraakStatus(Afspraak toBeUpdated, String status) {
        toBeUpdated.setStatus(status);
        afspraakDao.updateAfspraak(toBeUpdated);
    }
}



