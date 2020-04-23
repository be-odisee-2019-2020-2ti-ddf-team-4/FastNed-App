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

@Entity(name = "Contract")
@Table(name = "CONTRACTEN")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)
<<<<<<< HEAD

=======
>>>>>>> master

public class Contract {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private final long id;
<<<<<<< HEAD


	private final LocalDateTime contractDatum, uitvoeringsDatum;



=======

	private final LocalDateTime contractDatum, uitvoeringsDatum;
>>>>>>> master
}
