package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.OtherImpl.Afspraak;

public interface AfspraakDao {
    Afspraak createItem(Afspraak item);

    Afspraak deleteItem(Afspraak item);

    void updateItem(Afspraak item);
}
