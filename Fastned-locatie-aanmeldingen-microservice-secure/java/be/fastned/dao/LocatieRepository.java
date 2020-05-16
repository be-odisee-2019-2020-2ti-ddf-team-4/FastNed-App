package be.fastned.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import be.fastned.domain.Locatie;

@RepositoryRestResource
public interface LocatieRepository extends JpaRepository<Locatie, Integer> {
	
	/*
	 * findyBy methoden: CRUD uit JpaRepository = subinterface
	 * conventie mogelijkheid om op @parameter te zoeken 
	 * begin letters = voldoende
	 */
	
	Optional<List<Locatie>> findByStraatnaamStartingWith(@Param("straatnaam") String straatnaam);
	Optional<List<Locatie>> findByGemeenteStartingWith(@Param("gemeente") String gemeente);
	Optional<List<Locatie>> findByPostcodeStartingWith (@Param("postcode") String postcode);
}
