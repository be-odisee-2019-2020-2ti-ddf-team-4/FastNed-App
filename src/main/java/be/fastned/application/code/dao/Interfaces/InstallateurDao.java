package be.fastned.application.code.dao.Interfaces;

import be.fastned.application.code.domain.Personen.Installateur;

public interface InstallateurDao {
    Installateur createItem(Installateur item);

    Installateur deleteItem(Installateur item);

    void updateItem(Installateur item);
}
