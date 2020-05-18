package be.odisee.fastned.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be.odisee.fastned.domain.Probleem;
import be.odisee.fastned.service.ProbleemService;

@RestController
public class ProbleemController {
	
	@Autowired
	ProbleemService probleemService;

	@RequestMapping (path = "bestaandProbleem", method=RequestMethod.POST)
	Probleem bestaandeProbleem (@RequestBody Probleem probleem) {
		return probleemService.probleemLaadpaal(probleem.getId());
	}



	@RequestMapping (path = "nieuwProbleem", method=RequestMethod.POST)
	Probleem nieuweProbleem (@RequestBody Probleem probleem)	{
		probleem = probleemService.nieuwProbleem(probleem);
		return probleemService.probleemLaadpaal(probleem.getId());
	}
	
}
