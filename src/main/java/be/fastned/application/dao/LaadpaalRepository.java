package be.fastned.application.dao;

import be.fastned.application.domain.Laadpaal;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface LaadpaalRepository extends CrudRepository<Laadpaal, String> {
    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */
    Laadpaal findById(long id);

    /**
     * @return The entry with the largest id = the most recently added entry
     */
    Laadpaal findFirstByOrderByIdDesc();

    /**
     * List all categories, order alphabetically by name
     */
    List<Laadpaal> findAllByOrderByName();
}
