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

public class Bezoek {

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	private final long id;

	private final Probleem mogelijkProbleem;

	private final Documentatie bijhorendeDoc;

	private final String type;

	private final String eindVerslag;

	private final Date bezoekGestart, bezoekAfgerond;
}