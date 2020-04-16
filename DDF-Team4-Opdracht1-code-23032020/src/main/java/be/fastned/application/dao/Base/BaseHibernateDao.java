package be.fastned.application.dao.Base;

import be.fastned.application.domain.Base.Entiteit;
import be.fastned.application.service.AppConfig;
import be.fastned.application.service.AppRunner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
public abstract class BaseHibernateDao implements BaseDao {

    protected static String ENTITY_NAME;

    protected Session currentSession(){
        SessionFactory sf =(SessionFactory) AppRunner.getAppContext().getBean(AppConfig.BEAN_SESSIONFACTORY);
        System.out.println(sf + " e");
        System.out.println(sf.getCurrentSession() + " c");
        return sf.getCurrentSession();
    }

    public Entiteit getItemById(String id) {
        return (Entiteit) currentSession().createQuery(String.format("from %s where id = \'%s\'", ENTITY_NAME,id)).uniqueResult();
    }
    public ArrayList<Entiteit> getAllItems() {
        return (ArrayList<Entiteit>) currentSession().createQuery(String.format("from %s", ENTITY_NAME)).list();
    }

    public String getLastItemId() {
        Entiteit lastItem = (Entiteit) (((ArrayList<Entiteit>)currentSession().createQuery(String.format("select t from %s t order by t.id desc", ENTITY_NAME)).setMaxResults(1).getResultList()).get(0));
        String lastItemId = lastItem.getId();
        return lastItemId;
    }
    public Boolean isTableEmpty() {
        ArrayList<Entiteit> list = (ArrayList<Entiteit>)currentSession().createQuery(String.format("from %s", ENTITY_NAME)).setMaxResults(1).getResultList();
        return list.isEmpty();
    }
}
