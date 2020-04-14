package be.fastned.application.dao;

import be.fastned.application.dao.Interfaces.LaadpaalDao;
import be.fastned.application.domain.Afspraak;
import be.fastned.application.domain.Laadpaal;
import be.fastned.application.service.AppConfig;
import be.fastned.application.service.AppRunner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static be.fastned.application.dao.LaadpaalHibernateDao.BEAN_DAO_NAME;

@Repository(BEAN_DAO_NAME)
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)

public class LaadpaalHibernateDao extends BaseHibernateDao implements LaadpaalDao {

    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// -#####-------------------------#####- //----------------// */
    /* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####-------------------------#####- //----------------// */

    /* //----------------// SECTIE: Constanten //----------------// */
    public static final String BEAN_DAO_NAME = "laadpaalDao";
    private static final String LAADPAAL_ENTITY_NAME = Laadpaal.ENTITY_NAME;

    /* //----------------// -#####--------------------#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####--------------------#####- //----------------// */
    LaadpaalHibernateDao() {
        configureAbstractOperations();
    }

    /* //----------------// -#####----------------#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####----------------#####- //----------------// */

    /* //----------------\\ <||> ------------------ <||> //----------------\\ */
    /* //----------------\\ <||> FUNCTIES Technisch <||> //----------------\\ */
    /* //----------------\\ <||> ------------------ <||> //----------------\\ */
    private void configureAbstractOperations(){
        ENTITY_NAME = LAADPAAL_ENTITY_NAME;
    }

    /* //----------------\\ <||> --------------- <||> //----------------\\ */
    /* //----------------\\ <||> FUNCTIES Domein <||> //----------------\\ */
    /* //----------------\\ <||> --------------- <||> //----------------\\ */

    /* //----------------// SECTIE: Create //----------------// */
    /**
     * Voegt een instantie van deze klasse aan de DB toe als gepersisteerde entiteit.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Laadpaal addItem(Laadpaal item) {
        currentSession().save(item);
        return item;
    }

    /* //----------------// SECTIE: Read //----------------// */


    /* //----------------// SECTIE: Update //----------------// */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public void updateItem(Laadpaal item) {
        currentSession().update(item);
    }

    /* //----------------// SECTIE: Delete //----------------// */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Laadpaal deleteItem(Laadpaal item){
        return (Laadpaal) currentSession().createQuery(String.format("delete from %s where %s = ", ENTITY_NAME, item.getId())).uniqueResult();
    }
}
