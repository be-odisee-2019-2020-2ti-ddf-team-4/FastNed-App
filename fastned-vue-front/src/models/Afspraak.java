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

public class Afspraak {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	private final long id;

	private final String type;

	private final Installateur installateur;

	private final Laadpaal laadpaal;

	private final Contract contract;

	private final Bezoek bezoek;

	private final String  status;


}