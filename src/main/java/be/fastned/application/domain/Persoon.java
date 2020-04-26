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

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name = "Persoon")
@Table(name = "PERSONEN")
@NoArgsConstructor(access = AccessLevel.PUBLIC, force=true)
@Data()

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

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private final long id;

    @Column(unique = true)
    @Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$",message="Vul een geldig e-mail adres in ")
    private final String emailadres;

    @Column
    @Size(min=1, max = 255, message="Houd de naam tussen 1 en 255 tekens aub!")
    private final String naam;

    @Column
    @Size(min=1, max = 255, message="Houd de voornaam tussen 1 en 255 tekens aub!")
    private final String voornaam;

    @Column
    @Size(min=1, max = 255, message="Houd het geslacht tussen 1 en 255 tekens aub!")
    private final String geslacht;

    @Column
    @Size(min=1, max = 255, message="Houd de GSM-nummer tussen 1 en 255 tekens aub!")
    private final String gsm;


    @OneToOne(cascade=CascadeType.ALL)
    private final User user;
}
