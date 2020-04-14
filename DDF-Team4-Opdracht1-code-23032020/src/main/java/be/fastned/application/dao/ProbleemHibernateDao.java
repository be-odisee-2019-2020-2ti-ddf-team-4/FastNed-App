package be.fastned.application.dao;

import be.fastned.application.dao.Interfaces.ProbleemDao;
import be.fastned.application.domain.Probleem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static be.fastned.application.dao.ProbleemHibernateDao.BEAN_DAO_NAME;

@Repository(BEAN_DAO_NAME)
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
public class ProbleemHibernateDao extends BaseHibernateDao implements ProbleemDao {

    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// -#####-------------------------#####- //----------------// */
    /* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####-------------------------#####- //----------------// */

    /* //----------------// SECTIE: Constanten //----------------// */
    public static final String BEAN_DAO_NAME = "probleemDao";
    private static final String PROBLEEM_ENTITY_NAME = Probleem.ENTITY_NAME;

    /* //----------------// -#####--------------------#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####--------------------#####- //----------------// */
    ProbleemHibernateDao() {
        configureAbstractOperations();
    }

    /* //----------------// -#####----------------#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####----------------#####- //----------------// */

    /* //----------------\\ <||> ------------------ <||> //----------------\\ */
    /* //----------------\\ <||> FUNCTIES Technisch <||> //----------------\\ */
    /* //----------------\\ <||> ------------------ <||> //----------------\\ */
    private void configureAbstractOperations(){
        ENTITY_NAME = PROBLEEM_ENTITY_NAME;
    }

    /* //----------------\\ <||> --------------- <||> //----------------\\ */
    /* //----------------\\ <||> FUNCTIES Domein <||> //----------------\\ */
    /* //----------------\\ <||> --------------- <||> //----------------\\ */

    /* //----------------// SECTIE: Create //----------------// */
    /**
     * Voegt een instantie van deze klasse aan de DB toe als gepersisteerde entiteit.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Probleem addItem(Probleem item) {
        currentSession().save(item);
        return item;
    }

    /* //----------------// SECTIE: Read //----------------// */


    /* //----------------// SECTIE: Update //----------------// */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public void updateItem(Probleem item) {
        currentSession().update(item);
    }

    /* //----------------// SECTIE: Delete //----------------// */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Probleem deleteItem(Probleem item){
        return (Probleem) currentSession().createQuery(String.format("delete from %s where %s = ", ENTITY_NAME, item.getId())).uniqueResult();
    }
}