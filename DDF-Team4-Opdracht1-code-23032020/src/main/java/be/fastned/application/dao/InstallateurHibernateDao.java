package be.fastned.application.dao;

import be.fastned.application.dao.Base.BaseHibernateDao;
import be.fastned.application.dao.Interfaces.InstallateurDao;
import be.fastned.application.domain.Personen.Installateur;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static be.fastned.application.dao.InstallateurHibernateDao.BEAN_DAO_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Repository(BEAN_DAO_NAME)
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)

public class InstallateurHibernateDao extends BaseHibernateDao implements InstallateurDao {

    /* //----------------// -#########--------------------------------#########- //----------------// */
    /* //----------------// -#########- &|& INSTANTIE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########--------------------------------#########- //----------------// */

    /* //----------------// -#########-----------------------------#########- //----------------// */
    /* //----------------// -#########- &|& KLASSE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########-----------------------------#########- //----------------// */

    /* //----------------// SECTIE: Constanten //----------------// */
    public static final String BEAN_DAO_NAME = "installateurDao";
    private static final String INSTALLATEUR_ENTITY_NAME = Installateur.ENTITY_NAME;

    /* //----------------// -#########------------------------#########- //----------------// */
    /* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
    /* //----------------// -#########------------------------#########- //----------------// */

    InstallateurHibernateDao() {
        configureAbstractOperations();
    }

    /* //----------------// -#########--------------------#########- //----------------// */
    /* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
    /* //----------------// -#########--------------------#########- //----------------// */

    /* //----------------\\ # ------------------ # //----------------\\ */
    /* //----------------\\ # Functies Technisch # //----------------\\ */
    /* //----------------\\ # ------------------ # //----------------\\ */
    private void configureAbstractOperations(){
        ENTITY_NAME = INSTALLATEUR_ENTITY_NAME;
    }

    /* //----------------\\ # --------------- # //----------------\\ */
    /* //----------------\\ # Functies Domein # //----------------\\ */
    /* //----------------\\ # --------------- # //----------------\\ */

    /* //----------------// SECTIE: Create //----------------// */
    /**
     * Voegt een instantie van deze klasse aan de DB toe als gepersisteerde entiteit.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Installateur createItem(Installateur item) {
        currentSession().save(item);
        return item;
    }

    /* //----------------// SECTIE: Read //----------------// */


    /* //----------------// SECTIE: Update //----------------// */
    /**
     * Updated de gepersisteerde entiteit achter een instantie.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public void updateItem(Installateur item) {
        currentSession().update(item);
    }

    /* //----------------// SECTIE: Delete //----------------// */
    /**
     * Deleted de gepersisteerde entiteit achter een instantie.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Installateur deleteItem(Installateur item){
        return (Installateur) currentSession().createQuery(String.format("delete from %s where %s = ", ENTITY_NAME, item.getId())).uniqueResult();
    }
}