package be.fastned.application.code.dao.Interfaces;

import be.fastned.application.code.domain.Laadpaal;

public interface LaadpaalDao {
    Laadpaal createItem(Laadpaal item);

    Laadpaal deleteItem(Laadpaal item);

    void updateItem(Laadpaal item);
}
