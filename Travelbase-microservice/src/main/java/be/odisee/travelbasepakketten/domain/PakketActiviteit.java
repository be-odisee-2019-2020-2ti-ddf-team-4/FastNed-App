package be.odisee.travelbasepakketten.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PAKKETACTIVITEIT")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC,force=true)
public class PakketActiviteit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final long id;

    @ManyToOne
    private final Pakket pakket;

    private final long activiteitId;
}
