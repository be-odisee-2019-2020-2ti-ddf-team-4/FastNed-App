package be.fastned.application.domain;

import be.fastned.application.domain.custom.ArrayListExtended;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */
@Entity(name = "Locatiehouder")
@Table(name = "Locatiehouders")
public class Locatiehouder extends PersoonImpl {

	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long m_Id;
	@Column
	private String m_Bedrijfsnaam;
	@Column
	private String m_Adres;
	@Column
	private String m_BTWNummer;

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */
	@Transient
	/**
	 * Collectie van actieve & nieuwe Locatietoestemmingen. (data-bron voor schermen) */
	public ArrayListExtended<Locatiehouder, Locatiehouder> ActiveLocatietoestemmingen = new ArrayListExtended<Locatiehouder, Locatiehouder>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Locatietoestemmingen. (repository voor rollback) */
	public ArrayList<Locatiehouder> ArchivedLocatiehouderLocatietoestemming = new ArrayList<Locatiehouder>();

	@Transient
	/**
	 * Collectie van actieve & nieuwe Problemen. (data-bron voor schermen) */
	public ArrayListExtended<Locatiehouder, Probleem> ActiveProblemen = new ArrayListExtended<Locatiehouder, Probleem>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Problemen. (repository voor rollback) */
	public ArrayList<Probleem> ArchivedLocatiehouderProbleem = new ArrayList<Probleem>();

	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */
	/**
	 * Collectie van actieve & nieuwe Locatiehouders. (data-bron voor schermen) */
	public static ArrayListExtended<Locatiehouder, Locatiehouder> s_ActiveLocatiehouders = new ArrayListExtended<Locatiehouder, Locatiehouder>();
	/**
	 * Collectie van verlopen & afgehandelde Locatiehouders. (repository voor rollback) */
	public static ArrayList<Locatiehouder> s_ArchivedLocatiehouderLocatiehouder = new ArrayList<Locatiehouder>();


	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/**
	 * Default Constructor voor deze klasse.
	 */
	public Locatiehouder(){
		s_ActiveLocatiehouders.add(this);
	}
	/**
	 * Volledige Constructor voor deze klasse. */
	public Locatiehouder(String gebruikersnaam, String wachtwoord, String naam, String voornaam, String geslacht, String emailadres, String gsm, String bedrijfsnaam, String adres, String btwNummer){
		super(gebruikersnaam, wachtwoord, naam, voornaam, geslacht, emailadres, gsm);
		m_Bedrijfsnaam = bedrijfsnaam;
		m_Adres = adres;
		m_BTWNummer = btwNummer;
		s_ActiveLocatiehouders.add(this);
	}

	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Functies //----------------// */
	/**
	 * Deze domein-functie maakt een locatie-toestemming/-aanmelding aan via een Locatiehouder. */
	public Locatietoestemming meldLocatieAan(int aantalLaadpalen, String typeLaadpaal){
		Locatietoestemming createdLocatietoestemming = new Locatietoestemming(aantalLaadpalen, typeLaadpaal, "Aangemaakt");
		ActiveLocatietoestemmingen.add(createdLocatietoestemming);
		return createdLocatietoestemming;
	}
	/**
	 * Deze domein-functie maakt een probleem aan via een Laadklant. */
	public Probleem meldProbleem(Laadpaal laadpaal, String beschrijving){
		Probleem createdProbleem = new Probleem(laadpaal, beschrijving);
		ActiveProblemen.add(createdProbleem);
		return createdProbleem;
	}
	public Probleem meldProbleem(Probleem probleem){
		ActiveProblemen.add(probleem);
		return probleem;
	}


	/* //----------------// SECTIE: Technische-Functies //----------------// */
	/**
	 * Deze Domein-functie schrijft een deze instantie over van de Active-ArrayList naar de Archived-ArrayList.
	 * Dit via klasse "ArrayListExtended" via naamgeving "s_ArchivedKlasseItemKlasse" of dit zonder "s_". */
	public void archiveer(){
		this.s_ActiveLocatiehouders.removeWrapped(Locatiehouder.class, Locatiehouder.class, true);
	}

	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// PROPERTY: Bedrijfsnaam //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Bedrijfsnaam. */
	public void setBedrijfsnaam(String value){
		this.m_Bedrijfsnaam = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Bedrijfsnaam. */
	public String getBedrijfsnaam(){
		return this.m_Bedrijfsnaam;
	}

	/* //----------------// PROPERTY: Adres //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Adres. */
	public void setAdres(String value){
		this.m_Adres = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Adres. */
	public String getAdres(){
		return this.m_Adres;
	}

	/* //----------------// PROPERTY: BTW-Nummer //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de BTW-Nummer. */
	public void setBTWNummer(String value){
		this.m_BTWNummer = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de BTW-Nummer. */
	public String getBTWNummer(){
		return this.m_BTWNummer;
	}

}