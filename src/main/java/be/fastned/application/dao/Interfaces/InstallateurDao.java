package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Personen.Installateur;

public interface InstallateurDao {
    Installateur createItem(Installateur item);

    Installateur deleteItem(Installateur item);

    void updateItem(Installateur item);
}
