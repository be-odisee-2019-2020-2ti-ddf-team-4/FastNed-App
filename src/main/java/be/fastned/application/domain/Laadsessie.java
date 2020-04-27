package be.fastned.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Laadessie")
@Table(name = "LAADSESSIES")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)

public class Laadsessie {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private final long id;

	@ManyToOne
	private final Laadpaal laadpaal;

	@Column
	@Temporal(TemporalType.DATE)
	private final Date startSessie;

	@Column
	private final double startPercentage;
}