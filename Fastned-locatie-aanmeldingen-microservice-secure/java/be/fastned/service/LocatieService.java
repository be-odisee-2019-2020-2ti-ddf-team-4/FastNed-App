package be.fastned.service;

import be.fastned.domain.Locatie;

public interface LocatieService {

	public Locatie locatieAanmelding(int locatieId);

	public Locatie nieuweLocatie(Locatie locatie);

}