package be.fastned.application.domain;

import be.fastned.application.domain.custom.ArrayListExtended;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * @TODO Add dependency for .IsEmpty en .IsBlank use in Controle-class (org.apache.commons)
 */

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */

@Entity(name = "Afspraak")
@Table(name="afspraken")
public class Afspraak {
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Properties //----------------// */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long m_Id;
	@OneToOne(
			mappedBy = "afspraken",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	private Laadpaal m_Laadpaal = null;
	@OneToOne(
			mappedBy = "afspraken",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	private Installateur m_Installateur = null;
	@OneToOne(
			mappedBy = "afspraken",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	private Contract m_Contract = null;
	@Column
	private String  m_AfspraakStatus = "";
	@OneToOne(
			mappedBy = "afspraken",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	private Installatie m_Installatie = null;
	@OneToOne(
			mappedBy = "afspraken",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	private Reparatie m_Reparatie = null;

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */
	@Transient
	public ArrayList<Laadpaal> Laadpalen = null;

	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/**
	 * Collectie van actieve & nieuwe Afspraken. (data-bron voor schermen) */
	public static ArrayListExtended<Afspraak, Afspraak> s_ActiveAfspraken = new ArrayListExtended<Afspraak, Afspraak>();
	/**
	 * Collectie van verlopen & afgehandelde Afspraken. (repository voor rollback) */
	public static ArrayList<Afspraak> s_ArchivedAfspraakAfspraak = new ArrayList<Afspraak>();

	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/**
	 * Default Constructor voor deze klasse. */
	public Afspraak(){
		this.s_ActiveAfspraken.add(this);
		//this.ActiveReparaties.add(Afspraak);
	}
	/**
	 * Volledige Constructor voor deze klasse. */
	public Afspraak(ArrayList<Laadpaal> laadpalen, Installateur installateur, Contract contract){
		Laadpalen = laadpalen;
		m_Installateur = installateur;
		m_Contract = contract;
		m_AfspraakStatus = "Aangemaakt";
		this.s_ActiveAfspraken.add(this);
	}
	/**
	 * Secundaire Volledige Constructor voor deze klasse. */
	public Afspraak(Laadpaal laadpaal, Installateur installateur, Contract contract){
		m_Laadpaal = laadpaal;
		m_Installateur = installateur;
		m_Contract = contract;
		m_AfspraakStatus = "Aangemaakt";
		this.s_ActiveAfspraken.add(this);
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
		this.s_ActiveAfspraken.removeWrapped(Afspraak.class, Afspraak.class, true);
	}

	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// PROPERTY: Id //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de afspraak-id. */
	public void setId(long value){
		this.m_Id = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de afspraak-id. */
	public long getId(){
		return this.m_Id;
	}

	/* //----------------// PROPERTY: Laadpaal //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de afspraak-status. */
	public void setLaadpaal(Laadpaal value){
		this.m_Laadpaal = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de afspraak-status. */
	public Laadpaal getLaadpaal(){
		return this.m_Laadpaal;
	}

	/* //----------------// PROPERTY: Installateur //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de afspraak-status. */
	public void setInstallateur(Installateur value){
		this.m_Installateur = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de afspraak-status. */
	public Installateur getInstallateur(){
		return this.m_Installateur;
	}

	/* //----------------// PROPERTY: Contract //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de afspraak-status. */
	public void setContract(Contract value){
		this.m_Contract = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de afspraak-status. */
	public Contract getContract(){
		return this.m_Contract;
	}

	/* //----------------// PROPERTY: Laadpaal-Status //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de afspraak-status. */
	public void setStatus(String value){
		this.m_AfspraakStatus = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de afspraak-status. */
	public String getStatus(){
		return this.m_AfspraakStatus;
	}

	/* //----------------// PROPERTY: Installatie //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Installatie. */
	public void setInstallatie(Installatie value){
		this.m_Installatie = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Installatie. */
	public Installatie getInstallatie(){
		return this.m_Installatie;
	}

	/* //----------------// PROPERTY: Reparatie //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de Reparatie. */
	public void setReparatie(Reparatie value){
		this.m_Reparatie = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de Reparatie. */
	public Reparatie getReparatie(){
		return this.m_Reparatie;
	}
}