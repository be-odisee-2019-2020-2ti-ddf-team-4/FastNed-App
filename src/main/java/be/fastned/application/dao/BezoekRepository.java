package be.fastned.application.dao;

import be.fastned.application.domain.Bezoek;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BezoekRepository extends CrudRepository<Bezoek, String> {
    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */
    Bezoek findById(long id);

    /**
     * @return The entry with the largest id = the most recently added entry
     */
    Bezoek findFirstByOrderByIdDesc();
}
