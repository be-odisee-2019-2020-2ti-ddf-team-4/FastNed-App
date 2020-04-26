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

@Entity(name = "Oplossing")
@Table(name = "OPLOSSINGEN")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)

public class Oplossing {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private final long id;

	@Column
	@Size(min=1, max = 510, message="Houd de oplossing beschrijving tussen 1 en 510 tekens aub!")
	private final String oplossing;
}