package be.fastned.application.domain;

import lombok.*;

import javax.persistence.*;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Locatiehouder")
@Table(name = "LOCATIEHOUDERS")
@Data
@NoArgsConstructor(force=true)

@Value
@EqualsAndHashCode(callSuper = true)
public class Locatiehouder extends Persoon{

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	public Locatiehouder(long id, String emailadres, String naam, String voornaam, String geslacht, String gsm, String gebruikersnaam, String wachtwoord, String bedrijfsnaam, String btwNummer, String adres){
		super(id, emailadres, naam, voornaam, geslacht, gsm, gebruikersnaam, wachtwoord);
		this.bedrijfsnaam = bedrijfsnaam;
		this.btwNummer = btwNummer;
		this.adres = adres;
	}

//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	private final long id;

	private final String bedrijfsnaam, btwNummer, adres;
}