package be.fastned.application.dao;

import be.fastned.application.domain.Documentatie;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DocumentatieRepository extends CrudRepository<Documentatie, String> {
    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */
    Documentatie findById(long id);

    /**
     * @return The entry with the largest id = the most recently added entry
     */
    Documentatie findFirstByOrderByIdDesc();

    /**
     * List all categories, order alphabetically by name
     */
    List<Documentatie> findAllByOrderByName();
}