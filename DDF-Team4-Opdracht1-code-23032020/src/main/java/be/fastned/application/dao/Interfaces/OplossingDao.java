package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.AndereEntiteiten.Oplossing;

public interface OplossingDao {
    Oplossing createItem(Oplossing item);

    Oplossing deleteItem(Oplossing item);

    void updateItem (Oplossing item);
}
