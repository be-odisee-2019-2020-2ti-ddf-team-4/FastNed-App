package be.fastned.application.domain.Technisch;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashMap;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */
@Component("documentatieRepositoryInst")
public class DocumentatieRepository {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long m_Id;
    @Transient
    private static DocumentatieRepository uniqueInstance;

    public DocumentatieRepository(){

    }
    /*private DocumentatieRepository(){
    }*/
    public static DocumentatieRepository getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new DocumentatieRepository();
        }
        return uniqueInstance;
    }

    /* //----------------// SECTIE: Installatie Doc //----------------// */
    @Column
    public String TypeA_InstallatieDoc = "...";
    @Column
    public String TypeB_InstallatieDoc = "...";
    @Column
    public String TypeC_InstallatieDoc = "...";
    @Column
    public String TypeD_InstallatieDoc = "...";

    /* //----------------// SECTIE: Reparatie Doc //----------------// */
    @Column
    public String TypeA_ReparatieDoc = "...";
    @Column
    public String TypeB_ReparatieDoc = "...";
    @Column
    public String TypeC_ReparatieDoc = "...";
    @Column
    public String TypeD_ReparatieDoc = "...";

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    public HashMap<String, String> laadpaalHashMapInst = new HashMap<String, String>() {{
        put("A", TypeA_InstallatieDoc);
        put("B", TypeB_InstallatieDoc);
        put("C", TypeC_InstallatieDoc);
        put("D", TypeD_InstallatieDoc);}};

    public HashMap<String, String> laadpaalHashMapRep = new HashMap<String, String>() {{
        put("A", TypeA_ReparatieDoc);
        put("B", TypeB_ReparatieDoc);
        put("C", TypeC_ReparatieDoc);
        put("D", TypeD_ReparatieDoc);}};
}
