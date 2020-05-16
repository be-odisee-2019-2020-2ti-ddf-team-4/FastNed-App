package be.fastned.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be.fastned.domain.Locatie;
import be.fastned.service.LocatieService;

@RestController
public class LocatieController {
	
	@Autowired
	LocatieService locatieService;
	
	// locatie opvragen 
	
	@RequestMapping (path = "bestaandeLocatie", method = RequestMethod.POST)
	Locatie bestaandeLocatie (@RequestBody Locatie locatie) {
		return locatieService.locatieAanmelding(locatie.getId()); 
	}
	
	// locatie toevoegen 
	
	@RequestMapping (path = "nieuweLocatie", method=RequestMethod.POST)
	Locatie nieuweLocatie (@RequestBody Locatie locatie)	{
		locatie = locatieService.nieuweLocatie(locatie);
		return locatieService.locatieAanmelding(locatie.getId());
	}

	
	
}
