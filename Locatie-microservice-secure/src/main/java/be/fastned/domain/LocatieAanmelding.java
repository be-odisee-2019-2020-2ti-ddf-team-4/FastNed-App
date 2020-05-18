package be.fastned.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LocatieAanmelding {
	
	// properties
	
	// 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String ownerVoornaam;
	private String ownerNaam;
	private String straatnaam;
	private int huisnummer;
	private String gemeente;
	private String postcode;
	
	
	
	// constructors
	
	public LocatieAanmelding() {}
	
	public LocatieAanmelding(String ownerVnm, String ownerNm, String straatnm, int huisnr, String gemeente, String postcode) {
		this.ownerVoornaam = ownerVnm;
		this.ownerNaam = ownerNm;		
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
	
	public String getOwnerVoornaam() {
		return ownerVoornaam;
	}

	public void setOwnerVoornaam(String ownerVnm) {
		this.ownerVoornaam = ownerVnm;
	}
	
	public String getOwnerNaam() {
		return ownerNaam;
	}

	public void setOwnerNaam(String ownerNm) {
		this.ownerNaam = ownerNm;
	}
	
	public String getStraatnaam() {
		return straatnaam;
	}

	public void setStraatnaam(String straatnm) {
		this.straatnaam = straatnm;
	}
	
	public int getHuisnummer() {
		return huisnummer;
	}

	public void setHuisnummer(int huisnr) {
		this.huisnummer = huisnr;
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
