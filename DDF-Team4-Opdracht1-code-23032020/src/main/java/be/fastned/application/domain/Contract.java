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
@Entity(name = "Contract")
@Table(name="contracten")
public class Contract {

	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long m_Id;
	@Column
	private LocalDateTime m_ContractDatum = null;
	@Column
	private LocalDateTime m_InstallatieDatum = null;

	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |-------------------| -#####- //----------------// */

	/* //----------------// SECTIE: Domein-Variabelen //----------------// */

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */
	@Transient
	/**
	 * Collectie van actieve & nieuwe Contracten. (data-bron voor schermen) */
	public static ArrayListExtended<Contract, Contract> s_ActiveContracten = new ArrayListExtended<Contract, Contract>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Contracten. (repository voor rollback) */
	public static ArrayList<Contract> s_ArchivedContractContract = new ArrayList<Contract>();

	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/**
	 * Default Constructor voor deze klasse. */
	public Contract(){
		this.s_ActiveContracten.add(this);
	}
	/**
	 * Volledige Constructor voor deze klasse. */
	public Contract(LocalDateTime contractDatum, LocalDateTime installatieDatum){
		m_ContractDatum = contractDatum;
		m_InstallatieDatum = installatieDatum;
		this.s_ActiveContracten.add(this);
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
		this.s_ActiveContracten.removeWrapped(Contract.class, Contract.class, true);
	}

	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
	/* //----------------// -#####- |------------| -#####- //----------------// */

	/* //----------------// PROPERTY: Contract-Datum //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de contract-datum. */
	public void setContractDatum(LocalDateTime value){
		this.m_ContractDatum = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de contract-datum. */
	public LocalDateTime getContractDatum(){
		return this.m_ContractDatum;
	}

	/* //----------------// PROPERTY: Installatie-Datum //----------------// */
	/**
	 * Deze domein-attribuut setter vertegenwoordigt de installatie-datum. */
	public void setInstallatieDatum(LocalDateTime value){
		this.m_InstallatieDatum = value;
	}
	/**
	 * Deze domein-attribuut getter vertegenwoordigt de installatie-datum. */
	public LocalDateTime getInstallatieDatum(){
		return this.m_InstallatieDatum;
	}
}