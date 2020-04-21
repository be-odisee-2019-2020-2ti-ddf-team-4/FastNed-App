package be.fastned.application.domain;

import lombok.*;

import javax.persistence.*;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Planner")
@Table(name = "PLANNERS")
@Data
@NoArgsConstructor(force=true)

@Value
@EqualsAndHashCode(callSuper = true)
public class Planner extends Persoon {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	Planner(String emailadres, String naam, String voornaam, String geslacht, String gsm, String gebruikersnaam, String wachtwoord){
		super(emailadres, naam, voornaam, geslacht, gsm, gebruikersnaam, wachtwoord);
	}

//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	private final long id;
}