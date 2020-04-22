package be.fastned.application.domain;

import lombok.AccessLevel;
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
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force=true)
@Data()

public class Persoon {

    /* //----------------// -##########--------------------------------##########- //----------------// */
    /* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
    /* //----------------// -##########--------------------------------##########- //----------------// */

    // TODO: (Tibo) getById uit CRUDRepository ondersteunt enkel long id's, geen string emails (zelf schrijven voor email)
    @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    //private final String emailadres;
    private final long id;

    private final String emailadres, naam, voornaam, geslacht, gsm, gebruikersnaam, wachtwoord;
}
