package be.fastned.application.dao;

import be.fastned.application.domain.Locatietoestemming;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface LocatietoestemmingRepository extends CrudRepository<Locatietoestemming, String> {
    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */
    Locatietoestemming findById(long id);

    /**
     * @return The entry with the largest id = the most recently added entry
     */
    Locatietoestemming findFirstByOrderByIdDesc();
}
