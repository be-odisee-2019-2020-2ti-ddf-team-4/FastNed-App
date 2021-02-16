package be.odisee.fastned.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import be.odisee.fastned.dao.ProbleemRepository;
import be.odisee.fastned.domain.Probleem;

@Service
public class ProbleemServiceImpl implements ProbleemService {
	
	@Autowired
	private ProbleemRepository probleemRepository = null;

	private Probleem neemProbleem(int probleemId) {
		Probleem probleem = null;
		try {
			probleem = probleemRepository.findOne(probleemId);
			probleemRepository.saveAndFlush(probleem);
		} catch (ObjectRetrievalFailureException e) {
			e.printStackTrace();
		}
		return probleem;
	}
	
	@Override
	public Probleem probleemLaadpaal(int probleemId) {
		return neemProbleem(probleemId);
	}

	@Override
	public Probleem nieuwProbleem(Probleem probleem) {
		// TODO controleer of voor gegeven voornaam + familienaam + email-adres al een lid bestaat 
		return probleemRepository.save(probleem);
	}

}
