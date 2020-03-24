package be.fastned.application.domain;

import be.fastned.application.domain.custom.ArrayListExtended;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */
@Entity(name = "Planner")
@Table(name = "Planners")
public class Planner extends PersoonImpl {

	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long m_Id;
	@Transient
	/**
	 * Collectie van actieve & nieuwe Afspraken. (data-bron voor schermen) */
	public ArrayListExtended<Planner, Afspraak> ActiveAfspraken = new ArrayListExtended<Planner, Afspraak>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Afspraken. (repository voor rollback) */
	public ArrayList<Afspraak> ArchivedPlannerAfspraak = new ArrayList<Afspraak>();

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */

	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */
	@Transient
	/**
	 * Collectie van actieve & nieuwe Planners. (data-bron voor schermen) */
	public static ArrayListExtended<Planner, Planner> s_ActivePlanners = new ArrayListExtended<Planner, Planner>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Planners. (repository voor rollback) */
	public static ArrayList<Planner> s_ArchivedPlannerPlanner = new ArrayList<Planner>();

	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/**
	 * Default Constructor voor deze klasse. */
	public Planner(){
		s_ActivePlanners.add(this);
	}
	/**
	 * Volledige Constructor voor deze klasse. */
	public Planner(String gebruikersnaam, String wachtwoord, String naam, String voornaam, String geslacht, String emailadres, String gsm){
		super(gebruikersnaam, wachtwoord, naam, voornaam, geslacht, emailadres, gsm);
		s_ActivePlanners.add(this);
	}

	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Functies //----------------// */
	/**
	 * Deze domein-functie retourneert alle locatietoestemmingen. */
	public ArrayList<Locatietoestemming> toonLocatieToestemmingen(){
		ArrayList<Locatietoestemming> list = new ArrayList<Locatietoestemming>();
		Locatietoestemming.s_ActiveLocatietoestemmingen.forEach(item -> list.add((Locatietoestemming) item));
		return list;
	}

	/**
	 * Deze domein-functie maakt een afspraak via een planner. */
	public Afspraak maakAfspraak(Laadpaal laadpaal, Installateur installateur, Contract contract){
		Afspraak afspraak = new Afspraak(laadpaal, installateur, contract);
		ActiveAfspraken.add(afspraak);
		return afspraak;
	}
	public Afspraak maakAfspraak(ArrayList<Laadpaal> laadpalen, Installateur installateur, Contract contract){
		Afspraak afspraak = new Afspraak(laadpalen, installateur, contract);
		ActiveAfspraken.add(afspraak);
		return afspraak;
	}

	/**
	 * Deze domein-functie retourneert 1 of alle Probleem-object(en) binnen een Afspraak-object. */
	public ArrayList<Probleem> toonProblemen(Afspraak afspraak){
		Probleem installatieProblemen = afspraak.getInstallatie().getProbleem();
		Probleem reparatieProblemen = afspraak.getReparatie().getProbleem();

		ArrayList alleProblemen = new ArrayList();
		alleProblemen.add(installatieProblemen);
		alleProblemen.add(reparatieProblemen);

		return alleProblemen;
	}
	public Probleem toonInstallatieProbleem(Afspraak afspraak){
		return afspraak.getInstallatie().getProbleem();
	}
	public Probleem toonReparatieProbleem(Afspraak afspraak){
		return afspraak.getReparatie().getProbleem();
	}

	/**
	 * Deze domein-functie updated de status van een probleem-object. */
	public void evalueerProbleem(Probleem probleem, String status){
		probleem.setStatus(status);
	}

	/**
	 * Deze domein-functie updated de status van een probleem-object naar de default "afgesloten"-waarde. */
	public void sluitProbleem(Probleem probleem){
		probleem.setStatus("afgesloten");
	}

	/**
	 * Deze domein-functie updated de status van een locatietoestemming-object. */
	public void evalueerAanmelding(Locatietoestemming aanmelding, String status){
		aanmelding.setStatus(status);
	}

	/**
	 * Deze domein-functie maakt een contract in een afspraak via een planner. */
	public Contract maakContract(LocalDateTime contractDatum, LocalDateTime installatieDatum, Afspraak parentAfspraak){
		Contract contract = new Contract(contractDatum, installatieDatum);
		parentAfspraak.setContract(contract);
		return contract;
	}

	/* //----------------// SECTIE: Technische-Functies //----------------// */
	/**
	 * Deze Domein-functie schrijft een deze instantie over van de Active-ArrayList naar de Archived-ArrayList.
	 * Dit via klasse "ArrayListExtended" via naamgeving "s_ArchivedKlasseItemKlasse" of dit zonder "s_". */
	public void archiveer(){
		this.s_ActivePlanners.removeWrapped(Planner.class, Planner.class, true);
	}

	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
	/* //----------------// -#####- |------------| -#####- //----------------// */
}