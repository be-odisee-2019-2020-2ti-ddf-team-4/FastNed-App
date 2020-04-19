package be.fastned.application.domain.PersoonEntiteiten;

import be.fastned.application.dao.AfspraakHibernateDao;
import be.fastned.application.dao.Base.BaseDao;
import be.fastned.application.domain.AndereEntiteiten.Laadpaal;
import be.fastned.application.domain.AndereEntiteiten.Probleem;
import be.fastned.application.domain.Base.Entiteit;
import be.fastned.application.service.AppRunner;
import javax.persistence.*;
import java.util.ArrayList;
import static be.fastned.application.domain.PersoonEntiteiten.Laadklant.ENTITY_NAME;
import static be.fastned.application.domain.PersoonEntiteiten.Laadklant.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Laadklant extends PersoonImpl implements PersoonDefault, Entiteit {

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

	/* //----------------// SECTIE: Problemen //----------------// */
	/**
	 * (ACT-PROBLEMEN) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<Probleem> actieveProblemen = new ArrayList<Probleem>();
	/**
	 * (ARCH-PROBLEMEN) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<Probleem> gearchiveerdeProblemen = new ArrayList<Probleem>();


	/* //----------------// -##########-----------------------------##########- //----------------// */
	/* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
	/* //----------------// -##########-----------------------------##########- //----------------// */

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Laadklant";
	public static final String TABLE_NAME = "tbl_laadklanten";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = LAADKLANT_ID_PREFIX;

	// Constanten met kolom-namen
	private static final String ID_COL_NAME = ID_PREFIX + "ID";
	public static final String PERSOON_COL_NAME = "Persoon_FK";

	/* //----------------// SECTIE: Laadklanten //----------------// */
	/**
	 * (ACT-LAADKLANTEN) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<PersoonDefault> actieveLaadklanten = new ArrayList<PersoonDefault>();
	/**
	 * (ARCH-LAADKLANTEN) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<PersoonDefault> gearchiveerdeLaadklanten = new ArrayList<PersoonDefault>();

	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */

	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Laadklant(){
		super();
		setupInitConfig();
	}

	/**
	 * Default constructor voor deze klasse. (Optionele Configuratie)
	 */
	public Laadklant(boolean noConfig){
		super(noConfig);
		if (!noConfig) { setupInitConfig(); }
	}

	/**
	 * Basische constructor voor deze klasse. (enkel accountgegevens)
	 */
	public Laadklant(String gebruikersnaam, String emailadres, String wachtwoord) {
		super(gebruikersnaam, emailadres, wachtwoord);
		setupInitConfig();
	}
	/**
	 * Volledige constructor voor deze klasse. (accountgegevens + persoonsgegevens)
	 */
	public Laadklant(String gebruikersnaam, String emailadres, String wachtwoord, String naam, String voornaam, String geslacht, String gsm ){
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
	 * Deze domein-functie maakt een probleem aan (als laadklant naar planner toe)
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
	public PersoonDefault identificeer(String naam, String voornaam, String geslacht, String gsm){
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
		gearchiveerdeLaadklanten.add(this);
		actieveLaadklanten.remove(this);
	}

	/**
	 * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
	 */
	private void setupInitConfig(){
		actieveLaadklanten.add(this);
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

	/* //----------------// PROPERTY: Actieve & Gearchiveerde LAADKLANTEN //----------------// */
	/**
	 * (ACT-LAADKLANTEN) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. actieve installateurs.
	 */
	@Transient
	public static ArrayList<PersoonDefault> getActieveInstanties() { return actieveLaadklanten; }
	/**
	 * (ARCH-LAADKLANTEN) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. gearchiveerde installateurs.
	 */
	@Transient
	public static ArrayList<PersoonDefault> getGearchiveerdeInstanties() { return gearchiveerdeLaadklanten; }

	/* //----------------// PROPERTY: Actieve & Gearchiveerde aangemaakte PROBLEMEN //----------------// */
	/**
	 * (ACT-PROBLEMEN) Deze domein-attribuut-setter vertegenwoordigt de coll. v.d. actieve PROBLEMEN.
	 */
	@Transient
	public static ArrayList<Probleem> getActieveProblemen() { return actieveProblemen; }
	/**
	 * (ARCH-PROBLEMEN) Deze domein-attribuut-setter vertegenwoordigt de coll. v.d. gearchiveerde PROBLEMEN.
	 */
	@Transient
	public static ArrayList<Probleem> getGearchiveerdeProblemen() { return gearchiveerdeProblemen; }
}