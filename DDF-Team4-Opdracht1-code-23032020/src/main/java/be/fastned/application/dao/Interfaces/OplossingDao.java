package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Andere.Oplossing;

public interface OplossingDao {
    Oplossing createItem(Oplossing item);

    Oplossing deleteItem(Oplossing item);

    void updateItem (Oplossing item);
}
