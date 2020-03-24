package be.fastned.application.domain;

import be.fastned.application.domain.custom.ArrayListExtended;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:53
 */
@Entity(name = "Laadpaal")
@Table(name = "Laadpalen")
public class Laadpaal {
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long m_Id;
	@OneToOne(
			mappedBy = "Laadpalen",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	private Locatiehouder m_LocatieEigenaar;
	@Column
	private String m_InstallatieDoc;
	@Column
	private String m_ReparatieDoc;
	@Column
	private String m_LaadpaalType = null;
	@Column
	private String m_Status;

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */

	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |-------------------| -#####- //----------------// */

	/* //----------------// SECTIE: Domein-Variabelen //----------------// */
	@Transient
	public static DocumentatieRepository repo = DocumentatieRepository.getInstance();

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */
	@Transient
	/**
	 * Collectie van actieve & nieuwe Laadpalen. (data-bron voor schermen) */
	public static ArrayListExtended<Laadpaal, Laadpaal> s_ActiveLaadpalen = new ArrayListExtended<Laadpaal, Laadpaal>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Laadpalen. (repository voor rollback) */
	public static ArrayList<Laadpaal> s_ArchivedLaadpaalLaadpaal = new ArrayList<Laadpaal>();

	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/**
	 * Default Constructor voor deze klasse. */
	public Laadpaal(){
		s_ActiveLaadpalen.add(this);
	}
	/**
	 * Volledige Constructor voor deze klasse. */
	public Laadpaal(Locatiehouder locatiehouder, String laadpaalType){
		m_LocatieEigenaar = locatiehouder;
		m_LaadpaalType = laadpaalType;
		m_Status = "aangemaakt";
		m_InstallatieDoc = repo.laadpaalHashMapInst.get(laadpaalType);
		m_ReparatieDoc = repo.laadpaalHashMapRep.get(laadpaalType);
		s_ActiveLaadpalen.add(this);
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
		this.s_ActiveLaadpalen.removeWrapped(Laadpaal.class, Laadpaal.class, true);
	}

	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// PROPERTY: Locatie-Eigenaar //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Locatie-Eigenaar. */
	public void setLocatieEigenaar(Locatiehouder value){
		this.m_LocatieEigenaar = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Locatie-Eigenaar. */
	public Locatiehouder getLocatieEigenaar(){
		return this.m_LocatieEigenaar;
	}

	/* //----------------// PROPERTY: Laadpaal-Type //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Laadpaal-Type. */
	public void setLaadpaalType(String value){
		this.m_LaadpaalType = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Laadpaal-Type. */
	public String getLaadpaalType(){
		return this.m_LaadpaalType;
	}

	/* //----------------// PROPERTY: Status //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Status. */
	public void setStatus(String value){
		this.m_Status = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Status */
	public String getStatus(){
		return this.m_Status;
	}

	/* //----------------// PROPERTY: Installatie-Documentatie //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Installatie-Documentatie. */
	public void setInstallatieDoc(String value){
		this.m_InstallatieDoc = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Installatie-Documentatie. */
	public String getInstallatieDoc(){
		return this.m_InstallatieDoc;
	}

	/* //----------------// PROPERTY: Reparatie-Documentatie //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Reparatie-Documentatie. */
	public void setReparatieDoc(String value){
		this.m_ReparatieDoc = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Reparatie-Documentatie. */
	public String getReparatieDoc(){
		return this.m_ReparatieDoc;
	}

}