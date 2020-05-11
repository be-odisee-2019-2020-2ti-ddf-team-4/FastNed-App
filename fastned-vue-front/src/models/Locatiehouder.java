package be.fastned.application.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author TiboVG
 * @version 6.0
 */

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

	private final String bedrijfsnaam;

	private final String btwNummer;

	private final String adres;
}