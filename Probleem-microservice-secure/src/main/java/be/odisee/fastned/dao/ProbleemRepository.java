package be.odisee.fastned.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import be.odisee.fastned.domain.Probleem;

@RepositoryRestResource
public interface ProbleemRepository extends JpaRepository<Probleem, Integer> {
    Optional<List<Probleem>> findByStatusIsLike(@Param("status") String status);
}

