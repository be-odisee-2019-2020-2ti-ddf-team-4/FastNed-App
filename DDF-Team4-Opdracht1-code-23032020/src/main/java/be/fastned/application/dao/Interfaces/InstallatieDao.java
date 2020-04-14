package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Installatie;

public interface InstallatieDao {
    public Installatie addItem(Installatie item);

    public Installatie deleteItem(Installatie item);

    public void updateItem (Installatie item);
}
