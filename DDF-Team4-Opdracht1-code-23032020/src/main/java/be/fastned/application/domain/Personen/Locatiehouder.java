package be.fastned.application.domain.Personen;

import be.fastned.application.dao.AfspraakHibernateDao;
import be.fastned.application.dao.Interfaces.BaseDao;
import be.fastned.application.domain.Laadpaal;
import be.fastned.application.domain.Locatietoestemming;
import be.fastned.application.domain.Oplossing;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonExtension;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonProfessional;
import be.fastned.application.domain.PersoonAbstracties.PersoonProfessionalImpl;
import be.fastned.application.domain.Probleem;
import be.fastned.application.service.AppRunner;

import javax.persistence.*;
import java.util.ArrayList;
import static be.fastned.application.domain.Personen.Locatiehouder.ENTITY_NAME;
import static be.fastned.application.domain.Personen.Locatiehouder.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Locatiehouder extends PersoonProfessionalImpl {

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
	public static final String ID_COL_NAME = ID_PREFIX + "Id";

	/* //----------------// SECTIE: Locatiehouders //----------------// */
	/**
	 * (ACT-LOCATIEHOUDERS) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<PersoonProfessional> actieveLocatiehouders = new ArrayList<PersoonProfessional>();
	/**
	 * (ARCH-LOCATIEHOUDERS) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<PersoonProfessional> gearchiveerdeLocatiehouders = new ArrayList<PersoonProfessional>();

	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */

	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Locatiehouder(){
		setupInitConfig();
		id = extrapolateId();
	}

	/**
	 * Default constructor voor deze klasse. (Geen configuratie)
	 */
	public Locatiehouder(boolean noConfig){
		if (!noConfig) { setupInitConfig(); }
		id = extrapolateId();
	}

	/**
	 * Basische constructor voor deze klasse. (enkel accountgegevens)
	 */
	public Locatiehouder(String emailadres, String gebruikersnaam, String wachtwoord){
		super(emailadres, gebruikersnaam, wachtwoord);
		setupInitConfig();
		id = extrapolateId();
	}
	/**
	 * Volledige constructor voor deze klasse. (accountgegevens + persoonsgegevens)
	 */
	public Locatiehouder(String emailadres,  String gebruikersnaam, String wachtwoord, String bedrijfsnaam, String adres, String btwNummer, String naam, String voornaam, String geslacht, String gsm){
		super(emailadres, gebruikersnaam, wachtwoord, adres, bedrijfsnaam, btwNummer, naam, voornaam, geslacht, gsm);
		setupInitConfig();
		id = extrapolateId();
	}

	/* //----------------// -#########--------------------#########- //----------------// */
	/* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
	/* //----------------// -#########--------------------#########- //----------------// */

	/* //----------------\\ # ------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Domein Variabelen # //----------------\\ */
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


	/* //----------------\\ # ---------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Technisch Variabelen # //----------------\\ */
	/* //----------------\\ # ---------------------------- # //----------------\\ */

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

	/* //----------------// PROPERTY: Actieve & Gearchiveerde LOCATIEHOUDERS //----------------// */
	/**
	 * (ACT-LOCATIEHOUDERS) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. actieve installateurs.
	 */
	@Transient
	public static ArrayList<PersoonProfessional> getActieveInstanties() { return actieveLocatiehouders; }
	/**
	 * (ARCH-LOCATIEHOUDERS) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. gearchiveerde installateurs.
	 */
	@Transient
	public static ArrayList<PersoonProfessional> getGearchiveerdeInstanties() { return gearchiveerdeLocatiehouders; }

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