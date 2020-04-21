package be.fastned.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

/**
 * @author TiboVG
 * @version 6.0
 */

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name = "Persoon")
@Table(name = "PERSONEN")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)

public class Persoon {

    /* //----------------// -##########--------------------------------##########- //----------------// */
    /* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
    /* //----------------// -##########--------------------------------##########- //----------------// */

    @Id
    private String emailadres;

    protected String naam, voornaam, geslacht, gsm, gebruikersnaam, wachtwoord;
}
