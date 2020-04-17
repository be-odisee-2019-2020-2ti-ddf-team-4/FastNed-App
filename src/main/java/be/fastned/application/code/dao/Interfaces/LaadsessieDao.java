package be.fastned.application.code.dao.Interfaces;

import be.fastned.application.code.domain.Laadsessie;

public interface LaadsessieDao {
    Laadsessie createItem(Laadsessie item);

    Laadsessie deleteItem(Laadsessie item);

    void updateItem(Laadsessie item);
}
