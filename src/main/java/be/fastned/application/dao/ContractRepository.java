package be.fastned.application.dao;

import be.fastned.application.domain.Contract;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ContractRepository extends CrudRepository<Contract, String> {
    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */
    Contract findById(long id);

    /**
     * @return The entry with the largest id = the most recently added entry
     */
    Contract findFirstByOrderByIdDesc();

    /**
     * List all categories, order alphabetically by name
     */
    List<Contract> findAllByOrderByName();
}
