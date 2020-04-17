package be.fastned.application.code.dao.Interfaces;

import be.fastned.application.code.domain.Personen.Persoon;
import be.fastned.application.code.domain.Personen.PersoonImpl;

public interface PersoonDao {
    PersoonImpl createItem(PersoonImpl item);

    Persoon deleteItem(Persoon item);

    void updateItem(Persoon item);
}