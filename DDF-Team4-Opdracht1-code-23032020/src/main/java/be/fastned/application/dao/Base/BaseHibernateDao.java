package be.fastned.application.dao.Base;

import be.fastned.application.domain.Base.EntiteitBaseImpl;
import be.fastned.application.domain.AndereEntiteiten.Oplossing;
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

    public EntiteitBaseImpl getItemById(String id) {
        return (EntiteitBaseImpl) currentSession().createQuery(String.format("from %s where id = \'%s\'", ENTITY_NAME,id)).uniqueResult();
    }
    public ArrayList<EntiteitBaseImpl> getAllItems() {
        return (ArrayList<EntiteitBaseImpl>) currentSession().createQuery(String.format("from %s", ENTITY_NAME)).list();
    }

    public String getLastItemId() {
        EntiteitBaseImpl lastItem = (EntiteitBaseImpl) (((ArrayList<Oplossing>)currentSession().createQuery(String.format("select t from %s t order by t.id desc", ENTITY_NAME)).setMaxResults(1).getResultList()).get(0));
        String lastItemId = lastItem.getId();
        return lastItemId;
    }
    public Boolean isTableEmpty() {
        int resultSize = ((ArrayList<EntiteitBaseImpl>)currentSession().createQuery(String.format("from %s", ENTITY_NAME)).setMaxResults(1).getResultList()).size();
        return (resultSize == 0);
    }
}
