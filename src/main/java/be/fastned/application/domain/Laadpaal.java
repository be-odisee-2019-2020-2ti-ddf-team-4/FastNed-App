package be.fastned.application.domain;

import be.fastned.application.dao.Base.BaseDao;
import be.fastned.application.dao.LaadpaalHibernateDao;
import be.fastned.application.domain.Base.Entiteit;
import be.fastned.application.domain.Base.EntiteitBaseImpl;
import be.fastned.application.domain.Personen.Locatiehouder;
import be.fastned.application.service.AppRunner;

import javax.persistence.*;
import java.util.ArrayList;

import static be.fastned.application.domain.Laadpaal.ENTITY_NAME;
import static be.fastned.application.domain.Laadpaal.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Laadpaal extends EntiteitBaseImpl implements Entiteit {

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
	private DocumentatieDoc installatieDocType;
	private DocumentatieDoc reparatieDocType;
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

	static private BaseDao klasseDao;

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Laadpaal";
	public static final String TABLE_NAME = "tbl_laadpalen";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = LAADPAAL_ID_PREFIX;

	// Constanten met kolom-namen
	public static final String ID_COL_NAME = ID_PREFIX + "ID";
	public static final String LOCATIEHOUDER_COL_NAME = "Locatiehouder_FK";
	public static final String INSTALLATIEDOC_COL_NAME = "InstallatieDocumentatie";
	public static final String REPARATIEDOC_COL_NAME = "ReparatieDocumentatie";
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
	 * Default constructor voor deze klasse. (Optionele Configuratie)
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
		this.installatieDocType = new DocumentatieDoc();/*repo.findInstallatieDoc(getLaadpaalType()).getDocumentatie();*/
		this.reparatieDocType = new DocumentatieDoc(); /*repo.findReparatieDoc(getLaadpaalType()).getDocumentatie();*/
		this.installatieDoc = installatieDocType.toString();/*repo.findInstallatieDoc(getLaadpaalType()).getDocumentatie();*/
		this.reparatieDoc = reparatieDocType.toString(); /*repo.findReparatieDoc(getLaadpaalType()).getDocumentatie();*/
		setupInitConfig();
		id = extrapolateId();
	}

	/* //----------------// -#########--------------------#########- //----------------// */
	/* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
	/* //----------------// -#########--------------------#########- //----------------// */

	/* //----------------\\ # ------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Domein # //----------------\\ */
	/* //----------------\\ # ------------------------- # //----------------\\ */

	/* //----------------\\ # ---------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Base # //----------------\\ */
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
		klasseDao = (BaseDao) AppRunner.getAppContext().getBean(LaadpaalHibernateDao.BEAN_DAO_NAME);
		if (klasseDao.isTableEmpty()){
			id = id = ID_PREFIX + "0";
		}
		else {
			id = id = extrapolateId();
		}
	}

	/**
	 * Deze technische functie leidt het id af via het laatste record in de tabel.
	 */
	private String extrapolateId(){
		Integer prefixLength = ID_PREFIX.length();
		Integer highest = 0;

		for (Entiteit item : klasseDao.getAllItems() ) {
			item.getId();
			Integer idCount = Integer.parseInt(item.getId().substring(prefixLength));
			if (idCount > highest)
				highest = idCount;
		}
		return ID_PREFIX + (highest+1);
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

	/* //----------------// PROPERTY: Locatiehouder //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het locatiehouder-attribuut.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name=LOCATIEHOUDER_COL_NAME, referencedColumnName = Locatiehouder.ID_COL_NAME_PERSOON)
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
	// TODO ToString is tijdelijke fix -> DocumentatieDoc type
	@Column(name = INSTALLATIEDOC_COL_NAME)
	public String getInstallatieDoc(){
		return this.installatieDoc.toString();
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