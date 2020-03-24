package be.fastned.application.domain;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */
public interface Persoon {
	long getId();
	void setId(long id);

	String getNaam();
	void setNaam(String naam);

	String getVoorNaam();
	void setVoorNaam(String voorNaam);

	String getGeslacht();
	void setGeslacht(String geslacht);

	String getEmailadres();
	void setEmailadres(String emailadres);

	String getGsm();
	void setGsm(String gsm);

	String getGebruikersnaam();
	void setGebruikersnaam(String gebruikersnaam);

	String getWachtwoord();
	void setWachtwoord(String wachtwoord);

	Persoon identificeer(String voornaam, String naam, String geslacht, String emailAdres, String gsm);
}