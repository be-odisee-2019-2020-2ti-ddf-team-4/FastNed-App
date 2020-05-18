package be.odisee.fastned.domain;


import javax.persistence.*;


/**
 * @author Team4
 * @version 6.0
 */

@Entity

public class Probleem {

    /* //----------------// -##########--------------------------------##########- //----------------// */
    /* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
    /* //----------------// -##########--------------------------------##########- //----------------// */

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String beschrijving;
    private String status;
    private String oplossing;

    public Probleem() {}

    public Probleem(String beschrijving, String status, String oplossing) {
        this.beschrijving = beschrijving;
        this.status = status;
        this.oplossing = oplossing;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getbeschrijving() {
        return beschrijving;
    }
    public void setbeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
    public String getstatus() {
        return status;
    }
    public void setstatus(String status) {
        this.status = status;
    }
    public String getoplossing() {
        return oplossing;
    }
    public void setoplossing(String oplossing) {
        this.oplossing = oplossing;
    }


}