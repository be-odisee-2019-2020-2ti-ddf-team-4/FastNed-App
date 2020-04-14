package be.fastned.application.domain.AndereEntiteiten;

import be.fastned.application.dao.InstallatieHibernateDao;
import be.fastned.application.dao.Base.BaseDao;
import be.fastned.application.domain.Base.AbsoluteBase;
import be.fastned.application.domain.Technisch.Bezoek;
import be.fastned.application.domain.Technisch.DocumentatieDoc;
import be.fastned.application.service.AppRunner;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static be.fastned.application.domain.AndereEntiteiten.Installatie.ENTITY_NAME;
import static be.fastned.application.domain.AndereEntiteiten.Installatie.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Installatie extends AbsoluteBase implements Bezoek {

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
	private LocalDateTime installatieCompleet;
	private Probleem probleem;

	/* //----------------\\ # ------------------------------- # //----------------\\ */
	/* //----------------\\ # Instantie Technische Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------------- # //----------------\\ */


	/* //----------------// -##########-----------------------------##########- //----------------// */
	/* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########-----------------------------##########- //----------------// */

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Installatie";
	public static final String TABLE_NAME = "tbl_installaties";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = INSTALLATIE_ID_PREFIX;

	// Constanten met kolom-namen
	public static final String ID_COL_NAME = ID_PREFIX + "ID";
	public static final String INSTALLATIECOMPLEET_COL_NAME = "InstallatieCompleet";
	public static final String PROBLEEM_COL_NAME = "Probleem_FK";

	// Technische constanten
	public static DocumentatieDoc repo = new DocumentatieDoc();
	private static ArrayList<DocumentatieDoc> installatieDocs = repo.getInstallatieDocumentaties();

	/* //----------------// SECTIE: Installaties //----------------// */
	/**
	 * (ACT-INSTALLATIES) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<Installatie> actieveInstallaties = new ArrayList<Installatie>();
	/**
	 * (ARCH-INSTALLATIES) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<Installatie> gearchiveerdeInstallaties = new ArrayList<Installatie>();

	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */

	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Installatie(){
		setupInitConfig();
		id = extrapolateId();
	}

	/**
	 * Default constructor voor deze klasse. (Geen configuratie)
	 */
	public Installatie(boolean noConfig){
		if (!noConfig) { setupInitConfig(); }
		id = extrapolateId();
	}
	/**
	 * Volledige Constructor voor deze klasse.
	 */
	public Installatie(LocalDateTime installatieCompleet){
		this.installatieCompleet = installatieCompleet;
		setupInitConfig();
		id = extrapolateId();
	}

	/* //----------------// -#########--------------------#########- //----------------// */
	/* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
	/* //----------------// -#########--------------------#########- //----------------// */

	/* //----------------\\ # ------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Domein Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------- # //----------------\\ */

	/**
	 * Deze domein-functie haalt via de laadpaal installatie-documentatie op.
	 */
	public String toonInstallatieDoc(Laadpaal laadpaal){
		return repo.findInstallatieDoc(laadpaal.getLaadpaalType()).getDocumentatie();
	}

	/* //----------------\\ # ---------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Technisch Variabelen # //----------------\\ */
	/* //----------------\\ # ---------------------------- # //----------------\\ */

	/**
	 * Deze technische functie zet deze instantie over van de actieve- naar de gearchiveerde arraylist.
	 */
	public void archiveer(){
		gearchiveerdeInstallaties.add(this);
		actieveInstallaties.remove(this);
	}

	/**
	 * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
	 */
	private void setupInitConfig(){
		actieveInstallaties.add(this);
		klasseDao = (BaseDao) AppRunner.getAppContext().getBean(InstallatieHibernateDao.BEAN_DAO_NAME);
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

	/* //----------------// PROPERTY: Installatie Compleet //----------------// */
	/**
	 * Deze domein-attribuut-setter vertegenwoordigt het installatieCompleet-attribuut van deze instantie.
	 */
	@Column(name = INSTALLATIECOMPLEET_COL_NAME)
	public LocalDateTime getInstallatieCompleet(){
		return this.installatieCompleet;
	}
	/**
	 * Deze domein-attribuut-setter vertegenwoordigt het installatieCompleet-attribuut van deze instantie.
	 */
	@Transient
	public void setInstallatieCompleet(LocalDateTime value){
		this.installatieCompleet = value;
	}


	/* //----------------// PROPERTY: Probleem //----------------// */
	/**
	 * Deze domein-attribuut-setter vertegenwoordigt het probleem-attribuut van deze instantie.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name=PROBLEEM_COL_NAME, referencedColumnName = Probleem.ID_COL_NAME)
	public Probleem getProbleem(){
		return this.probleem;
	}
	/**
	 * Deze domein-attribuut-setter vertegenwoordigt het probleem-attribuut van deze instantie.
	 */
	@Transient
	public void setProbleem(Probleem value){
		this.probleem = value;
	}


	/* //----------------\\ # ---------------------------- # //----------------\\ */
	/* //----------------\\ # Propertie Technisch Variabelen # //----------------\\ */
	/* //----------------\\ # ---------------------------- # //----------------\\ */

	/* //----------------// PROPERTY: Actieve & Gearchiveerde INSTALLATIE (STATIC) //----------------// */
	/**
	 * (ACT-INSTALLATIES) Deze domein-attribuut-getter vertegenwoordigt de collectie v.d. actieve instanties.
	 */
	@Transient
	public static ArrayList<Installatie> getActieveInstanties() { return actieveInstallaties; }
	/**
	 * (ARCH-INSTALLATIES) Deze domein-attribuut-getter vertegenwoordigt de collectie v.d. gearchiveerde instanties.
	 */
	@Transient
	public static ArrayList<Installatie> getGearchiveerdeInstanties() { return gearchiveerdeInstallaties; }
}