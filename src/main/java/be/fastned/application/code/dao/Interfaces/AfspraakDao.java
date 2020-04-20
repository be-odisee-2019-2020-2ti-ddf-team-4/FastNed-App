package be.fastned.application.code.dao.Interfaces;

import be.fastned.application.code.domain.Afspraak;

public interface AfspraakDao {
    Afspraak createItem(Afspraak item);

    Afspraak deleteItem(Afspraak item);

    void updateItem(Afspraak item);
}
