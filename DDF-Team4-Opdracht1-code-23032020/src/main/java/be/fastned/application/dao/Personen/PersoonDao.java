package be.fastned.application.dao.Personen;

import be.fastned.application.domain.Personen.Persoon;

public interface PersoonDao {
    Persoon createItem(Persoon item);

    Persoon deleteItem(Persoon item);

    void updateItem (Persoon item);
}