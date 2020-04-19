package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.PersoonEntiteiten.Installateur;

public interface InstallateurDao {
    Installateur createItem(Installateur item);

    Installateur deleteItem(Installateur item);

    void updateItem (Installateur item);
}
