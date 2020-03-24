package be.fastned.application.dao;

import be.fastned.application.domain.Installateur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("installateurDao")
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
public class InstallateurHibernateDao implements InstallateurDao{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Installateur deleteInstallateur(Installateur installateur){
        return (Installateur) currentSession().createQuery(String.format("delete from %s where %s = ", "Installateur", installateur.getId())).uniqueResult();
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Installateur addInstallateur(Installateur installateur) {
        currentSession().save(installateur);
        return installateur;
    }

    public Installateur getInstallateurById(long id) {
        return (Installateur) currentSession().createQuery(String.format("from %s where %s = ", "Installateur",id)).uniqueResult();
    }
}
