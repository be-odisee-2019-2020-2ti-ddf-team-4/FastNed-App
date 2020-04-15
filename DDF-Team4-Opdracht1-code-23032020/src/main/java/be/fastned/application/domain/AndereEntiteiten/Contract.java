package be.fastned.application.domain.AndereEntiteiten;

import be.fastned.application.dao.ContractHibernateDao;
import be.fastned.application.dao.Base.BaseDao;
import be.fastned.application.domain.Base.EntiteitBaseImpl;
import be.fastned.application.service.AppRunner;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static be.fastned.application.domain.AndereEntiteiten.Contract.ENTITY_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = Oplossing.TABLE_NAME)

public class Contract extends EntiteitBaseImpl {

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
	private LocalDateTime contractDatum = null;
	private LocalDateTime uitvoeringsDatum = null;

	/* //----------------\\ # ------------------------------- # //----------------\\ */
	/* //----------------\\ # Instantie Technische Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------------- # //----------------\\ */

	/* //----------------// -##########-----------------------------##########- //----------------// */
	/* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########-----------------------------##########- //----------------// */

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Contract";
	public static final String TABLE_NAME = "tbl_contracten";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = CONTRACT_ID_PREFIX;

	// Constanten met kolom-namen
	public static final String ID_COL_NAME = ID_PREFIX + "ID";
	public static final String CONTRACTDATUM_COL_NAME = "ContractDatum";
	public static final String UITVOERINGSDATUM_COL_NAME = "UitvoeringsDatum";

	/* //----------------// SECTIE: Contracten //----------------// */
	/**
	 * (ACT-CONTRACTEN) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<Contract> actieveContracten = new ArrayList<Contract>();
	/**
	 * (ARCH-CONTRACTEN) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<Contract> gearchiveerdeContracten = new ArrayList<Contract>();

	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */
	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Contract(){
		setupInitConfig();
		id = extrapolateId();
	}

	/**
	 * Default constructor voor deze klasse. (Geen configuratie)
	 */
	public Contract(boolean noConfig){
		if (!noConfig) { setupInitConfig(); }
		id = extrapolateId();
	}

	/**
	 * Volledige Constructor voor deze klasse.
	 */
	public Contract(LocalDateTime contractDatum, LocalDateTime uitvoeringsDatum){
		setupInitConfig();
		id = extrapolateId();
		this.contractDatum = contractDatum;
		this.uitvoeringsDatum = uitvoeringsDatum;
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
		gearchiveerdeContracten.add(this);
		actieveContracten.remove(this);
	}

	/**
	 * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
	 */
	private void setupInitConfig(){
		actieveContracten.add(this);
		klasseDao = (BaseDao) AppRunner.getAppContext().getBean(ContractHibernateDao.BEAN_DAO_NAME);
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

	/* //----------------// PROPERTY: ContractDatum //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het contractdatum-attribuut van deze instantie.
	 */
	@Column(name = CONTRACTDATUM_COL_NAME)
	public LocalDateTime getContractDatum(){
		return this.contractDatum;
	}
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het contractdatum-attribuut van deze instantie.
	 */
	@Transient
	public void setContractDatum(LocalDateTime value){
		this.contractDatum = value;
	}


	/* //----------------// PROPERTY: ContractDatum //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het contractdatum-attribuut van deze instantie.
	 */
	@Column(name = UITVOERINGSDATUM_COL_NAME)
	public LocalDateTime getUitvoeringsDatum(){
		return this.uitvoeringsDatum;
	}
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het contractdatum-attribuut van deze instantie.
	 */
	@Transient
	public void setUitvoeringsDatum(LocalDateTime value){
		this.uitvoeringsDatum = value;
	}


	/* //----------------\\ # ---------------------------- # //----------------\\ */
	/* //----------------\\ # Propertie Technisch Variabelen # //----------------\\ */
	/* //----------------\\ # ---------------------------- # //----------------\\ */

	/* //----------------// PROPERTY: Actieve & Gearchiveerde AFSPRAKEN (STATIC) //----------------// */
	/**
	 * (ACT-CONTRACTEN) Deze domein-attribuut-getter vertegenwoordigt de collectie v.d. actieve instanties.
	 */
	@Transient
	public static ArrayList<Contract> getActieveInstanties() { return actieveContracten; }
	/**
	 * (ARCH-CONTRACTEN) Deze domein-attribuut-getter vertegenwoordigt de collectie v.d. gearchiveerde instanties.
	 */
	@Transient
	public static ArrayList<Contract> getGearchiveerdeInstanties() { return gearchiveerdeContracten; }
}