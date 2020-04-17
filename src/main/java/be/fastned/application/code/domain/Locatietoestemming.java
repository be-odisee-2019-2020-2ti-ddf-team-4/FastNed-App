package be.fastned.application.code.domain;

import be.fastned.application.code.dao.AfspraakHibernateDao;
import be.fastned.application.code.dao.Base.BaseDao;
import be.fastned.application.code.dao.InstallateurHibernateDao;
import be.fastned.application.code.dao.LocatietoestemmingHibernateDao;
import be.fastned.application.code.domain.Base.Entiteit;
import be.fastned.application.code.domain.Base.EntiteitBaseImpl;
import be.fastned.application.code.service.AppRunner;

import javax.persistence.*;
import java.util.ArrayList;

import static be.fastned.application.code.domain.Locatietoestemming.ENTITY_NAME;
import static be.fastned.application.code.domain.Locatietoestemming.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Locatietoestemming extends EntiteitBaseImpl implements Entiteit {

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

	private String id = null;
	private int aantalLaadpalen;
	private String typeLaadpaal = null;
	private String status = null;

	/* //----------------\\ # ------------------------------- # //----------------\\ */
	/* //----------------\\ # Instantie Technische Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------------- # //----------------\\ */


	/* //----------------// -##########-----------------------------##########- //----------------// */
	/* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########-----------------------------##########- //----------------// */

	static private BaseDao klasseDao;

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Locatietoestemming";
	public static final String TABLE_NAME = "tbl_locatietoestemmingen";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = LOCATIETOESTEMMING_ID_PREFIX;

	// Constanten met kolom-namen
	public static final String ID_COL_NAME = ID_PREFIX + "ID";
	public static final String AANTALLAADPALEN_COL_NAME = "AantalLaadpalen";
	public static final String TYPELAADPAAL_COL_NAME = "TypeLaadpaal";
	public static final String STATUS_COL_NAME = "Status";

	/* //----------------// SECTIE: Locatietoestemmingen //----------------// */
	/**
	 * (ACT-LOCATIETOESTEMMINGEN) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<Locatietoestemming> actieveLocatietoestemmingen = new ArrayList<Locatietoestemming>();
	/**
	 * (ARCH-LOCATIETOESTEMMINGEN) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<Locatietoestemming> gearchiveerdeLocatietoestemmingen = new ArrayList<Locatietoestemming>();

	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */

	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Locatietoestemming(){
		setupInitConfig();
		id = extrapolateId();
	}

	/**
	 * Default constructor voor deze klasse. (Optionele Configuratie)
	 */
	public Locatietoestemming(boolean noConfig){
		if (!noConfig) { setupInitConfig(); }
		id = extrapolateId();
	}

	/**
	 * Volledige Constructor voor deze klasse.
	 */
	public Locatietoestemming(int aantalLaadpalen, String typeLaadpaal, String status){
		setupInitConfig();
		id = extrapolateId();
		this.aantalLaadpalen = aantalLaadpalen;
		this.typeLaadpaal = typeLaadpaal;
		this.status = status;
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
		gearchiveerdeLocatietoestemmingen.add(this);
		actieveLocatietoestemmingen.remove(this);
	}

	/**
	 * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
	 */
	private void setupInitConfig(){
		actieveLocatietoestemmingen.add(this);
		klasseDao = (BaseDao) AppRunner.getAppContext().getBean(LocatietoestemmingHibernateDao.BEAN_DAO_NAME);
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

	/* //----------------// PROPERTY: AantalLaadpalen //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het aantalLaadpalen-attribuut van deze instantie.
	 */
	@Column(name = AANTALLAADPALEN_COL_NAME)
	public int getAantalLaadpalen(){ return this.aantalLaadpalen; }
	/**
	 * Deze domein-attribuut setter vertegenwoordigt het aantalLaadpalen-attribuut van deze instantie.
	 */
	@Transient
	public void setAantalLaadpalen(int value){ this.aantalLaadpalen = value; }


	/* //----------------// PROPERTY: TypeLaadpaal //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het typeLaadpaal-attribuut van deze instantie.
	 */
	@Column(name = TYPELAADPAAL_COL_NAME)
	public String getTypeLaadpaal(){
		return this.typeLaadpaal;
	}
	/**
	 * Deze domein-attribuut setter vertegenwoordigt het typeLaadpaal-attribuut van deze instantie.
	 */
	@Transient
	public void setTypeLaadpaal(String value){ this.typeLaadpaal = value; }


	/* //----------------// PROPERTY: Status //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het status-attribuut van deze instantie.
	 */
	@Column(name = STATUS_COL_NAME)
	public String getStatus(){ return this.status; }
	/**
	 * Deze domein-attribuut setter vertegenwoordigt het status-attribuut van deze instantie.
	 */
	@Transient
	public void setStatus(String value){ this.status = value; }
}