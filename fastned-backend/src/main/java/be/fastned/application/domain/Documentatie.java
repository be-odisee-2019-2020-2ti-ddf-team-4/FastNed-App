package be.fastned.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author TiboVG
 * @version 6.0
 */

@Entity(name = "Documentatie")
@Table(name = "DOCUMENTATIES")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)

public class Documentatie {

    /* //----------------// -##########--------------------------------##########- //----------------// */
    /* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
    /* //----------------// -##########--------------------------------##########- //----------------// */

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private final long id;

    @Column
    @Size(min=1, max = 64, message="Houd het laadpaal-type tussen 1 en 64 tekens aub!")
    private final String laadpaalType;

    @Column
    @Size(min=1, max = 64, message="Houd het documentatie-type tussen 1 en 64 tekens aub!")
    private final String documentatieType;

    @Column
    @Size(min=1, max = 510, message="Houd de documentatie-beschrijving tussen 1 en 510 tekens aub!")
    private final String documentatieBeschr;
}