package be.fastned.application.dao;

import be.fastned.application.dao.Interfaces.AfspraakDao;
import be.fastned.application.dao.Interfaces.LaadsessieDao;
import be.fastned.application.domain.Afspraak;
import be.fastned.application.domain.Laadsessie;
import be.fastned.application.service.AppConfig;
import be.fastned.application.service.AppRunner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static be.fastned.application.dao.LaadsessieHibernateDao.BEAN_DAO_NAME;

@Repository(BEAN_DAO_NAME)
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)

public class LaadsessieHibernateDao extends BaseHibernateDao implements LaadsessieDao {

    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// -#####-------------------------#####- //----------------// */
    /* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####-------------------------#####- //----------------// */

    /* //----------------// SECTIE: Constanten //----------------// */
    public static final String BEAN_DAO_NAME = "laadsessieDao";
    private static final String LAADSESSIE_ENTITY_NAME = Laadsessie.ENTITY_NAME;

    /* //----------------// -#####--------------------#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####--------------------#####- //----------------// */
    LaadsessieHibernateDao() {
        configureAbstractOperations();
    }

    /* //----------------// -#####----------------#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####----------------#####- //----------------// */

    /* //----------------\\ <||> ------------------ <||> //----------------\\ */
    /* //----------------\\ <||> FUNCTIES Technisch <||> //----------------\\ */
    /* //----------------\\ <||> ------------------ <||> //----------------\\ */
    private void configureAbstractOperations(){
        ENTITY_NAME = LAADSESSIE_ENTITY_NAME;
    }

    /* //----------------\\ <||> --------------- <||> //----------------\\ */
    /* //----------------\\ <||> FUNCTIES Domein <||> //----------------\\ */
    /* //----------------\\ <||> --------------- <||> //----------------\\ */

    /* //----------------// SECTIE: Create //----------------// */
    /**
     * Voegt een instantie van deze klasse aan de DB toe als gepersisteerde entiteit.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Laadsessie addItem(Laadsessie item) {
        currentSession().save(item);
        return item;
    }

    /* //----------------// SECTIE: Read //----------------// */


    /* //----------------// SECTIE: Update //----------------// */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public void updateItem(Laadsessie item) {
        currentSession().update(item);
    }

    /* //----------------// SECTIE: Delete //----------------// */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Laadsessie deleteItem(Laadsessie item){
        return (Laadsessie) currentSession().createQuery(String.format("delete from %s where %s = ", ENTITY_NAME, item.getId())).uniqueResult();
    }
}
