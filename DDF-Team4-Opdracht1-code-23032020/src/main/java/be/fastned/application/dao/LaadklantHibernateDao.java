package be.fastned.application.dao;

import be.fastned.application.dao.Base.BaseHibernateDao;
import be.fastned.application.dao.Interfaces.LaadklantDao;
import be.fastned.application.domain.Personen.Laadklant;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static be.fastned.application.dao.LaadklantHibernateDao.BEAN_DAO_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Repository(BEAN_DAO_NAME)
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)

public class LaadklantHibernateDao extends BaseHibernateDao implements LaadklantDao {

    /* //----------------// -#########--------------------------------#########- //----------------// */
    /* //----------------// -#########- &|& INSTANTIE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########--------------------------------#########- //----------------// */

    /* //----------------// -#########-----------------------------#########- //----------------// */
    /* //----------------// -#########- &|& KLASSE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########-----------------------------#########- //----------------// */

    /* //----------------// SECTIE: Constanten //----------------// */
    public static final String BEAN_DAO_NAME = "laadklantDao";
    private static final String LAADKLANT_ENTITY_NAME = Laadklant.ENTITY_NAME;

    /* //----------------// -#########------------------------#########- //----------------// */
    /* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
    /* //----------------// -#########------------------------#########- //----------------// */

    LaadklantHibernateDao() {
        configureAbstractOperations();
    }

    /* //----------------// -#########--------------------#########- //----------------// */
    /* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
    /* //----------------// -#########--------------------#########- //----------------// */

    /* //----------------\\ # ------------------ # //----------------\\ */
    /* //----------------\\ # Functies Technisch # //----------------\\ */
    /* //----------------\\ # ------------------ # //----------------\\ */
    private void configureAbstractOperations(){
        ENTITY_NAME = LAADKLANT_ENTITY_NAME;
    }

    /* //----------------\\ # --------------- # //----------------\\ */
    /* //----------------\\ # Functies Domein # //----------------\\ */
    /* //----------------\\ # --------------- # //----------------\\ */

    /* //----------------// SECTIE: Create //----------------// */
    /**
     * Voegt een instantie van deze klasse aan de DB toe als gepersisteerde entiteit.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Laadklant createItem(Laadklant item) {
        currentSession().save(item);
        return item;
    }

    /* //----------------// SECTIE: Read //----------------// */


    /* //----------------// SECTIE: Update //----------------// */
    /**
     * Updated de gepersisteerde entiteit achter een instantie.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public void updateItem(Laadklant item) {
        currentSession().update(item);
    }

    /* //----------------// SECTIE: Delete //----------------// */
    /**
     * Deleted de gepersisteerde entiteit achter een instantie.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Laadklant deleteItem(Laadklant item){
        return (Laadklant) currentSession().createQuery(String.format("delete from %s where %s = ", ENTITY_NAME, item.getId())).uniqueResult();
    }
}
