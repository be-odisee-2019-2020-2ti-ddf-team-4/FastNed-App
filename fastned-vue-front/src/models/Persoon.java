package be.fastned.application.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author TiboVG
 * @version 6.0
 */

public class Persoon {

    /* //----------------// -##########--------------------------------##########- //----------------// */
    /* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
    /* //----------------// -##########--------------------------------##########- //----------------// */

    protected Persoon(long id, String emailadres, String naam, String voornaam, String geslacht, String gsm,
                      User user){
        this.id = id;
        this.emailadres = emailadres;
        this.naam = naam;
        this.voornaam = voornaam;
        this.geslacht = geslacht;
        this.gsm = gsm;
        this.user = user;
    }

    private final long id;

    private final String emailadres;

    private final String naam;

    private final String voornaam;

    private final String geslacht;

    private final String gsm;

    private final User user;
}
