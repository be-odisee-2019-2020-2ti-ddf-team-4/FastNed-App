package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Afspraak;

public interface AfspraakDao {
    public Afspraak addItem(Afspraak item);

    public Afspraak deleteItem(Afspraak item);

    public void updateItem (Afspraak item);
}
