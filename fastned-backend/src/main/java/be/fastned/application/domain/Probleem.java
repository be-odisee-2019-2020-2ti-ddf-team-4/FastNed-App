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

@Entity(name = "Probleem")
@Table(name = "PROBLEMEN")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)

public class Probleem {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private final long id;

	@Column
	@Size(min=1, max = 510, message="Houd de GSM-nummer tussen 1 en 510 tekens aub!")
	private final String beschrijving;

	@Column
	@Size(min=1, max = 255, message="Houd de GSM-nummer tussen 1 en 255 tekens aub!")
	private final String status;

	@OneToOne
	private final Oplossing oplossing;
}