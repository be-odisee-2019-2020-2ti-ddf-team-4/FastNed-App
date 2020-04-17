package be.fastned.application.code.dao.Interfaces;

import be.fastned.application.code.domain.Installatie;

public interface InstallatieDao {
    Installatie createItem(Installatie item);

    Installatie deleteItem(Installatie item);

    void updateItem(Installatie item);
}
