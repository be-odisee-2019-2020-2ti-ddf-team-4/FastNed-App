package be.fastned.application.dao;

import be.fastned.application.dao.Interfaces.PlannerDao;
import be.fastned.application.domain.Personen.Planner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//@Repository("plannerDao")
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
public class PlannerHibernateDao implements PlannerDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Planner deletePlanner(Planner planner){
        return (Planner) currentSession().createQuery(String.format("delete from %s where %s = ", "Planner", planner.getId())).uniqueResult();
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Planner addPlanner(Planner planner) {
        currentSession().save(planner);
        return planner;
    }

    public Planner getPlannerById(long id) {
        return (Planner) currentSession().createQuery(String.format("from %s where %s = ", "Planner",id)).uniqueResult();
    }
}
