package be.fastned.application.dao;

import be.fastned.application.domain.Probleem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProbleemRepository extends CrudRepository<Probleem, String> {
    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */
    Probleem findById(long id);

    /**
     * @return The entry with the largest id = the most recently added entry
     */
    Probleem findFirstByOrderByIdDesc();
}
