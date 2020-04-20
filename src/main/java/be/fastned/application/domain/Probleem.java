package be.fastned.application.domain;


import be.fastned.application.dao.Base.BaseDao;
import be.fastned.application.dao.ProbleemHibernateDao;
import be.fastned.application.domain.Base.Entiteit;
import be.fastned.application.domain.Base.EntiteitBaseImpl;
import be.fastned.application.service.AppRunner;

import javax.persistence.*;
import java.util.ArrayList;

import static be.fastned.application.domain.Probleem.ENTITY_NAME;
import static be.fastned.application.domain.Probleem.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Probleem extends EntiteitBaseImpl implements Entiteit {

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
	private String beschrijving = null;
	private String status = null;
	private Oplossing oplossing = null;

	/* //----------------\\ # ------------------------------- # //----------------\\ */
	/* //----------------\\ # Instantie Technische Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------------- # //----------------\\ */


	/* //----------------// -##########-----------------------------##########- //----------------// */
	/* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########-----------------------------##########- //----------------// */

	static private BaseDao klasseDao;

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Probleem";
	public static final String TABLE_NAME = "tbl_probleem";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = PROBLEEM_ID_PREFIX;

	// Constanten met kolom-namen
	public static final String ID_COL_NAME = ID_PREFIX + "ID";
	public static final String BESCHRIJVING_COL_NAME = "Beschrijving";
	public static final String STATUS_COL_NAME = "Status";
	public static final String OPLOSSING_COL_NAME = "Oplossing";

	/* //----------------// SECTIE: Problemen //----------------// */
	/**
	 * (ACT-PROBLEMEN) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<Probleem> actieveProblemen = new ArrayList<Probleem>();
	/**
	 * (ARCH-PROBLEMEN) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<Probleem> gearchiveerdeProblemen = new ArrayList<Probleem>();

	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */

	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Probleem(){
		setupInitConfig();
		id = extrapolateId();
	}

	/**
	 * Default constructor voor deze klasse. (Optionele Configuratie)
	 */
	public Probleem(boolean noConfig){
		if (!noConfig) { setupInitConfig(); }
		id = extrapolateId();
	}

	/**
	 * Volledige constructor voor deze klasse.
	 */
	public Probleem(Laadpaal laadpaal, String beschrijving){
		setupInitConfig();
		id = extrapolateId();
		this.beschrijving = beschrijving;
		this.status = "aangemaakt";
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
		gearchiveerdeProblemen.add(this);
		actieveProblemen.remove(this);
	}

	/**
	 * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
	 */
	private void setupInitConfig(){
		actieveProblemen.add(this);
		klasseDao = (BaseDao) AppRunner.getAppContext().getBean(ProbleemHibernateDao.BEAN_DAO_NAME);
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

	/* //----------------// PROPERTY: Status //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het status-attribuut van deze instantie.
	 */
	@Column(name = STATUS_COL_NAME)
	public String getStatus(){
		return this.status;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het status-attribuut van deze instantie.
	 */
	@Transient
	public void setStatus(String value){
		this.status = value;
	}

	/* //----------------// PROPERTY: Beschrijving //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het beschrijving-attribuut van deze instantie.
	 */
	@Column(name = BESCHRIJVING_COL_NAME)
	public String getBeschrijving(){
		return beschrijving;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het beschrijving-attribuut van deze instantie.
	 */
	@Transient
	public void setBeschrijving(String value){ beschrijving = value; }

	/* //----------------// PROPERTY: OPlossing //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het beschrijving-attribuut van deze instantie.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name=OPLOSSING_COL_NAME, referencedColumnName = Oplossing.ID_COL_NAME)
	public Oplossing getOplossing(){
		return oplossing;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het beschrijving-attribuut van deze instantie.
	 */
	@Transient
	public void setOplossing(Oplossing value){ oplossing = value; }

	/* //----------------\\ # ---------------------------- # //----------------\\ */
	/* //----------------\\ # Propertie Base Variabelen # //----------------\\ */
	/* //----------------\\ # ---------------------------- # //----------------\\ */

	/* //----------------// PROPERTY: Actieve & Gearchiveerde PROBLEMEN (STATIC) //----------------// */
	/**
	 * (ACT-PROBLEMEN) Deze domein-attribuut-getter vertegenwoordigt de collectie v.d. actieve instanties.
	 */
	@Transient
	public static ArrayList<Probleem> getActieveInstanties() { return actieveProblemen; }
	/**
	 * (ARCH-PROBLEMEN) Deze domein-attribuut-getter vertegenwoordigt de collectie v.d. gearchiveerde instanties.
	 */
	@Transient
	public static ArrayList<Probleem> getGearchiveerdeInstanties() { return gearchiveerdeProblemen; }
}