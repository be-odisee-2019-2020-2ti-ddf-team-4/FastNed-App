package be.fastned.application.dao;

import be.fastned.application.domain.Planner;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PlannerRepository extends CrudRepository<Planner, String> {
    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */
    Planner findById(long id);

    /**
     * @return The entry with the largest id = the most recently added entry
     */
    Planner findFirstByOrderByIdDesc();

    /**
     * List all categories, order alphabetically by name
     */
     List<Planner> findAllByOrderByName();
}
