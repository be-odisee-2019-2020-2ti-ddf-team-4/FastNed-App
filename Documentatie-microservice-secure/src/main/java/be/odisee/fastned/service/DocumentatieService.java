package be.odisee.fastned.service;

import be.odisee.fastned.domain.Documentatie;
import org.springframework.stereotype.Service;

@Service
public interface DocumentatieService {

	public Documentatie documentatieLaadpaal(int documentatieId);

	public Documentatie nieuwDocumentatie(Documentatie documentatie);

}
