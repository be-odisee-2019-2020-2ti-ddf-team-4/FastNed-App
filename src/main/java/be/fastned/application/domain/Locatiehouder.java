package be.fastned.application.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Locatiehouder")
@Table(name = "LOCATIEHOUDERS")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC,force=true)

@Value
@EqualsAndHashCode(callSuper = true)
public class Locatiehouder extends Persoon {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	public Locatiehouder(long id, String emailadres, String naam, String voornaam, String geslacht, String gsm, String bedrijfsnaam, String btwNummer, String adres,
				   User user){
		super(id, emailadres, naam, voornaam, geslacht, gsm, user);
		this.bedrijfsnaam = bedrijfsnaam;
		this.btwNummer = btwNummer;
		this.adres = adres;
	}

//	@Transient
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	private final String test;

	@Column
	@Size(min=1, max = 64, message="Houd de bedrijfsnaam tussen 1 en 64 tekens aub!")
	private final String bedrijfsnaam;

	@Column
	@Size(min=1, max = 64, message="Houd het BTW-nummer tussen 1 en 64 tekens aub!")
	private final String btwNummer;

	@Column
	@Size(min=1, max = 64, message="Houd het adres tussen 1 en 64 tekens aub!")
	private final String adres;
}