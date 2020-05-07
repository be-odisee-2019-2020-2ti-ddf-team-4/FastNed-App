package be.fastned.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Size;

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

	@Column
	private final byte aantalLaadpalen;

	@Column
	@Size(min=1, max = 64, message="Houd het laadpaal-type tussen 1 en 64 tekens aub!")
	private final String typeLaadpaal;

	@Column
	@Size(min=1, max = 255, message="Houd de status tussen 1 en 255 tekens aub!")
	private final String status;
}