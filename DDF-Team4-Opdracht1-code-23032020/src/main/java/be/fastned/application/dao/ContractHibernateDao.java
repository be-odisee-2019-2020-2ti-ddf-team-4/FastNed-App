package be.fastned.application.dao;

import be.fastned.application.dao.Interfaces.ContractDao;
import be.fastned.application.domain.Contract;
import be.fastned.application.service.AppConfig;
import be.fastned.application.service.AppRunner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static be.fastned.application.dao.ContractHibernateDao.BEAN_DAO_NAME;

@Repository(BEAN_DAO_NAME)
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)

public class ContractHibernateDao extends BaseHibernateDao implements ContractDao {

    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// -#####-------------------------#####- //----------------// */
    /* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####-------------------------#####- //----------------// */

    /* //----------------// SECTIE: Constanten //----------------// */
    public static final String BEAN_DAO_NAME = "contractDao";
    private static final String CONTRACT_ENTITY_NAME = Contract.ENTITY_NAME;

    /* //----------------// -#####--------------------#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####--------------------#####- //----------------// */
    ContractHibernateDao() {
        configureAbstractOperations();
    }

    /* //----------------// -#####----------------#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####----------------#####- //----------------// */

    /* //----------------\\ <||> ------------------ <||> //----------------\\ */
    /* //----------------\\ <||> FUNCTIES Technisch <||> //----------------\\ */
    /* //----------------\\ <||> ------------------ <||> //----------------\\ */
    private void configureAbstractOperations(){
        ENTITY_NAME = CONTRACT_ENTITY_NAME;
    }

    /* //----------------\\ <||> --------------- <||> //----------------\\ */
    /* //----------------\\ <||> FUNCTIES Domein <||> //----------------\\ */
    /* //----------------\\ <||> --------------- <||> //----------------\\ */

    /* //----------------// SECTIE: Create //----------------// */
    /**
     * Voegt een instantie van deze klasse aan de DB toe als gepersisteerde entiteit.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Contract addItem(Contract item) {
        currentSession().save(item);
        return item;
    }

    /* //----------------// SECTIE: Read //----------------// */


    /* //----------------// SECTIE: Update //----------------// */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public void updateItem(Contract item) {
        currentSession().update(item);
    }

    /* //----------------// SECTIE: Delete //----------------// */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public Contract deleteItem(Contract item){
        return (Contract) currentSession().createQuery(String.format("delete from %s where %s = ", ENTITY_NAME, item.getId())).uniqueResult();
    }
}
