package be.fastned.application.code.dao.Interfaces;

import be.fastned.application.code.domain.Reparatie;

public interface ReparatieDao {
    Reparatie createItem(Reparatie item);

    Reparatie deleteItem(Reparatie item);

    void updateItem(Reparatie item);
}
