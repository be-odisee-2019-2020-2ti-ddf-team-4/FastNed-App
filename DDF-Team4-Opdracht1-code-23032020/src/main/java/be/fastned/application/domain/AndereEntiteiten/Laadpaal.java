package be.fastned.application.domain.AndereEntiteiten;

import be.fastned.application.dao.AfspraakHibernateDao;
import be.fastned.application.dao.Base.BaseDao;
import be.fastned.application.domain.Base.AbsoluteBase;
import be.fastned.application.domain.PersoonEntiteiten.Locatiehouder;
import be.fastned.application.domain.Technisch.DocumentatieDoc;
import be.fastned.application.service.AppRunner;
import javax.persistence.*;
import java.util.ArrayList;
import static be.fastned.application.domain.AndereEntiteiten.Laadpaal.ENTITY_NAME;
import static be.fastned.application.domain.AndereEntiteiten.Laadpaal.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Laadpaal extends AbsoluteBase {

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
	private Locatiehouder locatiehouder;
	private String installatieDoc;
	private String reparatieDoc;
	private String laadpaalType = null;
	private String status;

	/* //----------------\\ # ------------------------------- # //----------------\\ */
	/* //----------------\\ # Instantie Technische Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------------- # //----------------\\ */


	/* //----------------// -##########-----------------------------##########- //----------------// */
	/* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########-----------------------------##########- //----------------// */

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Laadpaal";
	public static final String TABLE_NAME = "tbl_laadpalen";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = LAADPAAL_ID_PREFIX;

	// Constanten met kolom-namen
	public static final String ID_COL_NAME = ID_PREFIX + "Id";
	public static final String LOCATIEHOUDER_COL_NAME = "Locatiehouder";
	public static final String INSTALLATIEDOC_COL_NAME = "InstallatieDoc";
	public static final String REPARATIEDOC_COL_NAME = "ReparatieDoc";
	public static final String LAADPAALTYPE_COL_NAME = "LaadpaalType";
	public static final String STATUS_COL_NAME = "Status";

	// Technische constanten
	public static DocumentatieDoc repo = new DocumentatieDoc();
	private static ArrayList<DocumentatieDoc> reparatieDocs = repo.getReparatieDocumentaties();
	private static ArrayList<DocumentatieDoc> installatieDocs = repo.getInstallatieDocumentaties();

	/* //----------------// SECTIE: Laadpalen //----------------// */
	/**
	 * (ACT-LAADPALEN) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<Laadpaal> actieveLaadpalen = new ArrayList<Laadpaal>();
	/**
	 * (ARCH-LAADPALEN) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<Laadpaal> gearchiveerdeLaadpalen = new ArrayList<Laadpaal>();

	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */

	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Laadpaal(){
		setupInitConfig();
		id = extrapolateId();
	}

	/**
	 * Default constructor voor deze klasse. (Geen configuratie)
	 */
	public Laadpaal(boolean noConfig){
		if (!noConfig) { setupInitConfig(); }
		id = extrapolateId();
	}

	/**
	 * Volledige Constructor voor deze klasse.
	 */
	public Laadpaal(Locatiehouder locatiehouder, String laadpaalType){
		this.locatiehouder = locatiehouder;
		this.laadpaalType = laadpaalType;
		this.status = "aangemaakt";
		this.installatieDoc = repo.findInstallatieDoc(getLaadpaalType()).getDocumentatie();
		this.reparatieDoc = repo.findReparatieDoc(getLaadpaalType()).getDocumentatie();
		setupInitConfig();
		id = extrapolateId();
	}

	/* //----------------// -#########--------------------#########- //----------------// */
	/* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
	/* //----------------// -#########--------------------#########- //----------------// */

	/* //----------------\\ # ------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Domein Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------- # //----------------\\ */

	/* //----------------\\ # ---------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Technisch Variabelen # //----------------\\ */
	/* //----------------\\ # ---------------------------- # //----------------\\ */

	/**
	 * Deze technische functie zet deze instantie over van de actieve- naar de gearchiveerde arraylist.
	 */
	public void archiveer(){
		gearchiveerdeLaadpalen.add(this);
		actieveLaadpalen.remove(this);
	}

	/**
	 * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
	 */
	private void setupInitConfig(){
		actieveLaadpalen.add(this);
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

	/* //----------------// PROPERTY: Locatiehouder //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het locatiehouder-attribuut.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name=LOCATIEHOUDER_COL_NAME, referencedColumnName = Locatiehouder.ID_COL_NAME)
	public Locatiehouder getLocatiehouder(){
		return this.locatiehouder;
	}
	/**
	 * Deze domein-attribuut setter vertegenwoordigt het locatiehouder-attribuut.
	 */
	@Transient
	public void setLocatiehouder(Locatiehouder value){
		this.locatiehouder = value;
	}

	/* //----------------// PROPERTY: Laadpaal-Type //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het producttype-attribuut.
	 */
	@Column(name = LAADPAALTYPE_COL_NAME)
	public String getLaadpaalType(){
		return this.laadpaalType;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het producttype-attribuut.
	 */
	@Transient
	public void setLaadpaalType(String value){
		this.laadpaalType = value;
	}

	/* //----------------// PROPERTY: Status //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het status-attribuut.
	 */
	@Column(name = STATUS_COL_NAME)
	public String getStatus(){
		return this.status;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het status-attribuut.
	 */
	@Transient
	public void setStatus(String value){
		this.status = value;
	}

	/* //----------------// PROPERTY: Installatie-Documentatie //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het installatiedocumentatie-attribuut.
	 */
	@Column(name = INSTALLATIEDOC_COL_NAME)
	public String getInstallatieDoc(){
		return this.installatieDoc;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het installatiedocumentatie-attribuut.
	 */
	@Transient
	public void setInstallatieDoc(String value){
		this.installatieDoc = value;
	}

	/* //----------------// PROPERTY: Reparatie-Documentatie //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het reparatiedocumentatie-attribuut.
	 */
	@Column(name = REPARATIEDOC_COL_NAME)
	public String getReparatieDoc(){
		return this.reparatieDoc;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het reparatiedocumentatie-attribuut.
	 */
	@Transient
	public void setReparatieDoc(String value){
		this.reparatieDoc = value;
	}
}