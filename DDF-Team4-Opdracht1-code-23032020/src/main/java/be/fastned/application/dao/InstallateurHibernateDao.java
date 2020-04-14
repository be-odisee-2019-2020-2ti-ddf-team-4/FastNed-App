package be.fastned.application.dao;

import be.fastned.application.dao.Interfaces.InstallateurDao;
import be.fastned.application.domain.Personen.Installateur;
import be.fastned.application.service.AppRunner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Repository("installateurDao")
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
@Component("installateurDao")
public class InstallateurHibernateDao implements InstallateurDao {

    private static final String TABLE = "Installateur";
    protected SessionFactory sessionFactory;

    protected Session currentSession(){
        return ((SessionFactory) AppRunner.getAppContext().getBean("sessionFactory")).getCurrentSession();
    }

    // Create
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Installateur createInstallateur(Installateur installateur) {
        currentSession().save(installateur);
        return installateur;
    }

    // Read
    public Installateur getInstallateurById(String id) {
        return (Installateur) currentSession().createQuery(String.format("from %s where id = \'%s\'", TABLE,id)).uniqueResult();
    }
    public ArrayList<Installateur> getAllInstallateurs() {
        return (ArrayList<Installateur>) currentSession().createQuery(String.format("from %s", TABLE)).list();
    }

    public String getLastItemId() {
        return (String) ((Installateur)currentSession().createQuery(String.format("SELECT TOP 1 * FROM %s ORDER BY INSTR_ID DESC ", TABLE)).uniqueResult()).getId();
    }
    public Boolean isTableEmpty() {
        return ((Installateur)currentSession().createQuery(String.format("SELECT TOP 1 * FROM %s ORDER BY INSTR_ID DESC ", TABLE)).uniqueResult() == null);
    }

    // Update
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Installateur updateInstallateur(Installateur installateur) {
        currentSession().update(installateur);
        return installateur;
    }

    // Delete
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Installateur deleteInstallateur(Installateur installateur){
        return (Installateur) currentSession().createQuery(String.format("DELETE from %s where %s = ", TABLE, installateur.getId())).uniqueResult();
    }
}
