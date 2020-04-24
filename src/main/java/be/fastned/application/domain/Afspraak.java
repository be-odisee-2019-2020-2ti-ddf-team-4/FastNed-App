package be.fastned.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Afspraak")
@Table(name = "AFSPRAKEN")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)

public class Afspraak {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private final long id;

	@ManyToOne
	private final Installateur installateur;

	@ManyToOne
	private final Laadpaal laadpaal;

	@OneToOne
	private final Contract contract;

	@OneToOne
	private final Bezoek bezoek;

	private final String status;
}