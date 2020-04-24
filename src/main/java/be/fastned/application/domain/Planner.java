package be.fastned.application.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Table;

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

	Planner(long id, String emailadres, String naam, String voornaam, String geslacht, String gsm, String gebruikersnaam, String wachtwoord, User user){
		super(id, emailadres, naam, voornaam, geslacht, gsm, gebruikersnaam, wachtwoord, user);
	}

//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	private final long id;
}