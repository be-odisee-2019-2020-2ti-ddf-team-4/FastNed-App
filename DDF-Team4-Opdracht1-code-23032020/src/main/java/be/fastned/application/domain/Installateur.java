package be.fastned.application.domain;

import be.fastned.application.domain.custom.ArrayListExtended;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */

@Entity(name = "Installateur")
@Table(name="Installateurs")
@MappedSuperclass
public class Installateur extends PersoonImpl {

	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Technische-Variabelen //----------------// */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long m_Id;

	@Transient
	/**
	 * Collectie van actieve & nieuwe Problemen. (repository voor rollback) */
	public ArrayListExtended<Probleem, Installateur> m_ActiveProblemen = new ArrayListExtended<Probleem, Installateur>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Problemen. (repository voor rollback) */
	public ArrayList<Probleem> m_ArchivedInstallateurProbleem = new ArrayList<Probleem>();
	@Transient
	/**
	 * Collectie van actieve & nieuwe Oplosingen. (data-bron voor schermen) */
	public ArrayListExtended<Oplossing, Installateur> m_ActiveOplossingen = new ArrayListExtended<Oplossing, Installateur>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Oplossingen. (repository voor rollback) */
	public ArrayList<Oplossing> m_ArchivedInstallateurOplossing = new ArrayList<Oplossing>();

	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Technische-Variabelen //----------------// */
	@Transient
	/**
	 * Collectie van actieve & nieuwe Installateurs. (data-bron voor schermen) */
	public static ArrayListExtended<Installateur, Installateur> s_ActiveInstallateurs = new ArrayListExtended<Installateur, Installateur>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Installateurs. (repository voor rollback) */
	public static ArrayList<Installateur> s_ArchivedInstallateurInstallateur = new ArrayList<Installateur>();

	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/**
	 * Default Constructor voor deze klasse.
	 */
	public Installateur(){
		s_ActiveInstallateurs.add(this);
	}

	/**
	 * Volledige Constructor voor deze klasse.
	 */
	public Installateur(String gebruikersnaam, String wachtwoord, String naam, String voornaam, String geslacht, String emailadres, String gsm ){
		super(gebruikersnaam, wachtwoord, naam, voornaam, geslacht, emailadres, gsm);
		s_ActiveInstallateurs.add(this);
	}

	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Functies //----------------// */
	/**
	 * Deze domein-functie maakt een probleem aan via een installateur en
	 * voegt deze toe aan een lijst met problemen die eigen zijn aan deze installateur.
	 */
	public Probleem maakProbleem(Laadpaal laadpaal, Afspraak afspraak, String beschrijving){
		Probleem createdProbleem = new Probleem(laadpaal, beschrijving);
		m_ActiveProblemen.add(createdProbleem);
		return createdProbleem;
	}

	/**
	 * Deze domein-functie maakt een oplossing aan via een installateur en
	 * voegt deze toe aan een lijst met oplossingen die eigen zijn aan deze installateur.
	 */
	public Oplossing maakOplossing(Probleem probleem, String oplossingStr){
		Oplossing createdOplossing = new Oplossing(oplossingStr);
		m_ActiveOplossingen.add(createdOplossing);
		return createdOplossing;
	}

	/**
	 *  Deze domein-functie updated het status-attribuut van een probleem.
	 */
	public void updateAfspraakStatus(Afspraak afspraak, String status){
		afspraak.setStatus(status);
	}

	/* //----------------// SECTIE: Technische-Functies //----------------// */
	/**
	 * Deze Domein-functie schrijft een deze instantie over van de Active-ArrayList naar de Archived-ArrayList.
	 * Dit via klasse "ArrayListExtended" via naamgeving "s_ArchivedKlasseItemKlasse" of dit zonder "s_". */
	public void archiveer(){
		this.s_ActiveInstallateurs.removeWrapped(Installateur.class, Installateur.class, true);
	}

	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
	/* //----------------// -#####- |------------| -#####- //----------------// */
}