package be.odisee.fastned.domain;


import javax.persistence.*;


/**
 * @author Team4
 * @version 6.0
 */

@Entity

public class Documentatie {

    /* //----------------// -##########--------------------------------##########- //----------------// */
    /* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
    /* //----------------// -##########--------------------------------##########- //----------------// */

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String laadpaalType;
    private String documentatieType;
    private String documentatieBeschr;

    public Documentatie() {}

    public Documentatie(String laadpaalType, String documentatieType, String documentatieBeschr) {
        this.laadpaalType = laadpaalType;
        this.documentatieType = documentatieType;
        this.documentatieBeschr = documentatieBeschr;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLaadpaalType() {
        return laadpaalType;
    }
    public void setLaadpaalType(String laadpaalType) {
        this.laadpaalType = laadpaalType;
    }
    public String getDocumentatieType() {
        return documentatieType;
    }
    public void setDocumentatieType(String documentatieType) {
        this.documentatieType = documentatieType;
    }
    public String getDocumentatieBeschr() {
        return documentatieBeschr;
    }
    public void setDocumentatieBeschr(String documentatieBeschr) {
        this.documentatieBeschr = documentatieBeschr;
    }


}