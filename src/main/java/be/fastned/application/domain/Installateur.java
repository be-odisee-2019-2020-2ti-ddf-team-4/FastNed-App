package be.fastned.application.domain;

import lombok.*;

import javax.persistence.*;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Installateur")
@Table(name = "INSTALLATEURS")
@Data
@NoArgsConstructor(force=true)

@Value
@EqualsAndHashCode(callSuper = true)
public class Installateur extends Persoon {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

    public Installateur(long id, String emailadres, String naam, String voornaam, String geslacht, String gsm, String gebruikersnaam, String wachtwoord){
        super(id, emailadres, naam, voornaam, geslacht, gsm, gebruikersnaam, wachtwoord);
    }

//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	private final long id;
}