package be.fastned.application.dao;

import be.fastned.application.dao.Interfaces.BaseDao;
import be.fastned.application.domain.AbsoluteBase;
import be.fastned.application.domain.Oplossing;
import be.fastned.application.service.AppConfig;
import be.fastned.application.service.AppRunner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.ArrayList;

public abstract class BaseHibernateDao implements BaseDao {

    protected static String ENTITY_NAME;

    protected Session currentSession(){
        return ((SessionFactory) AppRunner.getAppContext().getBean(AppConfig.BEAN_SESSIONFACTORY)).getCurrentSession();
    }

    public AbsoluteBase getItemById(String id) {
        return (AbsoluteBase) currentSession().createQuery(String.format("from %s where id = \'%s\'", ENTITY_NAME,id)).uniqueResult();
    }
    public ArrayList<AbsoluteBase> getAllItems() {
        return (ArrayList<AbsoluteBase>) currentSession().createQuery(String.format("from %s", ENTITY_NAME)).list();
    }

    public String getLastItemId() {
        Oplossing lastItem = (Oplossing) (((ArrayList<Oplossing>)currentSession().createQuery(String.format("select t from %s t order by t.id desc", ENTITY_NAME)).setMaxResults(1).getResultList()).get(0));
        String lastItemId = lastItem.getId();
        return lastItemId;
    }
    public Boolean isTableEmpty() {
        int resultSize = ((ArrayList<Oplossing>)currentSession().createQuery(String.format("select t from %s t order by t.id desc", ENTITY_NAME)).setMaxResults(1).getResultList()).size();
        return (resultSize == 0);
    }
}
