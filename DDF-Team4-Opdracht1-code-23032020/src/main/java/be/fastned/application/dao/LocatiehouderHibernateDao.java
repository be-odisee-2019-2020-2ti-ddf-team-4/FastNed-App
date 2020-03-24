package be.fastned.application.dao;

import be.fastned.application.domain.Locatiehouder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("locatiehouderDao")
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
public class LocatiehouderHibernateDao implements LocatiehouderDao{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Locatiehouder deleteLocatiehouder(Locatiehouder locatiehouder){
        return (Locatiehouder) currentSession().createQuery(String.format("delete from %s where %s = ", "Locatiehouder", locatiehouder.getId())).uniqueResult();
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Locatiehouder addLocatiehouder(Locatiehouder locatiehouder) {
        currentSession().save(locatiehouder);
        return locatiehouder;
    }

    public Locatiehouder getLocatiehouderById(long id) {
        return (Locatiehouder) currentSession().createQuery(String.format("from %s where %s = ", "Locatiehouder",id)).uniqueResult();
    }
}
