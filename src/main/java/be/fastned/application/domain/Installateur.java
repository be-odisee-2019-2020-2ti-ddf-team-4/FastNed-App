package be.fastned.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Installateur")
@Table(name = "INSTALLATEURS")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)

public class Installateur {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */
	private final long email;

	private final	long naam;

	private final long voornaam;


	private final long geslacht;


	private final 	long gsm;

	private final long username;

	private final long password;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private final long id;
}