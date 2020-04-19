package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.AndereEntiteiten.Laadsessie;

public interface LaadsessieDao {
    Laadsessie createItem(Laadsessie item);

    Laadsessie deleteItem(Laadsessie item);

    void updateItem (Laadsessie item);
}
