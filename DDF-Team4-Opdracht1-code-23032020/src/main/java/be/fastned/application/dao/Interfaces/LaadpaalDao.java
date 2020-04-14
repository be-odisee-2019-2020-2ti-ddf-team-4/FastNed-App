package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Laadpaal;

public interface LaadpaalDao {
    public Laadpaal addItem(Laadpaal item);

    public Laadpaal deleteItem(Laadpaal item);

    public void updateItem (Laadpaal item);
}
