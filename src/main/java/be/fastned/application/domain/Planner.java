package be.fastned.application.domain;

import lombok.*;

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

	public Planner(long id, String emailadres, String naam, String voornaam, String geslacht, String gsm,
				   String gebruikersnaam, String wachtwoord, String rol, String beschrijving, String status){
		super(id, emailadres, naam, voornaam, geslacht, gsm, gebruikersnaam, wachtwoord, rol, beschrijving, status);
		this.test = naam;
	}

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private final String test;
}