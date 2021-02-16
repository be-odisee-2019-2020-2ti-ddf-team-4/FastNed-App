package be.fastned.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.fastned.dao.LocatieRepository;
import be.fastned.domain.Locatie;

@Service
public class LocatieServiceImpl implements LocatieService {

	@Autowired
	private LocatieRepository locatieRepository = null;
	
	// locatie opzoeken
	public Locatie locatieAanmelding(int locatieId) {
		Locatie locatie = null;
		locatie = locatieRepository.findOne(locatieId);
		return locatie;
	
	}
	
	//  locatie toevoegen
	@Override
	public Locatie nieuweLocatie(Locatie locatie) {
		// TODO controleer of voor gegeven voornaam + familienaam + email-adres al een lid bestaat 
		return locatieRepository.save(locatie);
	}
}
