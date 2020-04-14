package be.fastned.application.domain.Technisch;

import be.fastned.application.dao.Base.BaseDao;
import be.fastned.application.dao.DocumentatieDocHibernateDao;
import be.fastned.application.domain.Base.AbsoluteBase;
import be.fastned.application.service.AppRunner;
import javax.persistence.*;
import java.util.ArrayList;
import static be.fastned.application.domain.Technisch.DocumentatieDoc.ENTITY_NAME;
import static be.fastned.application.domain.Technisch.DocumentatieDoc.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class DocumentatieDoc extends AbsoluteBase {

    /* //----------------// -##########-----------------------------##########- //----------------// */
    /* //----------------// -##########- | ! VERDUIDELIJKINGEN ! | -##########- //----------------// */
    /* //----------------// -##########-----------------------------##########- //----------------// */
	/*
		Verwijzing: Vragen omtrent actieve en gearchiveerde collecties -> (HELP02)
		Verwijzing: Vragen omtrent Hoofdletter-genaamde variabelen -> (HELP03)
	*/

    /* //----------------// -##########--------------------------------##########- //----------------// */
    /* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
    /* //----------------// -##########--------------------------------##########- //----------------// */

    /* //----------------\\ # ------------------------------- # //----------------\\ */
    /* //----------------\\ # Instantie Domein Variabelen # //----------------\\ */
    /* //----------------\\ # ------------------------------- # //----------------\\ */
    private String id;
    private String laadaalType;
    private String documentatieType;
    private String documentatie;

    /* //----------------\\ # ------------------------------- # //----------------\\ */
    /* //----------------\\ # Instantie Technische Variabelen # //----------------\\ */
    /* //----------------\\ # ------------------------------- # //----------------\\ */
    private ArrayList<DocumentatieDoc> installatieDocumentaties;
    private ArrayList<DocumentatieDoc> reparatieDocumentaties;

    /* //----------------// -##########-----------------------------##########- //----------------// */
    /* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
    /* //----------------// -##########-----------------------------##########- //----------------// */

    /* //----------------// SECTIE: Constanten //----------------// */
    // Configureren @Table en @Entity
    public static final String ENTITY_NAME = "DocumentatieDoc";
    public static final String TABLE_NAME = "tbl_documentatieDocs";

    // Lokale constante (id prefix) overkopieëren naar super-variabel
    public static final String ID_PREFIX = DOCUMENTATIEDOC_ID_PREFIX;

    // Constanten met kolom-namen
    public static final String ID_COL_NAME = ID_PREFIX + "ID";
    public static final String LAADPAALTYPE_COL_NAME = "LaadpaalType";
    public static final String DOCUMENTATIETYPE_COL_NAME = "DocumentatieType";
    public static final String DOCUMENTATIE_COL_NAME = "Documentatie";

    /* //----------------// -#########------------------------#########- //----------------// */
    /* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
    /* //----------------// -#########------------------------#########- //----------------// */

    /**
     * Default constructor voor deze klasse.
     */
    public DocumentatieDoc() {}

    /**
     * Volledige constructor voor deze klasse.
     */
    public DocumentatieDoc(String laadpaalType, String documentatieType, String documentatie){
        setupInitConfig();
        this.laadaalType = laadpaalType;
        this.documentatieType = documentatieType;
        this.documentatie = documentatie;
    }

    /* //----------------// -#########--------------------#########- //----------------// */
    /* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
    /* //----------------// -#########--------------------#########- //----------------// */

    /* //----------------\\ # -------------- # //----------------\\ */
    /* //----------------\\ # Functie Domein # //----------------\\ */
    /* //----------------\\ # -------------- # //----------------\\ */

    /**
     * Deze functie zoekt een installatieDoc op basis van het laadpaaltype.
     */
    public DocumentatieDoc findInstallatieDoc(String laadpaalType){
        for (DocumentatieDoc item : installatieDocumentaties) {
            if (item.getLaadpaalType() == laadpaalType){
                return item;
            }
        }
        return null;
    }

    /**
     * Deze functie zoekt een reparatieDoc op basis van het laadpaaltype.
     */
    public DocumentatieDoc findReparatieDoc(String laadpaalType){
        for (DocumentatieDoc item : installatieDocumentaties) {
            if (item.getLaadpaalType() == laadpaalType){
                return item;
            }
        }
        return null;
    }

    /* //----------------\\ # ----------------- # //----------------\\ */
    /* //----------------\\ # Functie Technisch # //----------------\\ */
    /* //----------------\\ # ----------------- # //----------------\\ */

    /**
     * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
     */
    private void setupInitConfig(){
        klasseDao = (BaseDao) AppRunner.getAppContext().getBean(DocumentatieDocHibernateDao.BEAN_DAO_NAME);
        reparatieDocumentaties = ((DocumentatieDocHibernateDao)klasseDao).getReparatieDocumentaties();
        installatieDocumentaties = ((DocumentatieDocHibernateDao)klasseDao).getInstallatieDocumentaties();
    }

    /**
     * Deze technische functie leidt het id af via het laatste record in de tabel.
     */
    private String extrapolateId(){
        return baseExtrapolateId(ID_PREFIX, klasseDao);
    }

    /* //----------------// -#########- |------------| -#########- //----------------// */
    /* //----------------// -#########- | PROPERTIES | -#########- //----------------// */
    /* //----------------// -#########- |------------| -#########- //----------------// */

    /* //----------------\\ # ------------------------- # //----------------\\ */
    /* //----------------\\ # Property Domein Variabelen # //----------------\\ */
    /* //----------------\\ # ------------------------- # //----------------\\ */

    /* //----------------// PROPERTY: ID //----------------// */
    /**
     * Deze domein-attribuut-getter vertegenwoordigt het id-attribuut van deze instantie.
     */
    @Id @Column(name = ID_COL_NAME)
    public String getId(){
        return this.id;
    }
    /**
     * Deze domein-attribuut-setter vertegenwoordigt het id-attribuut van deze instantie.
     */
    @Transient
    public void setId(String value){
        this.id = value;
    }

    /* //----------------// PROPERTY: LaadpaalType //----------------// */
    /**
     * Deze domein-attribuut-getter vertegenwoordigt het LaadpaalType-attribuut van deze instantie.
     */
    @Column(name = LAADPAALTYPE_COL_NAME)
    public String getLaadpaalType(){
        return this.laadaalType;
    }
    /**
     * Deze domein-attribuut-setter vertegenwoordigt het LaadpaalType-attribuut van deze instantie.
     */
    @Transient
    public void setLaadpaalType(String value){
        this.laadaalType = value;
    }

    /* //----------------// PROPERTY: DocumentatieType //----------------// */
    /**
     * Deze domein-attribuut-getter vertegenwoordigt het documentatieDoc-attribuut van deze instantie.
     */
    @Column(name = DOCUMENTATIETYPE_COL_NAME)
    public String getDocumentatieType(){
        return this.documentatieType;
    }
    /**
     * Deze domein-attribuut-setter vertegenwoordigt het documentatieDoc-attribuut van deze instantie.
     */
    @Transient
    public void setDocumentatieType(String value){
        this.documentatieType = value;
    }

    /* //----------------// PROPERTY: Documentatie //----------------// */
    /**
     * Deze domein-attribuut-getter vertegenwoordigt het documentatie-attribuut van deze instantie.
     */
    @Column(name = DOCUMENTATIE_COL_NAME)
    public String getDocumentatie(){
        return this.documentatie;
    }
    /**
     * Deze domein-attribuut-setter vertegenwoordigt het documentatie-attribuut van deze instantie.
     */
    @Transient
    public void setDocumentatie(String value){
        this.documentatie = value;
    }

    /* //----------------// PROPERTY: ReparatiesDocumentaties //----------------// */
    /**
     * Deze domein-attribuut-getter vertegenwoordigt het ReparatiesDocumentaties-attribuut van deze instantie.
     */
    @Transient
    public ArrayList<DocumentatieDoc> getReparatieDocumentaties(){
        return this.installatieDocumentaties;
    }

    /* //----------------// PROPERTY: InstallatiesDocumentaties //----------------// */
    /**
     * Deze domein-attribuut-getter vertegenwoordigt het InstallatiesDocumentaties-attribuut van deze instantie.
     */
    @Transient
    public ArrayList<DocumentatieDoc> getInstallatieDocumentaties(){
        return this.reparatieDocumentaties;
    }
}