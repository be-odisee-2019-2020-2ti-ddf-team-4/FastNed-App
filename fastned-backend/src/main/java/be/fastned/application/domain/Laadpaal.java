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
	private final long id;

	@ManyToOne
	private final Locatiehouder locatiehouder;

	@OneToOne
	private final Documentatie installatieDocType;

	@OneToOne
	private final Documentatie reparatieDocType;

	@Column
	@Size(min=1, max = 64, message="Houd het type tussen 1 en 64 tekens aub!")
	private final String laadpaalType;

	@Column
	@Size(min=1, max = 255, message="Houd de status tussen 1 en 255 tekens aub!")
	private final String status;
}