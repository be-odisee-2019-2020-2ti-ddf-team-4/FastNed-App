package be.fastned.application.domain.Personen;

import be.fastned.application.dao.implementations.InstallateurHibernateDao;
import be.fastned.application.dao.Base.BaseDao;
import be.fastned.application.domain.OtherImpl.Afspraak;
import be.fastned.application.domain.OtherImpl.Laadpaal;
import be.fastned.application.domain.OtherImpl.Oplossing;
import be.fastned.application.domain.OtherImpl.Probleem;
import be.fastned.application.domain.Base.Entiteit;
import be.fastned.application.AppRunner;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;

import static be.fastned.application.domain.Personen.Installateur.ENTITY_NAME;
import static be.fastned.application.domain.Personen.Installateur.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */


@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Installateur extends PersoonImpl implements PersoonDefault, Entiteit {

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

	/* //----------------\\ # ------------------------------- # //----------------\\ */
	/* //----------------\\ # Instantie Technische Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------------- # //----------------\\ */

	/* //----------------// SECTIE: Problemen //----------------// */
	/**
	 * (ACT-PROBLEMEN) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<Probleem> actieveProblemen = new ArrayList<Probleem>();
	/**
	 * (ARCH-PROBLEMEN) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<Probleem> gearchiveerdeProblemen = new ArrayList<Probleem>();

	/* //----------------// SECTIE: Oplossingen //----------------// */
	/**
	 * (ACT-OPLOSSINGEN) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<Oplossing> actieveOplossingen = new ArrayList<Oplossing>();
	/**
	 * (ARCH-OPLOSSINGEN) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<Oplossing> gearchiveerdeOplossingen = new ArrayList<Oplossing>();


	/* //----------------// -##########-----------------------------##########- //----------------// */
	/* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########-----------------------------##########- //----------------// */

	static private BaseDao klasseDao;

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Installateur";
	public static final String TABLE_NAME = "tbl_installateurs";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = PERSOON_ID_PREFIX;

	// Constanten met kolom-namen
	private static final String ID_COL_NAME = ID_PREFIX + "ID";

	/* //----------------// SECTIE: Installateurs //----------------// */
	/**
	 * (ACT-INSTALLATEURS) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<PersoonDefault> actieveInstallateurs = new ArrayList<PersoonDefault>();
	/**
	 * (ARCH-INSTALLATEURS) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<PersoonDefault> gearchiveerdeInstallateurs = new ArrayList<PersoonDefault>();


	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */

	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Installateur(){
		super();
	}

	/**
	 * Default constructor voor deze klasse. (Optionele Configuratie)
	 */
	public Installateur(boolean noConfig){
		super(noConfig);
		if (!noConfig) { setupInitConfig(); }
	}
	/**
	 * Basische constructor voor deze klasse. (enkel accountgegevens)
	 */
	public Installateur(String gebruikersnaam, String emailadres, String wachtwoord) {
		super(gebruikersnaam, emailadres, wachtwoord);
		this.gebruikersnaam = gebruikersnaam;
		this.emailadres = emailadres;
		this.wachtwoord = wachtwoord;
		setupInitConfig();
	}
	/**
	 * Volledige constructor voor deze klasse. (accountgegevens + persoonsgegevens)
	 */
	public Installateur(String gebruikersnaam, String emailadres, String wachtwoord, String naam, String voornaam, String geslacht, String gsm ){
		super(gebruikersnaam, emailadres, wachtwoord, naam, voornaam, geslacht, gsm);
		this.gebruikersnaam = gebruikersnaam;
		this.emailadres = emailadres;
		this.wachtwoord = wachtwoord;
		this.naam = naam;
		this.voornaam = voornaam;
		this.geslacht = geslacht;
		this.gsm = gsm;
		setupInitConfig();
	}

	/* //----------------// -#########--------------------#########- //----------------// */
	/* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
	/* //----------------// -#########--------------------#########- //----------------// */

	/* //----------------\\ # ------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Domein # //----------------\\ */
	/* //----------------\\ # ------------------------- # //----------------\\ */
	/**
	 * Deze domein-functie maakt een probleem aan (i.v.m. installatie/reparatie in afspraak) via deze persoon
	 * en voegt dat probleem toe aan de lijst met actieve problemen via deze persoon.
	 * (2 signaturen -> arg om een probleem te maken OF een al aangemaakt probleem)
	 */
	public Probleem maakProbleem(boolean isInstallatie, Laadpaal defecteLaadpaal, Afspraak parentAfspraak, String probBeschrijving){
		Probleem aangemaaktProbleem = new Probleem(defecteLaadpaal, probBeschrijving);

		if (isInstallatie) parentAfspraak.getInstallatie().setProbleem(aangemaaktProbleem);
		else parentAfspraak.getReparatie().setProbleem(aangemaaktProbleem);

		actieveProblemen.add(aangemaaktProbleem);
		return aangemaaktProbleem;
	}
	public Probleem maakProbleem(Probleem aangemaaktProbleem){
		actieveProblemen.add(aangemaaktProbleem);
		return aangemaaktProbleem;
	}

	/**
	 * Deze domein-functie maakt een oplossing aan (i.v.m. installatie/reparatie) via deze persoon en
	 * voegt dat probleem toe aan de lijst met actieve problemen. (gemaakt door deze installateur)
	 * (2 signaturen -> arg om een oplossing te maken OF een al aangemaakt oplossing)
	 */
	public Oplossing maakOplossing(Probleem parentProbleem, String oplBeschrijving){
		// TODO Fix null
		Oplossing aangemaakteOplossing = new Oplossing(oplBeschrijving);

		parentProbleem.setOplossing(aangemaakteOplossing);

		actieveOplossingen.add(aangemaakteOplossing);
		return aangemaakteOplossing;
	}
	public Oplossing maakOplossing(Oplossing aangemaakteOplossing){
		actieveOplossingen.add(aangemaakteOplossing);
		return aangemaakteOplossing;
	}

	/**
	 *  Deze domein-functie overschrijft het status-attribuut van een probleem.
	 */
	public void updateAfspraakStatus(Afspraak toBeUpdatedAfspraak, String nieuweStatus){
		toBeUpdatedAfspraak.setStatus(nieuweStatus);
	}

	/**
	 * Deze domein-functie identificeert resterende attributen bij registratie zonder persoonsgegevens.
	 */
	public PersoonDefault identificeer(String naam, String voornaam, String geslacht, String gsm){
		this.naam = naam;
		this.voornaam = voornaam;
		this.geslacht = geslacht;
		this.gsm = gsm;
		return this;
	}

	/* //----------------\\ # ----------------- # //----------------\\ */
	/* //----------------\\ # Functie Base # //----------------\\ */
	/* //----------------\\ # ----------------- # //----------------\\ */

	/**
	 * Deze technische functie zet deze instantie over van de actieve- naar de gearchiveerde arraylist.
	 */
	public void archiveer(){
		gearchiveerdeInstallateurs.add(this);
		actieveInstallateurs.remove(this);
	}

	/**
	 * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
	 */
	private void setupInitConfig(){
		actieveInstallateurs.add(this);
		klasseDao = (BaseDao) AppRunner.getAppContext().getBean(InstallateurHibernateDao.BEAN_DAO_NAME);
		if (klasseDao.isTableEmpty()){
			persoonId = id = ID_PREFIX + "0";
		}
		else {
			persoonId = id = extrapolateId();
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

	/* //----------------\\ # --------------- # //----------------\\ */
	/* //----------------\\ # Property Domein # //----------------\\ */
	/* //----------------\\ # --------------- # //----------------\\ */

	/* //----------------// PROPERTY: ID //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het id-attribuut van deze instantie.
	 */
	@Transient
	//@Id @Column(name = ID_COL_NAME)
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

	/* //----------------\\ # ------------------- # //----------------\\ */
	/* //----------------\\ # Property Technische # //----------------\\ */
	/* //----------------\\ # ------------------- # //----------------\\ */

	/* //----------------// PROPERTY: Actieve & Gearchiveerde INSTALLATEURS //----------------// */
	/**
	 * (ACT-INSTALLATEUR) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. actieve installateurs.
	 */
	@Transient
	public static ArrayList<PersoonDefault> getActieveInstanties() { return actieveInstallateurs; }
	/**
	 * (ARCH-INSTALLATEUR) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. gearchiveerde installateurs.
	 */
	@Transient
	public static ArrayList<PersoonDefault> getGearchiveerdeInstanties() { return gearchiveerdeInstallateurs; }

	/* //----------------// PROPERTY: Actieve & Gearchiveerde aangemaakte PROBLEMEN //----------------// */
	/**
	 * (ACT-PROBLEMEN) Deze domein-attribuut-setter vertegenwoordigt de coll. v.d. actieve PROBLEMEN.
	 */
	@Transient
	public ArrayList<Probleem> getActieveProblemen() { return actieveProblemen; }
	/**
	 * (ARCH-PROBLEMEN) Deze domein-attribuut-setter vertegenwoordigt de coll. v.d. gearchiveerde PROBLEMEN.
	 */
	@Transient
	public ArrayList<Probleem> getGearchiveerdeProblemen() { return gearchiveerdeProblemen; }

	/* //----------------// PROPERTY: Actieve & Gearchiveerde aangemaakte OPLOSSINGEN //----------------// */
	/**
	 * (ACT-OPLOSSINGEN) Deze domein-attribuut-setter vertegenwoordigt de coll. v.d. actieve OPLOSSINGEN.
	 */
	@Transient
	public ArrayList<Oplossing> getActieveOplossingen() { return actieveOplossingen; }
	/**
	 * (ARCH-OPLOSSINGEN) Deze domein-attribuut-setter vertegenwoordigt de coll. v.d. gearchiveerde OPLOSSINGEN.
	 */
	@Transient
	public ArrayList<Oplossing> getGearchiveerdeOplossingen() { return gearchiveerdeOplossingen; }
}