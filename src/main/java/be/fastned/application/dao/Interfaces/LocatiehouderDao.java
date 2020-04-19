package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.PersoonEntiteiten.Locatiehouder;

public interface LocatiehouderDao {
    Locatiehouder createItem(Locatiehouder item);

    Locatiehouder deleteItem(Locatiehouder item);

    void updateItem (Locatiehouder item);
}
