package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.PersoonEntiteiten.Persoon;

public interface PersoonDao {
    Persoon createItem(Persoon item);

    Persoon deleteItem(Persoon item);

    void updateItem (Persoon item);
}