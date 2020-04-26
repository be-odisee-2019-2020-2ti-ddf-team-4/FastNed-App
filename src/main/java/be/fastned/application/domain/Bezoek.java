package be.fastned.application.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Bezoek")
@Table(name = "BEZOEKEN")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)

public class Bezoek {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private final long id;

	@OneToOne
	private final Probleem mogelijkProbleem;

	@ManyToOne
	private final Documentatie bijhorendeDoc;

	@Column
	@Size(min=1, max = 64, message="Houd het type tussen 1 en 64 tekens aub!")
	private final String type;

	@Column
	@Size(min=1, max = 510, message="Houd het eindverslag tussen 1 en 510 tekens aub!")
	private final String eindVerslag;

	@Column
	//@Temporal(TemporalType.DATE)
	private final LocalDateTime bezoekGestart, bezoekAfgerond;
}