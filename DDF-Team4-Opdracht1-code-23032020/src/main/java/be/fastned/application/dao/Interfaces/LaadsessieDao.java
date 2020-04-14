package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Laadsessie;

public interface LaadsessieDao {
    public Laadsessie addItem(Laadsessie item);

    public Laadsessie deleteItem(Laadsessie item);

    public void updateItem (Laadsessie item);
}
