package be.fastned.application.domain.AndereEntiteiten;

import be.fastned.application.dao.Base.BaseDao;
import be.fastned.application.dao.OplossingHibernateDao;
import be.fastned.application.domain.Base.AbsoluteBase;
import be.fastned.application.service.AppRunner;
import javax.persistence.*;
import java.util.ArrayList;

import static be.fastned.application.domain.AndereEntiteiten.Oplossing.ENTITY_NAME;
import static be.fastned.application.domain.AndereEntiteiten.Oplossing.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Oplossing extends AbsoluteBase {

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
	private String oplossing;

	/* //----------------\\ # ------------------------------- # //----------------\\ */
	/* //----------------\\ # Instantie Technische Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------------- # //----------------\\ */


	/* //----------------// -##########-----------------------------##########- //----------------// */
	/* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########-----------------------------##########- //----------------// */

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Oplossing";
	public static final String TABLE_NAME = "tbl_Oplossingen";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = OPLOSSING_ID_PREFIX;

	// Constanten met kolom-namen
	public static final String ID_COL_NAME = ID_PREFIX + "Id";
	public static final String OPLOSSING_COL_NAME = "Oplossing";

	/* //----------------// SECTIE: Afspraken //----------------// */
	/**
	 * (ACT-OPLOSSINGEN) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<Oplossing> actieveOplossingen = new ArrayList<Oplossing>();
	/**
	 * (ARCH-OPLOSSINGEN) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<Oplossing> gearchiveerdeOplossingen = new ArrayList<Oplossing>();

	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */

	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Oplossing(){
		setupInitConfig();
		id = extrapolateId();
	}

	/**
	 * Default constructor voor deze klasse. (Geen configuratie)
	 */
	public Oplossing(boolean noConfig){
		if (!noConfig) { setupInitConfig(); }
		id = extrapolateId();
	}

	/**
	 * Volledige constructor voor deze klasse.
	 */
	public Oplossing(String oplossing){
		//setupInitConfig();
		//id = extrapolateId();
		this.oplossing = oplossing;
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
		gearchiveerdeOplossingen.add(this);
		actieveOplossingen.remove(this);
	}

	/**
	 * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
	 */
	private void setupInitConfig(){
		actieveOplossingen.add(this);
		klasseDao = (BaseDao) AppRunner.getAppContext().getBean(OplossingHibernateDao.BEAN_DAO_NAME);
	}

	/**
	 * Deze technische functie leidt het id af via het laatste record in de tabel.
	 */
	private String extrapolateId(){
		return baseExtrapolateId(ID_PREFIX, klasseDao);
	}

	/* //----------------// -#####------------------#####- //----------------// */
	/* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
	/* //----------------// -#####------------------#####- //----------------// */

	/* //----------------\\ <||> ----------------- <||> //----------------\\ */
	/* //----------------\\ <||> DOMEIN Properties <||> //----------------\\ */
	/* //----------------\\ <||> ----------------- <||> //----------------\\ */

	/* //----------------// PROPERTY: ID //----------------// */
	/**
	 * Deze domein-attribuut getter vertegenwoordigt het id-attribuut van deze instantie.
	 */
	@Id @Column(name = ID_COL_NAME)
	public String getId(){
		return this.id;
	}
	/**
	 * Deze domein-attribuut setter vertegenwoordigt het id-attribuut van deze instantie.
	 */
	@Transient
	public void setId(String value){
		this.id = value;
	}

	/* //----------------// PROPERTY: Oplossing //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt het oplossing-attribuut van deze instantie.
	 */
	@Column(name = OPLOSSING_ID_PREFIX)
	public String getOplossing(){
		return this.oplossing;
	}
	/**
	 * Deze domein-attribuut setter vertegenwoordigt het oplossing-attribuut van deze instantie.
	 */
	@Transient
	public void setOplossing(String value){
		this.oplossing = value;
	}


	/* //----------------\\ # ---------------------------- # //----------------\\ */
	/* //----------------\\ # Propertie Technisch Variabelen # //----------------\\ */
	/* //----------------\\ # ---------------------------- # //----------------\\ */

	/* //----------------// PROPERTY: Actieve & Gearchiveerde AFSPRAKEN (STATIC) //----------------// */
	/**
	 * (ACT-OPLOSSINGEN) Deze domein-attribuut-getter vertegenwoordigt de collectie v.d. actieve instanties.
	 */
	@Transient
	public static ArrayList<Oplossing> getActieveInstanties() { return actieveOplossingen; }
	/**
	 * (ARCH-OPLOSSINGEN) Deze domein-attribuut-getter vertegenwoordigt de collectie v.d. gearchiveerde instanties.
	 */
	@Transient
	public static ArrayList<Oplossing> getGearchiveerdeInstanties() { return gearchiveerdeOplossingen; }
}