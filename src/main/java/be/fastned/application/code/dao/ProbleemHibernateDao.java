package be.fastned.application.code.dao;

import be.fastned.application.code.dao.Base.BaseHibernateDao;
import be.fastned.application.code.dao.Interfaces.ProbleemDao;
import be.fastned.application.code.domain.Probleem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static be.fastned.application.code.dao.ProbleemHibernateDao.BEAN_DAO_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Repository(BEAN_DAO_NAME)
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)

public class ProbleemHibernateDao extends BaseHibernateDao implements ProbleemDao {

    /* //----------------// -#########--------------------------------#########- //----------------// */
    /* //----------------// -#########- &|& INSTANTIE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########--------------------------------#########- //----------------// */

    /* //----------------// -#########-----------------------------#########- //----------------// */
    /* //----------------// -#########- &|& KLASSE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########-----------------------------#########- //----------------// */

    /* //----------------// SECTIE: Constanten //----------------// */
    public static final String BEAN_DAO_NAME = "probleemDao";
    private static final String PROBLEEM_ENTITY_NAME = Probleem.ENTITY_NAME;

    /* //----------------// -#########------------------------#########- //----------------// */
    /* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
    /* //----------------// -#########------------------------#########- //----------------// */
    ProbleemHibernateDao() {
        configureAbstractOperations();
    }

    /* //----------------// -#########--------------------#########- //----------------// */
    /* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
    /* //----------------// -#########--------------------#########- //----------------// */

    /* //----------------\\ # ------------------ # //----------------\\ */
    /* //----------------\\ # Functies Base # //----------------\\ */
    /* //----------------\\ # ------------------ # //----------------\\ */
    private void configureAbstractOperations(){
        ENTITY_NAME_BASE = PROBLEEM_ENTITY_NAME;
    }

    /* //----------------\\ # --------------- # //----------------\\ */
    /* //----------------\\ # Functies Domein # //----------------\\ */
    /* //----------------\\ # --------------- # //----------------\\ */

    /* //----------------// SECTIE: Create //----------------// */
    /**
     * Voegt een instantie van deze klasse aan de DB toe als gepersisteerde entiteit.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Probleem createItem(Probleem item) {
        currentSession().save(item);
        return item;
    }

    /* //----------------// SECTIE: Read //----------------// */


    /* //----------------// SECTIE: Update //----------------// */
    /**
     * Updated de gepersisteerde entiteit achter een instantie.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public void updateItem(Probleem item) {
        currentSession().update(item);
    }

    /* //----------------// SECTIE: Delete //----------------// */
    /**
     * Deleted de gepersisteerde entiteit achter een instantie.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Probleem deleteItem(Probleem item){
        return (Probleem) currentSession().createQuery(String.format("delete from %s where %s = ", ENTITY_NAME_BASE, item.getId())).uniqueResult();
    }
}