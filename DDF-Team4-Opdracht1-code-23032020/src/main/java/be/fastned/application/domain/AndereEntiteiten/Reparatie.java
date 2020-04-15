package be.fastned.application.domain.AndereEntiteiten;

import be.fastned.application.dao.AfspraakHibernateDao;
import be.fastned.application.dao.Base.BaseDao;
import be.fastned.application.domain.Base.EntiteitBaseImpl;
import be.fastned.application.domain.Technisch.Bezoek;
import be.fastned.application.domain.Technisch.DocumentatieDoc;
import be.fastned.application.service.AppRunner;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static be.fastned.application.domain.AndereEntiteiten.Reparatie.ENTITY_NAME;
import static be.fastned.application.domain.AndereEntiteiten.Reparatie.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Reparatie extends EntiteitBaseImpl implements Bezoek {

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
	private LocalDateTime reparatieCompleet;
	public Probleem probleem;

	/* //----------------\\ # ------------------------------- # //----------------\\ */
	/* //----------------\\ # Instantie Technische Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------------- # //----------------\\ */


	/* //----------------// -##########-----------------------------##########- //----------------// */
	/* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########-----------------------------##########- //----------------// */

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Reparatie";
	public static final String TABLE_NAME = "tbl_reparaties";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = REPARATIE_ID_PREFIX;

	// Constanten met kolom-namen
	public static final String ID_COL_NAME = ID_PREFIX + "ID";
	public static final String REPARATIECOMPLEET_COL_NAME = "ReparatieCompleet";
	public static final String PROBLEEM_COL_NAME = "Probleem";

	// Technische constanten
	public static DocumentatieDoc repo = new DocumentatieDoc();
	private static ArrayList<DocumentatieDoc> reparatieDocs = repo.getReparatieDocumentaties();

	/* //----------------// SECTIE: Reparaties //----------------// */
	/**
	 * (ACT-REPARATIES) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<Reparatie> actieveReparaties = new ArrayList<Reparatie>();
	/**
	 * (ARCH-REPARATIES) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<Reparatie> gearchiveerdeReparaties = new ArrayList<Reparatie>();

	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */

	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Reparatie(){
		setupInitConfig();
		this.id = extrapolateId();
	}

	/**
	 * Default constructor voor deze klasse. (Geen configuratie)
	 */
	public Reparatie(boolean noConfig){
		if (!noConfig) { setupInitConfig(); }
		this.id = extrapolateId();
	}

	/**
	 * Volledige Constructor voor deze klasse.
	 */
	public Reparatie(LocalDateTime reparatieCompleet){
		setupInitConfig();
		id = extrapolateId();
		this.reparatieCompleet = reparatieCompleet;
	}

	/* //----------------// -#########--------------------#########- //----------------// */
	/* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
	/* //----------------// -#########--------------------#########- //----------------// */

	/* //----------------\\ # ------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Domein Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------- # //----------------\\ */
	// TODO laadpaal heeft geen prop!
	// TODO DocumentatieDoc wordt als static gebruikt in instanties

	/**
	 * Deze domein-functie haalt via een laadpaal reparatie-documentatie op.
	 */
	public String toonReparatieDoc(Laadpaal laadpaal){
		return repo.findReparatieDoc(laadpaal.getLaadpaalType()).getDocumentatie();
	}

	/* //----------------\\ # ---------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Technisch Variabelen # //----------------\\ */
	/* //----------------\\ # ---------------------------- # //----------------\\ */

	/**
	 * Deze technische functie zet deze instantie over van de actieve- naar de gearchiveerde arraylist.
	 */
	public void archiveer(){
		gearchiveerdeReparaties.add(this);
		actieveReparaties.remove(this);
	}

	/**
	 * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
	 */
	private void setupInitConfig(){
		actieveReparaties.add(this);
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
	/* //----------------\\ # Property Domein Variabelen # //----------------\\ */
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

	/* //----------------// PROPERTY: ReparatieCompleet //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het reparatiecompleet-attribuut van deze instantie.
	 */
	@Transient
	public void setReparatieBeeïndigd(LocalDateTime value){
		this.reparatieCompleet = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het reparatiecompleet-attribuut van deze instantie.
	 */
	@Column(name = REPARATIECOMPLEET_COL_NAME)
	public LocalDateTime getReparatieBeeïndigd(){
		return this.reparatieCompleet;
	}

	/* //----------------// PROPERTY: Probleem //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het probleem-attribuut van deze instantie.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name=PROBLEEM_COL_NAME, referencedColumnName = Probleem.ID_COL_NAME)
	public Probleem getProbleem(){
		return this.probleem;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het probleem-attribuut van deze instantie.
	 */
	@Transient
	public void setProbleem(Probleem value){
		this.probleem = value;
	}
}