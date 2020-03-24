package be.fastned.application.domain;

import be.fastned.application.domain.custom.ArrayListExtended;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:53
 */
@Entity(name = "Laadsessie")
@Table(name = "Laadsessies")
public class Laadsessie {

	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long m_Id;
	@Column
	private LocalDateTime m_StartSessie;
	@Column
	private double m_StartPercentage;
	@OneToOne(
			mappedBy = "Laadsessies",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	public Laadpaal m_Laadpaal;

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */

	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |-------------------| -#####- //----------------// */

	/* //----------------// SECTIE: Domein-Variabelen //----------------// */

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */
	@Transient
	private final double LAADPERCENT_PER_MINUUT = 1.5;
	@Transient
	/**
	 * Collectie van actieve & nieuwe Laadsessies. (data-bron voor schermen) */
	public static ArrayListExtended<Laadsessie, Laadsessie> s_ActiveLaadsessies = new ArrayListExtended<Laadsessie, Laadsessie>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Laadsessies. (repository voor rollback) */
	public static ArrayList<Laadsessie> s_ArchivedLaadsessieLaadsessie = new ArrayList<Laadsessie>();

	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/**
	 * Default Constructor voor deze klasse. */
	public Laadsessie(){
		this.s_ActiveLaadsessies.add(this);
	}
	/**
	 * Volledige Constructor voor deze klasse. */
	public Laadsessie(LocalDateTime startSessie, double startPercentage, Laadpaal laadpaal){
		m_StartSessie = startSessie;
		m_StartPercentage = startPercentage;
		m_Laadpaal = laadpaal;
		this.s_ActiveLaadsessies.add(this);
	}

	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Functies //----------------// */
	public double berekenLaadtijd(){
		return ((100 - m_StartPercentage)/this.LAADPERCENT_PER_MINUUT);
	}

	/* //----------------// SECTIE: Technische-Functies //----------------// */
	/**
	 * Deze Domein-functie schrijft een deze instantie over van de Active-ArrayList naar de Archived-ArrayList.
	 * Dit via klasse "ArrayListExtended" via naamgeving "s_ArchivedKlasseItemKlasse" of dit zonder "s_". */
	public void archiveer(){
		this.s_ActiveLaadsessies.removeWrapped(Laadsessie.class, Laadsessie.class, true);
	}

	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// PROPERTY: Start-Sessie //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Start-Sessie. */
	public void setStartSessie(LocalDateTime value){
		this.m_StartSessie = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Start-Sessie. */
	public LocalDateTime getStartSessie(){
		return this.m_StartSessie;
	}

	/* //----------------// PROPERTY: Start-Percentage //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Start-Percentage. */
	public void setStartPercentage(double value){
		this.m_StartPercentage = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Start-Percentage. */
	public double getStartPercentage(){
		return this.m_StartPercentage;
	}

	/* //----------------// PROPERTY: Laadpaal //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Laadpaal. */
	public void setLaadpaal(Laadpaal value){
		this.m_Laadpaal = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Laadpaal. */
	public Laadpaal getLaadpaal(){
		return this.m_Laadpaal;
	}
}