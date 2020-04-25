package be.fastned.application.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Size;

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

	@Column
	@Size(min=1, max = 255, message="Houd de status tussen 1 en 255 tekens aub!")
	private final String  status;
}