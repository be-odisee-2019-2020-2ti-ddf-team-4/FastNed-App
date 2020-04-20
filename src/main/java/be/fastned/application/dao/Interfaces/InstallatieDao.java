package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.OtherImpl.Installatie;

public interface InstallatieDao {
    Installatie createItem(Installatie item);

    Installatie deleteItem(Installatie item);

    void updateItem(Installatie item);
}
