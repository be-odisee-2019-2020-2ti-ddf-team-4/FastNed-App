package be.fastned.application.dao;

import be.fastned.application.domain.Oplossing;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface OplossingRepository extends CrudRepository<Oplossing, String> {
    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */
    Oplossing findById(long id);

    /**
     * @return The entry with the largest id = the most recently added entry
     */
    Oplossing findFirstByOrderByIdDesc();

    /**
     * List all categories, order alphabetically by name
     */
    List<Oplossing> findAllByOrderByName();
}
