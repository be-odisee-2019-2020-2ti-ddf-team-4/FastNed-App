package be.fastned.application.dao.form;

import be.fastned.application.domain.form.EntryAfspraak;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EntryAfspraakRepository extends CrudRepository<EntryAfspraak, Long> {

    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */
    public EntryAfspraak findById(long id);

    /**
     * @return The entry with the largest id = the most recently added entry
     */
    public EntryAfspraak findFirstByOrderByIdDesc();

    /**
     *
     * @param startDateTime
     * @param endDateTime
     * @return The entries with DateTimeFrom between startDateTime and endDateTime
     */
    public List<EntryAfspraak> findByDateTimeFromBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
