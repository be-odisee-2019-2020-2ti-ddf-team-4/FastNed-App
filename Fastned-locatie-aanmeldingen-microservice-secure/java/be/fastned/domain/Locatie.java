package be.fastned.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Locatie {
	
	// properties
	
	// 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String straatnaam;
	private int huisnummer;
	private String gemeente;
	private String postcode;
	
	
	
	// constructors
	
	public Locatie() {}
	
	public Locatie(String straatnm, int huisnr, String gemeente, String postcode) {
		this.straatnaam = straatnm;
		this.huisnummer = huisnr;
		this.gemeente = gemeente;
		this.postcode = postcode;
		
	}
	
	
	// getters & setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getStraatnaam() {
		return straatnaam;
	}

	public void setStraatnaam(String straatnaam) {
		this.straatnaam = straatnaam;
	}
	
	public int getHuisnummer() {
		return huisnummer;
	}

	public void setHuisnummer(int huisnummer) {
		this.huisnummer = huisnummer;
	}
	
	public String getGemeente() {
		return gemeente;
	}

	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}
	
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

}
