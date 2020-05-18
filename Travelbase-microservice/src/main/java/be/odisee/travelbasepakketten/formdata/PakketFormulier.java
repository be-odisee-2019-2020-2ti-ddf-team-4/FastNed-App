package be.odisee.travelbasepakketten.formdata;

import be.odisee.travelbasepakketten.domain.Pakket;
import lombok.Data;

import java.util.List;

@Data
public class PakketFormulier {
    private final Pakket pakket;

    private final List<Long> activiteitIds;
}
