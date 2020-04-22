package be.fastned.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

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

    private final String laadpaalType, documentatieType, documentatieBeschr;
}