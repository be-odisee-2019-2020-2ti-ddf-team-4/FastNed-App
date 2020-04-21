package be.fastned.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Laadklant")
@Table(name = "LAADKLANTEN")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)

public class Laadklant {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private final long id;
}