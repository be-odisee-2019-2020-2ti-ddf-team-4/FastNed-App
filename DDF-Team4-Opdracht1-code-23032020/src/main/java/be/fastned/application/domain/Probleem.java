package be.fastned.application.domain;

import be.fastned.application.domain.custom.ArrayListExtended;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */
@Entity(name = "Probleem")
@Table(name = "Problemen")
public class Probleem {
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long m_Id;
	@Column
	private String m_Beschrijving = "";
	@Column
	private String m_Status = "";
	@OneToOne(
			mappedBy = "Problemen",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	private Oplossing m_Oplossing = null;

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */

	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */
	@Transient
	/**
	 * Collectie van actieve & nieuwe Problemen. (data-bron voor schermen) */
	public static ArrayListExtended<Probleem, Probleem> s_ActiveProblemen = new ArrayListExtended<Probleem, Probleem>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Problemen. (repository voor rollback) */
	public static ArrayList<Probleem> s_ArchivedProbleemProbleem = new ArrayList<Probleem>();

	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/**
	 * Default Constructor voor deze klasse. */
	public Probleem(){
		s_ActiveProblemen.add(this);
	}
	/**
	 * Volledige Constructor voor deze klasse. */
	public Probleem(Laadpaal laadpaal, String beschrijving){
		m_Beschrijving = beschrijving;
		m_Status = "aangemaakt";
		s_ActiveProblemen.add(this);
	}

	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Functies //----------------// */
	/* //----------------// SECTIE: Technische-Functies //----------------// */
	/**
	 * Deze Domein-functie schrijft een deze instantie over van de Active-ArrayList naar de Archived-ArrayList.
	 * Dit via klasse "ArrayListExtended" via naamgeving "s_ArchivedKlasseItemKlasse" of dit zonder "s_". */
	public void archiveer(){
		this.s_ActiveProblemen.removeWrapped(Probleem.class, Probleem.class, true);
	}

	/**
	 * Deze domein-functie voeg een Oplossing toe via dit Probleem. */
	public void voegOplossingToe(String beschrijving){
		m_Oplossing = new Oplossing(beschrijving);
	}

	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// PROPERTY: Status //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Status. */
	public void setStatus(String value){
		this.m_Status = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Status. */
	public String getStatus(){
		return this.m_Status;
	}

	/* //----------------// PROPERTY: Beschrijving //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Beschrijving. */
	public void setBeschrijving(String value){
		m_Beschrijving = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Beschrijving. */
	public String getBeschrijving(){
		return m_Beschrijving;
	}

	/* //----------------// PROPERTY: Oplossing //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Oplossing. */
	public void setOplossing(Oplossing value){
		this.m_Oplossing = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Oplossing. */
	public Oplossing getOplossing(){
		return this.m_Oplossing;
	}
}