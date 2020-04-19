package be.fastned.application.dao;

import be.fastned.application.dao.Base.BaseHibernateDao;
import be.fastned.application.dao.Interfaces.DocumentatieDocDao;
import be.fastned.application.domain.DocumentatieDoc;
import be.fastned.application.domain.Base.Entiteit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static be.fastned.application.dao.DocumentatieDocHibernateDao.BEAN_DAO_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Repository(BEAN_DAO_NAME)
@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)

public class DocumentatieDocHibernateDao extends BaseHibernateDao implements DocumentatieDocDao {

    /* //----------------// -#########--------------------------------#########- //----------------// */
    /* //----------------// -#########- &|& INSTANTIE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########--------------------------------#########- //----------------// */

    /* //----------------// -#########-----------------------------#########- //----------------// */
    /* //----------------// -#########- &|& KLASSE VARIABELEN &|& -#########- //----------------// */
    /* //----------------// -#########-----------------------------#########- //----------------// */

    /* //----------------// SECTIE: Constanten //----------------// */
    public static final String BEAN_DAO_NAME = "documentatieDocDao";
    private static final String DOCUMENTATIEDOC_ENTITY_NAME = DocumentatieDoc.ENTITY_NAME;

    /* //----------------// -#########------------------------#########- //----------------// */
    /* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
    /* //----------------// -#########------------------------#########- //----------------// */

    DocumentatieDocHibernateDao() {
        configureAbstractOperations();
    }

    /* //----------------// -#########--------------------#########- //----------------// */
    /* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
    /* //----------------// -#########--------------------#########- //----------------// */

    public ArrayList<DocumentatieDoc> getInstallatieDocumentaties(){
        ArrayList<DocumentatieDoc> list = new ArrayList<DocumentatieDoc>();
        for (Entiteit item : getAllItems()) {
            if (((DocumentatieDoc)item).getDocumentatieType()=="installatie"){
                list.add((DocumentatieDoc)item);
            }
        }
        return list;
    }

    public ArrayList<DocumentatieDoc> getReparatieDocumentaties(){
        ArrayList<DocumentatieDoc> list = new ArrayList<DocumentatieDoc>();
        for (Entiteit item : getAllItems()) {
            if (((DocumentatieDoc)item).getDocumentatieType()=="reparatie"){
                list.add((DocumentatieDoc)item);
            }
        }
        return list;
    }

    /* //----------------\\ # ------------------ # //----------------\\ */
    /* //----------------\\ # Functies Base # //----------------\\ */
    /* //----------------\\ # ------------------ # //----------------\\ */
    private void configureAbstractOperations(){
        ENTITY_NAME_BASE = DOCUMENTATIEDOC_ENTITY_NAME;
    }

    /* //----------------\\ # --------------- # //----------------\\ */
    /* //----------------\\ # Functies Domein # //----------------\\ */
    /* //----------------\\ # --------------- # //----------------\\ */

    /* //----------------// SECTIE: Create //----------------// */
    /**
     * Voegt een instantie van deze klasse aan de DB toe als gepersisteerde entiteit.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public DocumentatieDoc createItem(DocumentatieDoc item) {
        currentSession().save(item);
        return item;
    }

    /* //----------------// SECTIE: Read //----------------// */


    /* //----------------// SECTIE: Update //----------------// */
    /**
     * Updated de gepersisteerde entiteit achter een instantie.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public void updateItem(DocumentatieDoc item) {
        currentSession().update(item);
    }

    /* //----------------// SECTIE: Delete //----------------// */
    /**
     * Deleted de gepersisteerde entiteit achter een instantie.
     */
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public DocumentatieDoc deleteItem(DocumentatieDoc item){
        return (DocumentatieDoc) currentSession().createQuery(String.format("delete from %s where %s = ", ENTITY_NAME_BASE, item.getId())).uniqueResult();
    }
}
