package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Andere.Laadpaal;

public interface LaadpaalDao {
    Laadpaal createItem(Laadpaal item);

    Laadpaal deleteItem(Laadpaal item);

    void updateItem (Laadpaal item);
}
