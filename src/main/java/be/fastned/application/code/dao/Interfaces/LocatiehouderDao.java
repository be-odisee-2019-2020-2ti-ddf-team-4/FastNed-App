package be.fastned.application.code.dao.Interfaces;

import be.fastned.application.code.domain.Personen.Locatiehouder;

public interface LocatiehouderDao {
    Locatiehouder createItem(Locatiehouder item);

    Locatiehouder deleteItem(Locatiehouder item);

    void updateItem(Locatiehouder item);
}
