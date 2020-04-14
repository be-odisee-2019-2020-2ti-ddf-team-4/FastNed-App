package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Reparatie;

public interface ReparatieDao {
    public Reparatie addItem(Reparatie item);

    public Reparatie deleteItem(Reparatie item);

    public void updateItem (Reparatie item);
}
