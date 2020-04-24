package be.fastned.application.dao;

import be.fastned.application.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    // needed for Spring Security
    public User findByUsername(String username);

    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */
    User findById(long id);
}
