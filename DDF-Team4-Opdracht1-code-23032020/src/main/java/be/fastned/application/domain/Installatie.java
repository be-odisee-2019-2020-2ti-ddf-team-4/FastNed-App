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
@Entity(name = "Installatie")
@Table(name = "Installaties")
public class Installatie {

	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long m_Id;
	@Column
	private LocalDateTime m_InstallatieBeëindigd;
	@OneToOne(
			mappedBy = "Installaties",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	private Probleem m_Probleem;

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
	 * Collectie van actieve & nieuwe Installaties. (data-bron voor schermen) */
	public static ArrayListExtended<Installatie, Installatie> s_ActiveInstallaties = new ArrayListExtended<Installatie, Installatie>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Installaties. (repository voor rollback) */
	public static ArrayList<Installatie> s_ArchivedInstallatieInstallatie = new ArrayList<Installatie>();

	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/**
	 * Default Constructor voor deze klasse. */
	public Installatie(){
		s_ActiveInstallaties.add(this);
	}
	/**
	 * Volledige Constructor met parameters voor deze klasse. */
	public Installatie(LocalDateTime installatieBeëindigd){
		m_InstallatieBeëindigd = installatieBeëindigd;
		s_ActiveInstallaties.add(this);
	}

	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
	/* //----------------// -#####- |----------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Functies //----------------// */
	/**
	 * Deze domein-functie haalt via een laadpaal installatie-documentatie op. */
	public String toonInstallatieDoc(Laadpaal laadpaal){
		return s_Repo.laadpaalHashMapInst.get(laadpaal.getLaadpaalType());
	}

	/* //----------------// SECTIE: Technische-Functies //----------------// */
	/**
	 * Deze Domein-functie schrijft een deze instantie over van de Active-ArrayList naar de Archived-ArrayList.
	 * Dit via klasse "ArrayListExtended" via naamgeving "s_ArchivedKlasseItemKlasse" of dit zonder "s_". */
	public void archiveer(){
		this.s_ActiveInstallaties.removeWrapped(Installatie.class, Installatie.class, true);
	}

	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
	/* //----------------// -#####- |------------| -#####- //----------------// */

	/* //----------------// PROPERTY: Installatie Beëindigd //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Installatie Beëindigd. */
	public void setInstallatieBeëindigd(LocalDateTime value){
		this.m_InstallatieBeëindigd = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Installatie Beëindigd.
	 */
	public LocalDateTime getInstallatieBeëindigd(){
		return this.m_InstallatieBeëindigd;
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