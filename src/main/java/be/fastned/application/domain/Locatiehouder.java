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

public final class Locatiehouder extends Persoon{

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private final long id;

	private final String bedrijfsnaam, btwNummer, adres;
}