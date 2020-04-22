package be.fastned.application.dao;

import be.fastned.application.domain.Locatiehouder;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface LocatiehouderRepository extends CrudRepository<Locatiehouder, String> {
    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */
    Locatiehouder findById(long id);

    /**
     * @return The entry with the largest id = the most recently added entry
     */
    Locatiehouder findFirstByOrderByIdDesc();
}
