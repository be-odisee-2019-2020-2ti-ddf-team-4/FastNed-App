package be.odisee.fastned.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import be.odisee.fastned.dao.DocumentatieRepository;
import be.odisee.fastned.domain.Documentatie;

@Service
public class DocumentatieServiceImpl implements DocumentatieService {
	
	@Autowired
	private DocumentatieRepository documentatieRepository = null;

	private Documentatie neemDocumentatie(int documentatieId) {
		Documentatie documentatie = null;
		try {
			documentatie = documentatieRepository.findOne(documentatieId);
			documentatieRepository.saveAndFlush(documentatie);
		} catch (ObjectRetrievalFailureException e) {
			e.printStackTrace();
		}
		return documentatie;
	}
	
	@Override
	public Documentatie documentatieLaadpaal(int documentatieId) {
		return neemDocumentatie(documentatieId);
	}

	@Override
	public Documentatie nieuwDocumentatie(Documentatie documentatie) {
		// TODO controleer of voor gegeven voornaam + familienaam + email-adres al een lid bestaat 
		return documentatieRepository.save(documentatie);
	}

}
