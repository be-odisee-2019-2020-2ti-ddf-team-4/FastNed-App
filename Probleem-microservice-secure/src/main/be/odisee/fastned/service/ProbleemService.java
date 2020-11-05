package be.odisee.fastned.service;

import be.odisee.fastned.domain.Probleem;
import org.springframework.stereotype.Service;

@Service
public interface ProbleemService {

	public Probleem probleemLaadpaal(int probleemId);

	public Probleem nieuwProbleem(Probleem probleem);

}
