package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Oplossing;

public interface OplossingDao {
    public Oplossing addItem(Oplossing item);

    public Oplossing deleteItem(Oplossing item);

    public void updateItem (Oplossing item);
}
