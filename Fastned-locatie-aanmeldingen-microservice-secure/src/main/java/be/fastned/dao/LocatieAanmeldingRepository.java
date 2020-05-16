package be.fastned.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import be.fastned.domain.LocatieAanmelding;


@RepositoryRestResource
public interface LocatieAanmeldingRepository extends JpaRepository<LocatieAanmelding, Integer> {
	
	/*
	 * findyBy methoden: CRUD uit JpaRepository = subinterface
	 * conventie mogelijkheid om op @parameter te zoeken 
	 * begin letters = voldoende
	 */
	

	Optional<List<LocatieAanmelding>> findByOwnerVoornaamStartingWith(@Param("voornaam") String voornaam);
	Optional<List<LocatieAanmelding>> findByOwnerNaamStartingWith(@Param("naam") String naam);
	Optional<List<LocatieAanmelding>> findByOwnerVoornaamStartingWithIgnoreCaseAndOwnerNaamStartingWithIgnoreCaseOrderByOwnerNaam
								(@Param("voornaam") String voornaam, @Param("naam") String naam);
	
	Optional<List<LocatieAanmelding>> findByStraatnaamStartingWith(@Param("straatnaam") String straatnaam);
	Optional<List<LocatieAanmelding>> findByGemeenteStartingWith(@Param("gemeente") String gemeente);
	Optional<List<LocatieAanmelding>> findByPostcodeStartingWith (@Param("postcode") String postcode);
}
