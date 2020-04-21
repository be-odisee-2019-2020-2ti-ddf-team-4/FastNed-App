package be.fastned.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Locatiehouder")
@Table(name = "LOCATIEHOUDERS")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)

public class Locatiehouder {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private final String email;
	private final String voornaam;
	private final String naam;
	private final String geslacht;
	private final String gsmnummer;
	private final String username;
	private final String wachtwoord;
	private final long id;
}