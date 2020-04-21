package be.fastned.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Locatietoestemming")
@Table(name = "LOCATIETOESTEMMINGEN")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)

public class Locatietoestemming {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private final long id;

	private final byte aantalLaadpalen;

	private final String typeLaadpaal, status;
}