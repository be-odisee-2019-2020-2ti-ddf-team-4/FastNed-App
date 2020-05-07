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

public class Laadsessie {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	private final long id;

	private final Laadpaal laadpaal;

	private final Date startSessie;

	private final double startPercentage;
}