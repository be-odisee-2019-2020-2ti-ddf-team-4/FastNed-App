package be.fastned.application.domain;

import be.fastned.application.dao.AfspraakHibernateDao;
import be.fastned.application.dao.Base.BaseDao;
import be.fastned.application.domain.Base.Entiteit;
import be.fastned.application.domain.Base.EntiteitBaseImpl;
import be.fastned.application.domain.Personen.Installateur;
import be.fastned.application.domain.Technisch.Bezoek;
import be.fastned.application.domain.Technisch.EnumStatus;
import be.fastned.application.service.AppRunner;

import javax.persistence.*;
import java.util.ArrayList;

import static be.fastned.application.domain.Afspraak.ENTITY_NAME;
import static be.fastned.application.domain.Afspraak.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Afspraak extends EntiteitBaseImpl implements Entiteit {

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
	private Laadpaal laadpaal = null;
	private Installateur installateur = null;
	private Contract contract = null;
	private String  status = null;
	private Bezoek bezoek = null;

	/* //----------------\\ # ------------------------------- # //----------------\\ */
	/* //----------------\\ # Instantie Technische Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------------- # //----------------\\ */


	/* //----------------// -##########-----------------------------##########- //----------------// */
	/* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########-----------------------------##########- //----------------// */

	static private BaseDao klasseDao;

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Afspraak";
	public static final String TABLE_NAME = "tbl_afspraken";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = AFSPRAAK_ID_PREFIX;

	// Constanten met kolom-namen
	public static final String ID_COL_NAME = ID_PREFIX + "ID";
	public static final String LAADPAAL_COL_NAME = "Laadpaal_FK";
	public static final String INSTALLATEUR_COL_NAME = "Installateur_FK";
	public static final String CONTRACT_COL_NAME = "Contract_FK";
	public static final String STATUS_COL_NAME = "Status";
	public static final String INSTALLATIE_COL_NAME = "Installatie_FK";
	public static final String REPARATIE_COL_NAME = "Reparatie_FK";

	/* //----------------// SECTIE: Afspraken //----------------// */
	/**
	 * (ACT-AFSPRAKEN) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<Afspraak> actieveAfspraken = new ArrayList<Afspraak>();
	/**
	 * (ARCH-AFSPRAKEN) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<Afspraak> gearchiveerdeAfspraken = new ArrayList<Afspraak>();

	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */

	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Afspraak(){
		setupInitConfig();
		id = extrapolateId();
	}

	/**
	 * Default constructor voor deze klasse. (Optionele Configuratie)
	 */
	public Afspraak(boolean noConfig){
		if (!noConfig) { setupInitConfig(); }
		id = extrapolateId();
	}

	/**
	 * Volledige constructor voor deze klasse.
	 */
	public Afspraak(Laadpaal laadpaal, Installateur installateur, Contract contract){
		setupInitConfig();
		this.laadpaal = laadpaal;
		this.installateur = installateur;
		this.contract = contract;
		this.status = EnumStatus.AANGEMAAKT.getValue();
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
		gearchiveerdeAfspraken.add(this);
		actieveAfspraken.remove(this);
	}

	/**
	 * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
	 */
	private void setupInitConfig(){
		actieveAfspraken.add(this);
		klasseDao = (BaseDao) AppRunner.getAppContext().getBean(AfspraakHibernateDao.BEAN_DAO_NAME);
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
	 * Deze domein-attribuut-setter vertegenwoordigt het laadpaal-attribuut van deze instantie.
	 */
	@Transient
	public void setLaadpaal(Laadpaal value){
		this.laadpaal = value;
	}

	/* //----------------// PROPERTY: Installateur //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het installateur-attribuut van deze instantie.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name=INSTALLATEUR_COL_NAME, referencedColumnName = Installateur.ID_COL_NAME_PERSOON)
	public Installateur getInstallateur(){
		return this.installateur;
	}
	/**
	 * Deze domein-attribuut-setter vertegenwoordigt het installateur-attribuut van deze instantie.
	 */
	@Transient
	public void setInstallateur(Installateur value){
		this.installateur = value;
	}

	/* //----------------// PROPERTY: Contract //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het contract-attribuut van deze instantie.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name=CONTRACT_COL_NAME, referencedColumnName = Contract.ID_COL_NAME)
	public Contract getContract(){
		return this.contract;
	}
	/**
	 * Deze domein-attribuut-setter vertegenwoordigt het contract-attribuut van deze instantie.
	 */
	@Transient
	public void setContract(Contract value){
		this.contract = value;
	}

	/* //----------------// PROPERTY: Status //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het status-attribuut van deze instantie.
	 */
	@Column(name = STATUS_COL_NAME)
	public String getStatus(){
		return this.status;
	}
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het status-attribuut van deze instantie.
	 */
	@Transient
	public void setStatus(String value){
		this.status = value;
	}

	/* //----------------// PROPERTY: Installatie //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het installatie-attribuut van deze instantie.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name=INSTALLATIE_COL_NAME, referencedColumnName = Installatie.ID_COL_NAME)
	public Installatie getInstallatie(){
		return ((Installatie) this.getBezoek());
	}
	/**
	 * Deze domein-attribuut-setter vertegenwoordigt het installatie-attribuut van deze instantie.
	 */
	@Transient
	public void setInstallatie(Installatie value){
		this.setBezoek(value);
	}

	/* //----------------// PROPERTY: Reparatie //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het reparatie-attribuut van deze instantie.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name=REPARATIE_COL_NAME, referencedColumnName = Reparatie.ID_COL_NAME)
	public Reparatie getReparatie(){
		return ((Reparatie) this.getBezoek());
	}
	/**
	 * Deze domein-attribuut-setter vertegenwoordigt het installatie-attribuut van deze instantie.
	 */
	@Transient
	public void setReparatie(Reparatie value){
		this.setBezoek(value);
	}

	/* //----------------\\ # ---------------------------- # //----------------\\ */
	/* //----------------\\ # Propertie Base Variabelen # //----------------\\ */
	/* //----------------\\ # ---------------------------- # //----------------\\ */

	/* //----------------// PROPERTY: Actieve & Gearchiveerde AFSPRAKEN (STATIC) //----------------// */
	/**
	 * (ACT-AFSPRAKEN) Deze domein-attribuut-getter vertegenwoordigt de collectie v.d. actieve instanties.
	 */
	@Transient
	public static ArrayList<Afspraak> getActieveInstanties() { return actieveAfspraken; }
	/**
	 * (ARCH-AFSPRAKEN) Deze domein-attribuut-getter vertegenwoordigt de collectie v.d. gearchiveerde instanties.
	 */
	@Transient
	public static ArrayList<Afspraak> getGearchiveerdeInstanties() { return gearchiveerdeAfspraken; }

	/* //----------------// PROPERTY: Bezoek (PRIVATE) //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt de bezoek-member van deze instantie.
	 * (Gateway voor Reparatie-/Installatie-prop die dezelfde bezoek-member refereren)
	 */
	@Transient
	private Bezoek getBezoek(){
		return this.bezoek;
	}
	/**
	 * Deze domein-attribuut-setter vertegenwoordigt de bezoek-member van deze instantie.
	 * (Gateway voor Reparatie-/Installatie-prop die dezelfde bezoek-member refereren)
	 */
	@Transient
	private void setBezoek(Bezoek value){
		this.bezoek = value;
	}
}