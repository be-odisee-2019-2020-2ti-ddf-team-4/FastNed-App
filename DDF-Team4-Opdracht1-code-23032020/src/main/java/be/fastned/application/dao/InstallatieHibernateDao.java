package be.fastned.application.dao;

import be.fastned.application.dao.Base.BaseHibernateDao;
import be.fastned.application.dao.Interfaces.InstallatieDao;
import be.fastned.application.domain.Installatie;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static be.fastned.application.dao.InstallatieHibernateDao.BEAN_DAO_NAME;

@Repository(BEAN_DAO_NAME)
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)

public class InstallatieHibernateDao extends BaseHibernateDao implements InstallatieDao {

    /* //----------------// -#########--------------------------------#########- //----------------// */
    /* //----------------// -#########- &|& INSTANTIE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########--------------------------------#########- //----------------// */

    /* //----------------// -#########-----------------------------#########- //----------------// */
    /* //----------------// -#########- &|& KLASSE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########-----------------------------#########- //----------------// */

    /* //----------------// SECTIE: Constanten //----------------// */
    public static final String BEAN_DAO_NAME = "installatieDao";
    private static final String INSTALLATIE_ENTITY_NAME = Installatie.ENTITY_NAME;

    /* //----------------// -#########------------------------#########- //----------------// */
    /* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
    /* //----------------// -#########------------------------#########- //----------------// */

    InstallatieHibernateDao() {
        configureAbstractOperations();
    }

    /* //----------------// -#########--------------------#########- //----------------// */
    /* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
    /* //----------------// -#########--------------------#########- //----------------// */

    /* //----------------\\ # ------------------ # //----------------\\ */
    /* //----------------\\ # Functies Technisch # //----------------\\ */
    /* //----------------\\ # ------------------ # //----------------\\ */
    private void configureAbstractOperations(){
        ENTITY_NAME = INSTALLATIE_ENTITY_NAME;
    }

    /* //----------------\\ # --------------- # //----------------\\ */
    /* //----------------\\ # Functies Domein # //----------------\\ */
    /* //----------------\\ # --------------- # //----------------\\ */

    /* //----------------// SECTIE: Create //----------------// */
    /**
     * Voegt een instantie van deze klasse aan de DB toe als gepersisteerde entiteit.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Installatie createItem(Installatie item) {
        currentSession().save(item);
        return item;
    }

    /* //----------------// SECTIE: Read //----------------// */


    /* //----------------// SECTIE: Update //----------------// */
    /**
     * Updated de gepersisteerde entiteit achter een instantie.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public void updateItem(Installatie item) {
        currentSession().update(item);
    }

    /* //----------------// SECTIE: Delete //----------------// */
    /**
     * Deleted de gepersisteerde entiteit achter een instantie.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Installatie deleteItem(Installatie item){
        return (Installatie) currentSession().createQuery(String.format("delete from %s where %s = ", ENTITY_NAME, item.getId())).uniqueResult();
    }
}