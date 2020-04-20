package be.fastned.application.code.dao.Interfaces;

import be.fastned.application.code.domain.Oplossing;

public interface OplossingDao {
    Oplossing createItem(Oplossing item);

    Oplossing deleteItem(Oplossing item);

    void updateItem(Oplossing item);
}
