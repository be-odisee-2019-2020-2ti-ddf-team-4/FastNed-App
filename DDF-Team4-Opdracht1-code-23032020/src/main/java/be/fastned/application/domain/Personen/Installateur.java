package be.fastned.application.domain.Personen;

import be.fastned.application.dao.AfspraakHibernateDao;
import be.fastned.application.dao.Interfaces.BaseDao;
import be.fastned.application.domain.Afspraak;
import be.fastned.application.domain.Laadpaal;
import be.fastned.application.domain.Oplossing;
import be.fastned.application.domain.PersoonAbstracties.PersoonDefaultImpl;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonExtension;
import be.fastned.application.domain.Probleem;
import be.fastned.application.service.AppRunner;
import javax.persistence.*;
import java.util.ArrayList;
import static be.fastned.application.domain.Personen.Installateur.ENTITY_NAME;
import static be.fastned.application.domain.Personen.Installateur.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

public class Installateur extends PersoonDefaultImpl implements PersoonExtension {

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

	/* //----------------// SECTIE: Constanten //----------------// */
	// Configureren @Table en @Entity
	public static final String ENTITY_NAME = "Installateur";
	public static final String TABLE_NAME = "tbl_Installateurs";

	// Lokale constante (id prefix) overkopieëren naar super-variabel
	public static final String ID_PREFIX = INSTALLATEUR_ID_PREFIX;

	// Constanten met kolom-namen
	public static final String ID_COL_NAME = ID_PREFIX + "Id";

	/* //----------------// SECTIE: Installateurs //----------------// */
	/**
	 * (ACT-INSTALLATEURS) Collectie van actieve & nieuwe instanties via deze klasse.
	 */
	public static ArrayList<PersoonExtension> actieveInstallateurs = new ArrayList<PersoonExtension>();
	/**
	 * (ARCH-INSTALLATEURS) Collectie van verlopen & afgehandelde instanties via deze klasse.
	 */
	public static ArrayList<PersoonExtension> gearchiveerdeInstallateurs = new ArrayList<PersoonExtension>();


	/* //----------------// -#########------------------------#########- //----------------// */
	/* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
	/* //----------------// -#########------------------------#########- //----------------// */

	/**
	 * Default constructor voor deze klasse. (Wel configuratie)
	 */
	public Installateur(){
		setupInitConfig();
		id = extrapolateId();
	}

	/**
	 * Default constructor voor deze klasse. (Geen configuratie)
	 */
	public Installateur(boolean noConfig){
		if (!noConfig) { setupInitConfig(); }
		id = extrapolateId();
	}
	/**
	 * Basische constructor voor deze klasse. (enkel accountgegevens)
	 */
	public Installateur(String emailadres, String gebruikersnaam, String wachtwoord ){
		super(emailadres, gebruikersnaam, wachtwoord);
		setupInitConfig();
		id = extrapolateId();
	}
	/**
	 * Volledige constructor voor deze klasse. (accountgegevens + persoonsgegevens)
	 */
	public Installateur(String emailadres, String gebruikersnaam, String wachtwoord, String naam, String voornaam, String geslacht, String gsm ){
		super(emailadres, gebruikersnaam, wachtwoord, naam, voornaam, geslacht, gsm);
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

	/* //----------------\\ # ---------------------------- # //----------------\\ */
	/* //----------------\\ # Functie Technisch Variabelen # //----------------\\ */
	/* //----------------\\ # ---------------------------- # //----------------\\ */

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

	/* //----------------\\ # ------------------------------ # //----------------\\ */
	/* //----------------\\ # Property Technische Variabelen # //----------------\\ */
	/* //----------------\\ # ------------------------------ # //----------------\\ */

	/* //----------------// PROPERTY: Actieve & Gearchiveerde INSTALLATEURS //----------------// */
	/**
	 * (ACT-INSTALLATEUR) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. actieve installateurs.
	 */
	@Transient
	public static ArrayList<PersoonExtension> getActieveInstanties() { return actieveInstallateurs; }
	/**
	 * (ARCH-INSTALLATEUR) Deze domein-attribuut-setter vertegenwoordigt de collectie v.d. gearchiveerde installateurs.
	 */
	@Transient
	public static ArrayList<PersoonExtension> getGearchiveerdeInstanties() { return gearchiveerdeInstallateurs; }

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