package be.odisee.travelbasepakketten.dao;

import be.odisee.travelbasepakketten.domain.Pakket;
import be.odisee.travelbasepakketten.domain.PakketActiviteit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PakketActiviteitRepository extends JpaRepository<PakketActiviteit, Long> {
    List<PakketActiviteit> findPakketActiviteitsByPakket(Pakket pakket);
}
