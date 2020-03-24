package be.fastned.application.domain;

import be.fastned.application.domain.custom.ArrayListExtended;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */
@Entity(name = "Locatietoestemming")
@Table(name = "Locatietoestemmingmen")
public class Locatietoestemming {
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |----------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long m_Id;
	@Column
	private int m_AantalLaadpalen;
	@Column
	private String m_TypeLaadpaal;
	@Column
	private String m_Status;

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */


	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
	/* //----------------// -#####- |-------------------| -#####- //----------------// */
	/* //----------------// SECTIE: Domein-Variabelen //----------------// */

	/* //----------------// SECTIE: Technische-Variabelen //----------------// */
	@Transient
	/**
	 * Collectie van actieve & nieuwe Locatietoestemmingen. (data-bron voor schermen) */
	public static ArrayListExtended<Locatietoestemming, Locatietoestemming> s_ActiveLocatietoestemmingen = new ArrayListExtended<Locatietoestemming, Locatietoestemming>();
	@Transient
	/**
	 * Collectie van verlopen & afgehandelde Locatietoestemming. (repository voor rollback) */
	public static ArrayList<Locatietoestemming> s_ArchivedLocatietoestemmingLocatietoestemming = new ArrayList<Locatietoestemming>();

	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
	/* //----------------// -#####- |--------------| -#####- //----------------// */
	/**
	 * Default Constructor voor deze klasse. */
	public Locatietoestemming(){
		s_ActiveLocatietoestemmingen.add(this);

	}
	/**
	 * Volledige Constructor voor deze klasse. */
	public Locatietoestemming(int aantalLaadpalen, String typeLaadpaal, String status){
		m_AantalLaadpalen = aantalLaadpalen;
		m_TypeLaadpaal = typeLaadpaal;
		m_Status = status;
		s_ActiveLocatietoestemmingen.add(this);
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
		this.s_ActiveLocatietoestemmingen.removeWrapped(Locatietoestemming.class, Locatietoestemming.class, true);
	}

	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
	/* //----------------// -#####- |------------| -#####- //----------------// */
	/* //----------------// PROPERTY: AantalLaadpalen //----------------// */
	public void setAantalLaadpalen(int value){ m_AantalLaadpalen = value; }
	public int getAantalLaadpalen(){
		return m_AantalLaadpalen;
	}

	/* //----------------// PROPERTY: TypeLaadpaal //----------------// */
	public void setTypeLaadpaal(String value){ m_TypeLaadpaal = value; }
	public String getTypeLaadpaal(){
		return m_TypeLaadpaal;
	}

	/* //----------------// PROPERTY: Status //----------------// */
	public void setStatus(String value){ m_Status = value; }
	public String getStatus(){
		return m_Status;
	}
}