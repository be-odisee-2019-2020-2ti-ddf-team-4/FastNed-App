package be.fastned.application.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author TiboVG
 * @version 6.0
 */
public class Laadklant extends Persoon {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	Laadklant(long id, String emailadres, String naam, String voornaam, String geslacht, String gsm,
			  User user){
		super(id, emailadres, naam, voornaam, geslacht, gsm, user);
	}
}