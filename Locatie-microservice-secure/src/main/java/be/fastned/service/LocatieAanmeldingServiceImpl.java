package be.fastned.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.fastned.dao.LocatieAanmeldingRepository;
import be.fastned.domain.LocatieAanmelding;

@Service
public class LocatieAanmeldingServiceImpl implements be.fastned.service.LocatieAanmeldingService {

	@Autowired
	private LocatieAanmeldingRepository locatieAanmeldingRepository = null;
	
	// locatie opzoeken
	public LocatieAanmelding locatieAanmelding(int locatieId) {
		LocatieAanmelding locatieAanmelding = null;
		locatieAanmelding = locatieAanmeldingRepository.findOne(locatieId);
		return locatieAanmelding;
	
	}
	
	//  locatie toevoegen
	@Override
	public LocatieAanmelding nieuweLocatie(LocatieAanmelding locatieAanmelding) {
		// TODO controleer of voor gegeven voornaam + familienaam + email-adres al een lid bestaat 
		return locatieAanmeldingRepository.save(locatieAanmelding);
	}
}
