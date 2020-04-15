package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;

public interface PersoonDao {
    Persoon createItem(Persoon item);

    Persoon deleteItem(Persoon item);

    void updateItem (Persoon item);
}
