package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Personen.Locatiehouder;

public interface LocatiehouderDao {
    public Locatiehouder addLocatiehouder(Locatiehouder locatiehouder);

    public Locatiehouder deleteLocatiehouder(Locatiehouder locatiehouder);

    public Locatiehouder getLocatiehouderById (long id);
}
