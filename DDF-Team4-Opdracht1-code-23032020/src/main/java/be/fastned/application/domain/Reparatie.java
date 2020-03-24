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
@Entity(name = "Reparatie")
@Table(name = "Reparaties")
public class Reparatie {
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long m_Id;
	@Column
	private LocalDateTime m_ReparatieBeeïndigd;
	@OneToOne(
			mappedBy = "Reparaties",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	public Probleem m_Probleem;

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */

	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */
	@Transient
	public static DocumentatieRepository s_Repo = DocumentatieRepository.getInstance();

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */
	@Transient
	/**
	 * Collectie van actieve & nieuwe Reparaties. (data-bron voor schermen) */
	public static ArrayListExtended<Reparatie, Reparatie> s_ActiveReparaties = new ArrayListExtended<Reparatie, Reparatie>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Reparaties. (repository voor rollback) */
	public static ArrayList<Reparatie> s_ArchivedReparatieReparatie = new ArrayList<Reparatie>();

	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/**
	 * Default Constructor voor deze klasse. */
	public Reparatie(){
		s_ActiveReparaties.add(this);

	}
	/**
	 * Volledige Constructor voor deze klasse. */
	public Reparatie(LocalDateTime reparatieBeeïndigd){
		m_ReparatieBeeïndigd = reparatieBeeïndigd;
		s_ActiveReparaties.add(this);
	}

	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Functies //----------------// */
	/**
	 * Deze domein-functie haalt via een laadpaal reparatie-documentatie op. */
	public String toonReparatieDoc(Laadpaal laadpaal){
		return s_Repo.laadpaalHashMapInst.get(laadpaal.getLaadpaalType());
	}


	/* //----------------// SECTIE: Technische-Functies //----------------// */
	/**
	 * Deze Domein-functie schrijft een deze instantie over van de Active-ArrayList naar de Archived-ArrayList.
	 * Dit via klasse "ArrayListExtended" via naamgeving "s_ArchivedKlasseItemKlasse" of dit zonder "s_". */
	public void archiveer(){
		this.s_ActiveReparaties.removeWrapped(Oplossing.class, Oplossing.class, true);
	}

	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// PROPERTY: ReparatieBeeïndigd //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de ReparatieBeeïndigd. */
	public void setReparatieBeeïndigd(LocalDateTime value){
		this.m_ReparatieBeeïndigd = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de ReparatieBeeïndigd. */
	public LocalDateTime getReparatieBeeïndigd(){
		return this.m_ReparatieBeeïndigd;
	}

	/* //----------------// PROPERTY: Probleem //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Probleem. */
	public void setProbleem(Probleem value){
		this.m_Probleem = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Probleem. */
	public Probleem getProbleem(){
		return this.m_Probleem;
	}
}