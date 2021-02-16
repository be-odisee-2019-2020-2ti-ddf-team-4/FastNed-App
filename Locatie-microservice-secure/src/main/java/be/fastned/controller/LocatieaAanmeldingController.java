package be.fastned.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import be.fastned.domain.LocatieAanmelding;
import be.fastned.service.LocatieAanmeldingService;

@RestController
@CrossOrigin(origins="http://localhost:8888", maxAge = 3600, allowCredentials = "true")  // needed for CORS cookie passing
public class LocatieaAanmeldingController {
	
	@Autowired
	LocatieAanmeldingService locatieAanmeldingService;
	
	// locatie opvragen 
	
	@RequestMapping (path = "bestaandeLocatie", method = RequestMethod.POST)
	LocatieAanmelding bestaandeLocatie (@RequestBody LocatieAanmelding locatieAanmelding) {
		return locatieAanmeldingService.locatieAanmelding(locatieAanmelding.getId()); 
	}
	
	// locatie toevoegen 
	
	@RequestMapping (path = "nieuweLocatie", method=RequestMethod.POST)
	LocatieAanmelding nieuweLocatie (@RequestBody LocatieAanmelding locatieAanmelding)	{
		locatieAanmelding = locatieAanmeldingService.nieuweLocatie(locatieAanmelding);
		return locatieAanmeldingService.locatieAanmelding(locatieAanmelding.getId());
	}

	
	
}
