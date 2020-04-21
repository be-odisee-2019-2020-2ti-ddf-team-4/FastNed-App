package be.fastned.application.domain;

import lombok.AccessLevel;
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
@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)

public class Afspraak {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private Installateur installateur;

	@ManyToOne
	private Laadpaal laadpaal;

	@OneToOne
	private Contract contract;

	@OneToOne
	private Bezoek bezoek;

	private String  status;
}