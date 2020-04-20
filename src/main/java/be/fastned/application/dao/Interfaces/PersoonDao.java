package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Personen.Persoon;
import be.fastned.application.domain.Personen.PersoonImpl;

public interface PersoonDao {
    PersoonImpl createItem(PersoonImpl item);

    Persoon deleteItem(Persoon item);

    void updateItem(Persoon item);
}