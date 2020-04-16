package be.fastned.application.domain.Personen;

import be.fastned.application.dao.Base.BaseDao;
import be.fastned.application.dao.Andere.PlannerHibernateDao;
import be.fastned.application.domain.Andere.*;
import be.fastned.application.domain.Base.Entiteit;
import be.fastned.application.service.AppRunner;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static be.fastned.application.domain.Personen.Planner.ENTITY_NAME;
import static be.fastned.application.domain.Personen.Planner.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Planner extends PersoonImpl implements PersoonDefault, Entiteit {

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
	private PersoonImpl parentPersoon;

	/* //----------------\\ # ------------------------------- # //----------------\\ */
	/* //----------------\\ # Instantie Technische Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------------- # //----------------\\ */

	/* //----------------// SECTIE: Afspraken //----------------// */
	/**
	 * (ACT-AFSPRAKEN) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<Afspraak> actieveAfspraken = new ArrayList<Afspraak>();
	/**
	 * (ARCH-AFSPRAKEN) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<Afspraak> gearchiveerdeAfspraken = new ArrayList<Afspraak>();

	/* //----------------// -##########-----------------------------##########- //----------------// */
	/* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########-----------------------------##########- //----------------// */

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Planner";
	public static final String TABLE_NAME = "tbl_planners";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = PLANNER_ID_PREFIX;

	// Constanten met kolom-namen
	private static final String ID_COL_NAME = ID_PREFIX + "ID";
	public static final String PERSOON_COL_NAME = "Persoon_FK";

	/* //----------------// SECTIE: Planners //----------------// */
	/**
	 * (ACT-PLANNERS) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<PersoonDefault> actievePlanners = new ArrayList<PersoonDefault>();
	/**
	 * (ARCH-PLANNERS) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<PersoonDefault> gearchiveerdePlanners = new ArrayList<PersoonDefault>();

	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */

	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Planner(){
		super();
		setupInitConfig();
	}

	/**
	 * Default constructor voor deze klasse. (Optionele Configuratie)
	 */
	public Planner(boolean noConfig){
		super(noConfig);
		if (!noConfig) { setupInitConfig(); }
	}

	/**
	 * Basische constructor voor deze klasse. (enkel accountgegevens)
	 */
	public Planner(String gebruikersnaam, String emailadres, String wachtwoord) {
		super(gebruikersnaam, emailadres, wachtwoord);
		setupInitConfig();
	}

	/**
	 * Volledige constructor voor deze klasse. (accountgegevens + persoonsgegevens)
	 */
	public Planner(String gebruikersnaam, String emailadres, String wachtwoord, String naam, String voornaam, String geslacht, String gsm ){
		super(gebruikersnaam, emailadres, wachtwoord, naam, voornaam, geslacht, gsm);
		setupInitConfig();
	}

	/* //----------------// -#########--------------------#########- //----------------// */
	/* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
	/* //----------------// -#########--------------------#########- //----------------// */

	/* //----------------\\ # ------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Domein # //----------------\\ */
	/* //----------------\\ # ------------------------- # //----------------\\ */
	/**
	 * Deze domein-functie retourneert alle locatietoestemmingen
	 */
	public ArrayList<Locatietoestemming> toonLocatieToestemmingen(){
		ArrayList<Locatietoestemming> tussenStap = new ArrayList<Locatietoestemming>();
		Locatietoestemming.actieveLocatietoestemmingen.forEach(item -> tussenStap.add((Locatietoestemming) item));
		return tussenStap;
	}

	/**
	 * Deze domein-functie maakt een afspraak aan (als planner) en
	 * voegt die afspraak toe aan de lijst met actieve afspraken via deze persoon.
	 * (5 signaturen -> arg om een afspraak te maken OF dat met laadpalen OF een al aangemaakte afspraak + 2x no contract)
	 */
	public Afspraak maakAfspraak(Afspraak aangemaakteAfspraak){
		actieveAfspraken.add(aangemaakteAfspraak);
		return aangemaakteAfspraak;
	}
	public Afspraak maakAfspraak(Laadpaal laadpaal, Installateur installateur){
		Afspraak afspraak = new Afspraak(laadpaal, installateur, null);
		actieveAfspraken.add(afspraak);
		return afspraak;
	}

	public Afspraak maakAfspraak(Laadpaal laadpaal, Installateur installateur, Contract contract){
		Afspraak afspraak = new Afspraak(laadpaal, installateur, contract);
		actieveAfspraken.add(afspraak);
		return afspraak;
	}

	/**
	 * (PROBLEMEN) Deze domein-functie retourneert alle Problemen, ingezonden door laadklanten of locatiehouders.
	 */
	public ArrayList<Probleem> toonProblemen(){
		ArrayList<Probleem> tussenStap = new ArrayList<Probleem>();
		Locatiehouder.getActieveProblemen().forEach(item -> tussenStap.add((Probleem) item));
		Laadklant.getActieveProblemen().forEach(item -> tussenStap.add((Probleem) item));
		return tussenStap;
	}

	/**
	 * (INST PROBLEEM) Deze domein-functie retourneert het probleem binnen een installatie-afspraak.
	 */
	public Probleem toonInstallatieProbleem(Afspraak afspraak){
		return afspraak.getInstallatie().getProbleem();
	}

	/**
	 * (REP PROBLEEM) Deze domein-functie retourneert het probleem binnen een reparatie-afspraak.
	 */
	public Probleem toonReparatieProbleem(Afspraak afspraak){
		return afspraak.getReparatie().getProbleem();
	}

	/**
	 * (PROB STATUS) Deze domein-functie overschrijft de status van een probleem.
	 */
	public void evalueerProbleem(Probleem probleem, String status){
		probleem.setStatus(status);
	}

	/**
	 * (SLUIT PROB) Deze domein-functie overschrijft de status van een probleem met "Gesloten".
	 */
	public void sluitProbleem(Probleem probleem){
		probleem.setStatus("Gesloten");
	}

	/**
	 * (LOCAAN STATUS) Deze domein-functie overschrijft de status van een locatietoestemming.
	 */
	public void evalueerAanmelding(Locatietoestemming aanmelding, String status){
		aanmelding.setStatus(status);
	}

	/**
	 * (CONTRACT) Deze domein-functie maakt een contract binnen een afspraak via deze persoon.
	 */
	public Contract maakContract(LocalDateTime contractDatum, LocalDateTime installatieDatum, Afspraak parentAfspraak){
		Contract aangemaaktContract = new Contract(contractDatum, installatieDatum);
		parentAfspraak.setContract(aangemaaktContract);
		return aangemaaktContract;
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

	/* //----------------\\ # ---------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Technisch # //----------------\\ */
	/* //----------------\\ # ---------------------------- # //----------------\\ */

	/**
	 * Deze technische functie zet deze instantie over van de actieve- naar de gearchiveerde arraylist.
	 */
	public void archiveer(){
		gearchiveerdePlanners.add(this);
		actievePlanners.remove(this);
	}

	/**
	 * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
	 */
	private void setupInitConfig(){
		actievePlanners.add(this);
		klasseDao = (BaseDao) AppRunner.getAppContext().getBean(PlannerHibernateDao.BEAN_DAO_NAME);
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

	/* //----------------// PROPERTY: Actieve & Gearchiveerde Planners //----------------// */
	/**
	 * (ACT-PLANNERS) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. actieve instanties.
	 */
	@Transient
	public static ArrayList<PersoonDefault> getActieveInstanties() { return actievePlanners; }
	/**
	 * (ARCH-PLANNERS) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. gearchiveerde instanties.
	 */
	@Transient
	public static ArrayList<PersoonDefault> getGearchiveerdeInstanties() { return gearchiveerdePlanners; }
}