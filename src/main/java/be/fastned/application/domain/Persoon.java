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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private final long id;
    private final String emailadres;

    protected final String naam, voornaam, geslacht, gsm, gebruikersnaam, wachtwoord;
}
