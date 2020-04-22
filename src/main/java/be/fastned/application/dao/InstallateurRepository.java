package be.fastned.application.dao;

import be.fastned.application.domain.Installateur;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface InstallateurRepository extends CrudRepository<Installateur, String> {
    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */
    Installateur findById(long id);

    /**
     * @return The entry with the largest id = the most recently added entry
     */
    Installateur findFirstByOrderByIdDesc();

    /**
     * List all categories, order alphabetically by name
     */
    List<Installateur> findAllByOrderByName();
}
