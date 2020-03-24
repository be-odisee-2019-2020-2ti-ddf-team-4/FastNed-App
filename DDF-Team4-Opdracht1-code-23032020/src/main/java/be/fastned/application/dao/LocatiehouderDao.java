package be.fastned.application.dao;

import be.fastned.application.domain.Locatiehouder;

public interface LocatiehouderDao {
    public Locatiehouder addLocatiehouder(Locatiehouder locatiehouder);

    public Locatiehouder deleteLocatiehouder(Locatiehouder locatiehouder);

    public Locatiehouder getLocatiehouderById (long id);
}
