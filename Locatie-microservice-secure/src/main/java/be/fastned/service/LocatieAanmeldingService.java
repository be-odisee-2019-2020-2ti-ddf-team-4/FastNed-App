package be.fastned.service;

import be.fastned.domain.LocatieAanmelding;

public interface LocatieAanmeldingService {

	public LocatieAanmelding locatieAanmelding(int locatieId);

	public LocatieAanmelding nieuweLocatie(LocatieAanmelding locatieAanmelding);

}