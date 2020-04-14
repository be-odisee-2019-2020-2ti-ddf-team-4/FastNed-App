package be.fastned.application.dao;

import be.fastned.application.dao.Interfaces.OplossingDao;
import be.fastned.application.domain.Oplossing;
import be.fastned.application.service.AppConfig;
import be.fastned.application.service.AppRunner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static be.fastned.application.dao.OplossingHibernateDao.BEAN_DAO_NAME;

@Repository(BEAN_DAO_NAME)
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
public class OplossingHibernateDao extends BaseHibernateDao implements OplossingDao {

    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// -#####-------------------------#####- //----------------// */
    /* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####-------------------------#####- //----------------// */

    /* //----------------// SECTIE: Constanten //----------------// */
    public static final String BEAN_DAO_NAME = "oplossingDao";
    private static final String OPLOSSING_ENTITY_NAME = Oplossing.ENTITY_NAME;

    /* //----------------// -#####--------------------#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####--------------------#####- //----------------// */
    OplossingHibernateDao() {
        configureAbstractOperations();
    }

    /* //----------------// -#####----------------#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####----------------#####- //----------------// */

    /* //----------------\\ <||> ------------------ <||> //----------------\\ */
    /* //----------------\\ <||> FUNCTIES Technisch <||> //----------------\\ */
    /* //----------------\\ <||> ------------------ <||> //----------------\\ */
    private void configureAbstractOperations(){
        ENTITY_NAME = OPLOSSING_ENTITY_NAME;
    }

    /* //----------------\\ <||> --------------- <||> //----------------\\ */
    /* //----------------\\ <||> FUNCTIES Domein <||> //----------------\\ */
    /* //----------------\\ <||> --------------- <||> //----------------\\ */

    /* //----------------// SECTIE: Create //----------------// */
    /**
     * Voegt een instantie van deze klasse aan de DB toe als gepersisteerde entiteit.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Oplossing addItem(Oplossing item) {
        currentSession().save(item);
        return item;
    }

    /* //----------------// SECTIE: Read //----------------// */


    /* //----------------// SECTIE: Update //----------------// */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public void updateItem(Oplossing item) {
        currentSession().update(item);
    }

    /* //----------------// SECTIE: Delete //----------------// */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Oplossing deleteItem(Oplossing item){
        return (Oplossing) currentSession().createQuery(String.format("delete from %s where %s = ", ENTITY_NAME, item.getId())).uniqueResult();
    }
}
