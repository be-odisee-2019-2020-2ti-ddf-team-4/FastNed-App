package be.odisee.fastned.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import be.odisee.fastned.domain.Documentatie;

@RepositoryRestResource
public interface DocumentatieRepository extends JpaRepository<Documentatie, Integer> {
    Optional<List<Documentatie>> findByLaadpaalTypeStartingWith(@Param("laadpaalType") String laadpaalType);
}

