package be.fastned.application.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Bezoek")
@Table(name = "BEZOEKEN")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)

public class Bezoek {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	@OneToOne
	private Probleem mogelijkProbleem;

	@ManyToOne
	private Documentatie bijhorendeDoc;

	private String type, eindVerslag;

	private LocalDateTime bezoekGestart, bezoekAfgerond;
}