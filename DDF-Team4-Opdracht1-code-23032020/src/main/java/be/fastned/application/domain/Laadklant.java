package be.fastned.application.domain;

import be.fastned.application.domain.custom.ArrayListExtended;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:53
 */
@Entity(name = "Laadklant")
@Table(name = "Laadklanten")
public class Laadklant extends PersoonImpl {

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
	public ArrayListExtended<Probleem, Laadklant> m_ActiveProblemen = new ArrayListExtended<Probleem, Laadklant>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Problemen. (repository voor rollback) */
	public ArrayList<Probleem> m_ArchivedLaadklantProbleem = new ArrayList<Probleem>();

	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Technische-Variabelen //----------------// */
	@Transient
	/**
	 * Collectie van actieve & nieuwe Problemen. (repository voor rollback) */
	public static ArrayListExtended<Laadklant, Laadklant> s_ActiveLaadklanten = new ArrayListExtended<Laadklant, Laadklant>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Problemen. (repository voor rollback) */
	public static ArrayList<Laadklant> s_ArchivedLaadklantLaadklant = new ArrayList<Laadklant>();

	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/**
	 * Default Constructor voor deze klasse. */
	public Laadklant(){
	}
	/**
	 * Volledige Constructor voor deze klasse. */
	public Laadklant(String gebruikersnaam, String wachtwoord, String naam, String voornaam, String geslacht, String emailadres, String gsm ){
		super(gebruikersnaam, wachtwoord, naam, voornaam, geslacht, emailadres, gsm);
	}

	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Functies //----------------// */
	/**
	 * Deze domein-functie maakt een probleem aan via een Laadklant. */
	public Probleem meldProbleem(Laadpaal laadpaal, String beschrijving){
		Probleem createdProbleem = new Probleem(laadpaal, beschrijving);
		m_ActiveProblemen.add(createdProbleem);
		return createdProbleem;
	}
	public Probleem meldProbleem(Probleem probleem){
		m_ActiveProblemen.add(probleem);
		return probleem;
	}

	/* //----------------// SECTIE: Technische-Functies //----------------// */
	/**
	 * Deze Domein-functie schrijft een deze instantie over van de Active-ArrayList naar de Archived-ArrayList.
	 * Dit via klasse "ArrayListExtended" via naamgeving "s_ArchivedKlasseItemKlasse" of dit zonder "s_". */
	public void archiveer(){
		this.s_ActiveLaadklanten.removeWrapped(Laadklant.class, Laadklant.class, true);
	}

	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
	/* //----------------// -#####- |------------| -#####- //----------------// */

}