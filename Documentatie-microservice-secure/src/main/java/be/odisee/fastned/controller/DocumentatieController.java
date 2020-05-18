package be.odisee.fastned.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import be.odisee.fastned.domain.Documentatie;
import be.odisee.fastned.service.DocumentatieService;

@RestController
@CrossOrigin(origins="http://localhost:" +
		"8888", maxAge = 3600, allowCredentials = "true")  // needed for CORS cookie passing
public class DocumentatieController {
	
	@Autowired
	DocumentatieService documentatieService;
	
	@RequestMapping (path = "bestaandeDocumentatie", method=RequestMethod.POST)
	Documentatie bestaandeDocumentatie (@RequestBody Documentatie documentatie)	{
		return documentatieService.documentatieLaadpaal(documentatie.getId());
	}



	@RequestMapping (path = "nieuweDocumentatie", method=RequestMethod.POST)
	Documentatie nieuweDocumentatie (@RequestBody Documentatie documentatie)	{
		documentatie = documentatieService.nieuwDocumentatie(documentatie);
		return documentatieService.documentatieLaadpaal(documentatie.getId());
	}
	
}
