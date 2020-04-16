package be.fastned.application.dao.Personen;

import be.fastned.application.domain.Personen.Locatiehouder;

public interface LocatiehouderDao {
    Locatiehouder createItem(Locatiehouder item);

    Locatiehouder deleteItem(Locatiehouder item);

    void updateItem (Locatiehouder item);
}
