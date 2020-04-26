package be.fastned.application.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Laadklant")
@Table(name = "LAADKLANTEN")
@Data
@NoArgsConstructor(force=true)

@Value
@EqualsAndHashCode(callSuper = true)
public class Laadklant extends Persoon {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	Laadklant(long id, String emailadres, String naam, String voornaam, String geslacht, String gsm,
			  User user){
		super(id, emailadres, naam, voornaam, geslacht, gsm, user);
	}

//	@Transient
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	private final String test;
}