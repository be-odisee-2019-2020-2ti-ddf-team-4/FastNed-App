package be.fastned.application.domain.Personen;

/**
 * @author TiboVG
 * @version 6.0
 */

public interface Persoon {
	Object hf_CheckIsActive(Object arg);

	String getId();
	void setId(String value);

	String getNaam();
	void setNaam(String value);

	String getVoorNaam();
	void setVoorNaam(String value);

	String getGeslacht();
	void setGeslacht(String value);

	String getGsm();
	void setGsm(String value);

	String getEmailadres();
	void setEmailadres(String value);

	String getGebruikersnaam();
	void setGebruikersnaam(String value);

	String getWachtwoord();
	void setWachtwoord(String value);
}