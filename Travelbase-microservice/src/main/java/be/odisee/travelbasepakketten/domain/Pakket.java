package be.odisee.travelbasepakketten.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PAKKETTEN")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC,force=true) // TODO moet nog aangepast worden naar bendodigheden
public class Pakket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final long id;

    private String naam;

    private String description;
}