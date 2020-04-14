package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Reparatie;

public interface ReparatieDao {
    Reparatie createItem(Reparatie item);

    Reparatie deleteItem(Reparatie item);

    void updateItem (Reparatie item);
}
