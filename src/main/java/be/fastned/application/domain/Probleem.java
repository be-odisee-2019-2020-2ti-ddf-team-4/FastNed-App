package be.fastned.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

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

	private final String beschrijving, status;

	@OneToOne
	private final Oplossing oplossing;
}