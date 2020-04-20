package be.fastned.application.dao.Base;

import be.fastned.application.domain.Base.Entiteit;
import be.fastned.application.AppConfig;
import be.fastned.application.AppRunner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
public abstract class BaseHibernateDao implements BaseDao {

    protected String ENTITY_NAME_BASE; // TEST

    protected Session currentSession(){
        SessionFactory sf =(SessionFactory) AppRunner.getAppContext().getBean(AppConfig.BEAN_SESSIONFACTORY);
        return sf.getCurrentSession();
    }

    public Entiteit getItemById(String id) {
        return (Entiteit) currentSession().createQuery(String.format("from %s where id = \'%s\'", ENTITY_NAME_BASE,id)).uniqueResult();
    }
    public ArrayList<Entiteit> getAllItems() {
        return (ArrayList<Entiteit>) currentSession().createQuery(String.format("from %s", ENTITY_NAME_BASE)).list();
    }

    public String getLastItemId() {
        Entiteit lastItem = (Entiteit) (((ArrayList<Entiteit>)currentSession().createQuery(String.format("select t from %s t order by t.id desc", ENTITY_NAME_BASE)).setMaxResults(1).getResultList()).get(0));
        String lastItemId = lastItem.getId();
        return lastItemId;
    }
    public Boolean isTableEmpty() {
        Session s = currentSession();
        System.out.println("\n\n[!!!]   " + ENTITY_NAME_BASE + "\n\n");
        System.out.println("\n\n[!!!]   " + (s.createQuery(String.format("from %s", ENTITY_NAME_BASE))).list().size() + "\n\n");
        ArrayList<Entiteit> queryList = (ArrayList<Entiteit>)s.createQuery(String.format("from %s", ENTITY_NAME_BASE)).list();
        System.out.println();
        //ArrayList<Entiteit> list = (ArrayList<Entiteit>)s.createQuery(String.format("from %s", ENTITY_NAME_BASE)).setMaxResults(1).getResultList();
        boolean res = queryList.isEmpty();
        return res;
    }
}
