package be.fastned.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Laadpaal")
@Table(name = "LAADPALEN")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)

public class Laadpaal {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private final Locatiehouder locatiehouder;

	@OneToOne
	private final Documentatie installatieDocType, reparatieDocType;

	private final String laadpaalType, status;
}