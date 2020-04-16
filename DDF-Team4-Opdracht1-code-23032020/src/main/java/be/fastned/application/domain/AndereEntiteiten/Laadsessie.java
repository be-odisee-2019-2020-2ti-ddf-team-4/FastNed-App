package be.fastned.application.domain.AndereEntiteiten;

import be.fastned.application.dao.AfspraakHibernateDao;
import be.fastned.application.dao.Base.BaseDao;
import be.fastned.application.domain.Base.Entiteit;
import be.fastned.application.domain.Base.EntiteitBaseImpl;
import be.fastned.application.service.AppRunner;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static be.fastned.application.domain.AndereEntiteiten.Laadsessie.ENTITY_NAME;
import static be.fastned.application.domain.AndereEntiteiten.Laadsessie.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Laadsessie extends EntiteitBaseImpl implements Entiteit {

	/* //----------------// -##########-----------------------------##########- //----------------// */
	/* //----------------// -##########- | ! VERDUIDELIJKINGEN ! | -##########- //----------------// */
	/* //----------------// -##########-----------------------------##########- //----------------// */
	/*
		Verwijzing: Vragen omtrent actieve en gearchiveerde collecties -> (HELP02)
		Verwijzing: Vragen omtrent Hoofdletter-genaamde variabelen -> (HELP03)
	*/

	/* //----------------// -##########--------------------------------##########- //----------------// */
	/* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########--------------------------------##########- //----------------// */

	/* //----------------\\ # ------------------------------- # //----------------\\ */
	/* //----------------\\ # Instantie Domein Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------------- # //----------------\\ */
	private String id;
	private LocalDateTime startSessie;
	private double startPercentage;
	private Laadpaal laadpaal;

	/* //----------------\\ # ------------------------------- # //----------------\\ */
	/* //----------------\\ # Instantie Technische Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------------- # //----------------\\ */


	/* //----------------// -##########-----------------------------##########- //----------------// */
	/* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########-----------------------------##########- //----------------// */

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Laadsessie";
	public static final String TABLE_NAME = "tbl_laadsessies";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = LAADSESSIE_ID_PREFIX;

	// Constanten met kolom-namen
	public static final String ID_COL_NAME = ID_PREFIX + "ID";
	public static final String LAADPAAL_COL_NAME = "Laadpaal_FK";
	public static final String STARTPERCENTAGE_COL_NAME = "StartPercentage";
	public static final String STARTSESSIE_COL_NAME = "StartSessie";

	private static final int LAADPERCENT_PER_MINUUT = 1;

	/* //----------------// SECTIE: Laadsessies //----------------// */
	/**
	 * (ACT-LAADSESSIES) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<Laadsessie> actieveLaadsessies = new ArrayList<Laadsessie>();
	/**
	 * (ARCH-LAADSESSIES) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<Laadsessie> gearchiveerdeLaadsessies = new ArrayList<Laadsessie>();

	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */

	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Laadsessie(){
		setupInitConfig();
		id = extrapolateId();
	}

	/**
	 * Default constructor voor deze klasse. (Optionele Configuratie)
	 */
	public Laadsessie(boolean noConfig){
		if (!noConfig) { setupInitConfig(); }
		id = extrapolateId();
	}

	/**
	 * Volledige Constructor voor deze klasse.
	 */
	public Laadsessie(LocalDateTime startSessie, double startPercentage, Laadpaal laadpaal){
		this.startSessie = startSessie;
		this.startPercentage = startPercentage;
		this.laadpaal = laadpaal;
		setupInitConfig();
		id = extrapolateId();
	}

	/* //----------------// -#########--------------------#########- //----------------// */
	/* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
	/* //----------------// -#########--------------------#########- //----------------// */

	/* //----------------\\ # ------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Domein # //----------------\\ */
	/* //----------------\\ # ------------------------- # //----------------\\ */

	public double berekenLaadtijd(){
		return ((100 - this.startPercentage)/this.LAADPERCENT_PER_MINUUT);
	}

	/* //----------------\\ # ---------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Technisch # //----------------\\ */
	/* //----------------\\ # ---------------------------- # //----------------\\ */

	/**
	 * Deze technische functie zet deze instantie over van de actieve- naar de gearchiveerde arraylist.
	 */
	public void archiveer(){
		gearchiveerdeLaadsessies.add(this);
		actieveLaadsessies.remove(this);
	}

	/**
	 * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
	 */
	private void setupInitConfig(){
		actieveLaadsessies.add(this);
		klasseDao = (BaseDao) AppRunner.getAppContext().getBean(AfspraakHibernateDao.BEAN_DAO_NAME);
	}

	/**
	 * Deze technische functie leidt het id af via het laatste record in de tabel.
	 */
	private String extrapolateId(){
		return baseExtrapolateId(ID_PREFIX, klasseDao);
	}

	/* //----------------// -#########- |------------| -#########- //----------------// */
	/* //----------------// -#########- | PROPERTIES | -#########- //----------------// */
	/* //----------------// -#########- |------------| -#########- //----------------// */

	/* //----------------\\ # ------------------------- # //----------------\\ */
	/* //----------------\\ # Property Domein # //----------------\\ */
	/* //----------------\\ # ------------------------- # //----------------\\ */

	/* //----------------// PROPERTY: ID //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het id-attribuut van deze instantie.
	 */
	@Id @Column(name = ID_COL_NAME)
	public String getId(){
		return this.id;
	}
	/**
	 * Deze domein-attribuut-setter vertegenwoordigt het id-attribuut van deze instantie.
	 */
	@Transient
	public void setId(String value){
		this.id = value;
	}

	/* //----------------// PROPERTY: Start-Sessie //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het startsessie-attribuut van deze instantie.
	 */
	@Column(name = STARTSESSIE_COL_NAME)
	public LocalDateTime getStartSessie(){
		return this.startSessie;
	}
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het startsessie-attribuut van deze instantie.
	 */
	@Transient
	public void setStartSessie(LocalDateTime value){
		this.startSessie = value;
	}

	/* //----------------// PROPERTY: Start-Percentage //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het startpercentage-attribuut van deze instantie.
	 */
	@Column(name = STARTPERCENTAGE_COL_NAME)
	public double getStartPercentage(){
		return this.startPercentage;
	}
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het startpercentage-attribuut van deze instantie.
	 */
	@Transient
	public void setStartPercentage(double value){
		this.startPercentage = value;
	}

	/* //----------------// PROPERTY: Laadpaal //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het laadpaal-attribuut van deze instantie.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name=LAADPAAL_COL_NAME, referencedColumnName = Laadpaal.ID_COL_NAME)
	public Laadpaal getLaadpaal(){
		return this.laadpaal;
	}
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het laadpaal-attribuut van deze instantie.
	 */
	@Transient
	public void setLaadpaal(Laadpaal value){
		this.laadpaal = value;
	}
}