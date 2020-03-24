package be.fastned.application.dao;

import be.fastned.application.domain.Afspraak;
import org.hibernate.SessionFactory;
import org.hibernate.Session;										// HV 20150210
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("afspraakDao")
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
public class AfspraakHibernateDao implements AfspraakDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Afspraak deleteAfspraak(Afspraak afspraak){
        return (Afspraak) currentSession().createQuery(String.format("delete from %s where %s = ", "Afspraak", afspraak.getId())).uniqueResult();
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Afspraak addAfspraak(Afspraak rekening) {
        currentSession().save(rekening);
        return rekening;
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public void updateAfspraak(Afspraak rekening) {
        currentSession().update(rekening);
    }

    public Afspraak getAfspraakById(long id) {
        return (Afspraak) currentSession().createQuery(String.format("from Afspraak where id = ", "Afspraak",id)).uniqueResult();
    }
}
