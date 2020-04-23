package be.fastned.application.dao;

import be.fastned.application.domain.Afspraak;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface AfspraakRepository extends CrudRepository<Afspraak, String> {
    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */

    //hans zoekt afspraken bij id op een andere manier ???
    Afspraak findById(long id);

    /**
     * @return The entry with the largest id = the most recently added entry
     */
    Afspraak findFirstByOrderByIdDesc();
}
