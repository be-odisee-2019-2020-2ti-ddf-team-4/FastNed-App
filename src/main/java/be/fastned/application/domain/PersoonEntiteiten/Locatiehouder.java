package be.fastned.application.domain.PersoonEntiteiten;

import be.fastned.application.dao.AfspraakHibernateDao;
import be.fastned.application.dao.Base.BaseDao;
import be.fastned.application.domain.AndereEntiteiten.Laadpaal;
import be.fastned.application.domain.AndereEntiteiten.Locatietoestemming;
import be.fastned.application.domain.AndereEntiteiten.Probleem;
import be.fastned.application.domain.Base.Entiteit;
import be.fastned.application.service.AppRunner;
import javax.persistence.*;
import java.util.ArrayList;
import static be.fastned.application.domain.PersoonEntiteiten.Locatiehouder.ENTITY_NAME;
import static be.fastned.application.domain.PersoonEntiteiten.Locatiehouder.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Locatiehouder extends PersoonImpl implements PersoonExtended, Entiteit {

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
	private String bedrijfsnaam;
	private String btwNummer;
	private String adres;
	private PersoonImpl parentPersoon;

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

	/* //----------------// SECTIE: Locatietoestemmingen //----------------// */
	/**
	 * (ACT-LOCATIETOESTEMMINGEN) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<Locatietoestemming> actieveLocatietoestemmingen = new ArrayList<Locatietoestemming>();
	/**
	 * (ARCH-LOCATIETOESTEMMINGEN) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<Locatietoestemming> gearchiveerdeLocatietoestemmingen = new ArrayList<Locatietoestemming>();

	/* //----------------// -##########-----------------------------##########- //----------------// */
	/* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########-----------------------------##########- //----------------// */

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Locatiehouder";
	public static final String TABLE_NAME = "tbl_locatiehouders";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = LOCATIEHOUDER_ID_PREFIX;

	// Constanten met kolom-namen
	private static final String ID_COL_NAME = ID_PREFIX + "ID";
	public static final String ADRES_COL_NAME = "Adres";
	public static final String BTWNUMMER_COL_NAME = "BTWNummer";
	public static final String BEDRIJFSNAAM_COL_NAME = "Bedrijfsnaam";
	public static final String PERSOON_COL_NAME = "Persoon_FK";

	/* //----------------// SECTIE: Locatiehouders //----------------// */
	/**
	 * (ACT-LOCATIEHOUDERS) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<PersoonExtended> actieveLocatiehouders = new ArrayList<PersoonExtended>();
	/**
	 * (ARCH-LOCATIEHOUDERS) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<PersoonExtended> gearchiveerdeLocatiehouders = new ArrayList<PersoonExtended>();

	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */

	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Locatiehouder(){
		super();
		setupInitConfig();
	}

	/**
	 * Default constructor voor deze klasse. (Optionele Configuratie)
	 */
	public Locatiehouder(boolean noConfig){
		super(noConfig);
		if (!noConfig) { setupInitConfig(); }
	}

	/**
	 * Basische constructor voor deze klasse. (enkel accountgegevens)
	 */
	public Locatiehouder(String gebruikersnaam, String emailadres, String wachtwoord) {
		super(gebruikersnaam, emailadres, wachtwoord);
		setupInitConfig();
	}
	/**
	 * Volledige constructor voor deze klasse. (accountgegevens + persoonsgegevens)
	 */
	public Locatiehouder(String emailadres,  String gebruikersnaam, String wachtwoord, String bedrijfsnaam, String adres, String btwNummer, String naam, String voornaam, String geslacht, String gsm){
		super(gebruikersnaam, emailadres, wachtwoord, naam, voornaam, geslacht, gsm);
		setupInitConfig();
		this.btwNummer = btwNummer;
		this.bedrijfsnaam = bedrijfsnaam;
		this.adres = adres;
	}

	/* //----------------// -#########--------------------#########- //----------------// */
	/* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
	/* //----------------// -#########--------------------#########- //----------------// */

	/* //----------------\\ # ------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Domein # //----------------\\ */
	/* //----------------\\ # ------------------------- # //----------------\\ */
	/**
	 * Deze domein-functie maakt een locatietoestemming aan (als l.houder naar planner toe)
	 * en voegt die locatietoestemming toe aan de lijst met actieve locatietoestemmingen via deze persoon.
	 * (2 signaturen -> arg om een locatietoestemming te maken OF een al aangemaakte locatietoestemming)
	 */
	public Locatietoestemming maakLocatietoestemming(int aantalLaadpalen, String typeLaadpaal){
		Locatietoestemming aangemaakteLoctoest = new Locatietoestemming(aantalLaadpalen, typeLaadpaal, "Aangemaakt");
		actieveLocatietoestemmingen.add(aangemaakteLoctoest);
		return aangemaakteLoctoest;
	}
	public Locatietoestemming maakLocatietoestemming(Locatietoestemming aangemaakteLoctoest){
		actieveLocatietoestemmingen.add(aangemaakteLoctoest);
		return aangemaakteLoctoest;
	}

	/**
	 * Deze domein-functie maakt een probleem aan (als locatiehouder naar planner toe)
	 * voegt dat probleem toe aan de lijst met actieve problemen via deze persoon.
	 * (2 signaturen -> arg om een probleem te maken OF een al aangemaakt probleem)
	 */
	public Probleem maakProbleem(Laadpaal defecteLaadpaal, String probBeschrijving){
		Probleem createdProbleem = new Probleem(defecteLaadpaal, probBeschrijving);
		actieveProblemen.add(createdProbleem);
		return createdProbleem;
	}
	public Probleem maakProbleem(Probleem probleem){
		actieveProblemen.add(probleem);
		return probleem;
	}

	/**
	 * Deze domein-functie identificeert resterende attributen bij registratie zonder persoonsgegevens.
	 */
	public PersoonExtended identificeer(String bedrijfsnaam, String adres, String btwNummer, String naam, String voornaam, String geslacht, String gsm){
		this.bedrijfsnaam = bedrijfsnaam;
		this.adres = adres;
		this.btwNummer = btwNummer;
		this.naam = naam;
		this.voornaam = voornaam;
		this.geslacht = geslacht;
		this.gsm = gsm;
		return this;
	}


	/* //----------------\\ # ----------------- # //----------------\\ */
	/* //----------------\\ # Functie Technisch # //----------------\\ */
	/* //----------------\\ # ----------------- # //----------------\\ */

	/**
	 * Deze technische functie zet deze instantie over van de actieve- naar de gearchiveerde arraylist.
	 */
	public void archiveer(){
		gearchiveerdeLocatiehouders.add(this);
		actieveLocatiehouders.remove(this);
	}

	/**
	 * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
	 */
	private void setupInitConfig(){
		actieveLocatiehouders.add(this);
		klasseDao = (BaseDao) AppRunner.getAppContext().getBean(AfspraakHibernateDao.BEAN_DAO_NAME);
		this.id = (klasseDao.isTableEmpty()) ? (ID_PREFIX_PERSOON + "0") : extrapolateId();
		this.parentPersoon = this;
	}

	/**
	 * Deze technische functie leidt het id af via het laatste record in de tabel.
	 */
	private String extrapolateId(){
		return klasseDao.getLastItemId();
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
	//@Id @Column(name = ID_COL_NAME)
	@Transient
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

	/* //----------------// PROPERTY: Persoon-Adres //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het Wachtwoord-attribuut van deze instantie.
	 */
	@Column(name = ADRES_COL_NAME)
	public String getAdres() { return adres; }
	/**
	 * Deze domein-attribuut-setter vertegenwoordigt het Wachtwoord-attribuut van deze instantie.
	 */
	@Transient
	public void setAdres(String value) { adres = value; }

	/* //----------------// PROPERTY: Persoon-BTWNummer //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het Wachtwoord-attribuut van deze instantie.
	 */
	@Column(name = BTWNUMMER_COL_NAME)
	public String getBTWNummer() { return btwNummer; }
	/**
	 * Deze domein-attribuut-setter vertegenwoordigt het Wachtwoord-attribuut van deze instantie.
	 */
	@Transient
	public void setBTWNummer(String value) { btwNummer = value; }

	/* //----------------// PROPERTY: Persoon-Bedrijfsnaam //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het Wachtwoord-attribuut van deze instantie.
	 */
	@Column(name = BEDRIJFSNAAM_COL_NAME)
	public String getBedrijfsnaam() { return bedrijfsnaam; }
	/**
	 * Deze domein-attribuut-setter vertegenwoordigt het Wachtwoord-attribuut van deze instantie.
	 */
	@Transient
	public void setBedrijfsnaam(String value) { bedrijfsnaam = value; }

	/* //----------------// PROPERTY: Persoon-FK //----------------// */
	/**
	 * Deze domein-attribuut-getter vertegenwoordigt het id-attribuut van deze instantie.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name=PERSOON_COL_NAME, referencedColumnName = PersoonImpl.ID_COL_NAME_PERSOON)
	public PersoonImpl getPersoon(){
		return this.parentPersoon;
	}
	/**
	 * Deze domein-attribuut-setter vertegenwoordigt het id-attribuut van deze instantie.
	 */
	@Transient
	public void setPersoon(PersoonImpl value){
		this.parentPersoon = value;
	}

	/* //----------------\\ # ------------------- # //----------------\\ */
	/* //----------------\\ # Property Technische # //----------------\\ */
	/* //----------------\\ # ------------------- # //----------------\\ */

	/* //----------------// PROPERTY: Actieve & Gearchiveerde LOCATIEHOUDERS //----------------// */
	/**
	 * (ACT-LOCATIEHOUDERS) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. actieve installateurs.
	 */
	@Transient
	public static ArrayList<PersoonExtended> getActieveInstanties() { return actieveLocatiehouders; }
	/**
	 * (ARCH-LOCATIEHOUDERS) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. gearchiveerde installateurs.
	 */
	@Transient
	public static ArrayList<PersoonExtended> getGearchiveerdeInstanties() { return gearchiveerdeLocatiehouders; }

	/* //----------------// PROPERTY: Actieve & Gearchiveerde PROBLEMEN //----------------// */
	/**
	 * (ACT-PROBLEMEN) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. actieve installateurs.
	 */
	@Transient
	public static ArrayList<Probleem> getActieveProblemen() { return actieveProblemen; }
	/**
	 * (ARCH-PROBLEMEN) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. gearchiveerde installateurs.
	 */
	@Transient
	public static ArrayList<Probleem> getGearchiveerdeProblemen() { return gearchiveerdeProblemen; }

	/* //----------------// PROPERTY: Actieve & Gearchiveerde LOCATIETOESTEMMINGEN //----------------// */
	/**
	 * (ACT-LOCATIETOESTEMMINGEN) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. actieve installateurs.
	 */
	@Transient
	public static ArrayList<Locatietoestemming> getActieveLocatietoestemmingen() { return actieveLocatietoestemmingen; }
	/**
	 * (ARCH-LOCATIETOESTEMMINGEN) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. gearchiveerde installateurs.
	 */
	@Transient
	public static ArrayList<Locatietoestemming> getGearchiveerdeLocatietoestemmingen() { return gearchiveerdeLocatietoestemmingen; }
}