package be.fastned.application.domain;

import be.fastned.application.domain.custom.ArrayListExtended;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */
@Entity(name = "Oplossing")
@Table(name = "Oplossingen")
public class Oplossing {

	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long m_Id;
	@Column
	private String m_OplossingStr;

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */

	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */
	@Transient
	/**
	 * Collectie van actieve & nieuwe Oplossingen. (data-bron voor schermen) */
	public static ArrayListExtended<Oplossing, Oplossing> s_ActiveOplossingen = new ArrayListExtended<Oplossing, Oplossing>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Oplossingen. (repository voor rollback) */
	public static ArrayList<Oplossing> s_ArchivedOplossingOplossing = new ArrayList<Oplossing>();

	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/**
	 * Default Constructor voor deze klasse. */
	public Oplossing(){
		s_ActiveOplossingen.add(this);
	}

	/**
	 * Volledige Constructor voor deze klasse. */
	public Oplossing(String oplossingStr){
		m_OplossingStr = oplossingStr;
		s_ActiveOplossingen.add(this);
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
		this.s_ActiveOplossingen.removeWrapped(Oplossing.class, Oplossing.class, true);
	}

	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// PROPERTY: OplossingStr //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de OplossingStr. */
	public void setOplossingStr(String value){
		this.m_OplossingStr = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de OplossingStr. */
	public String getOplossingStr(){
		return this.m_OplossingStr;
	}
}