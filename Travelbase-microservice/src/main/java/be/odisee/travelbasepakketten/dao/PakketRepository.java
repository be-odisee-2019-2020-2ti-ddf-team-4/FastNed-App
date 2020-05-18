package be.odisee.travelbasepakketten.dao;

import be.odisee.travelbasepakketten.domain.Pakket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PakketRepository extends JpaRepository<Pakket, Long> {

    Pakket findPakketById(long id);

}
