package be.fastned.application.dao;

import be.fastned.application.domain.Laadklant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("laadklantDao")
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
public class LaadklantHibernateDao implements LaadklantDao{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Laadklant deleteLaadklant(Laadklant laadklant){
        return (Laadklant) currentSession().createQuery(String.format("delete from %s where %s = ", "Laadklant", laadklant.getId())).uniqueResult();
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Laadklant addLaadklant(Laadklant laadklant) {
        currentSession().save(laadklant);
        return laadklant;
    }

    public Laadklant getLaadklantById(long id) {
        return (Laadklant) currentSession().createQuery(String.format("from %s where %s = ", "Laadklant",id)).uniqueResult();
    }
}
