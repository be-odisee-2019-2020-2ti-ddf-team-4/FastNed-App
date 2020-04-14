package be.fastned.application.domain.PersoonAbstracties.Interfaces;

import java.util.ArrayList;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */
public interface Persoon {
	Object hf_CheckIsActive(Object arg);

	String getSuperId();
	void setSuperId(String value);

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