package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Contract;
import be.fastned.application.domain.Personen.Locatiehouder;

public interface LocatiehouderDao {
    public Locatiehouder createItem(Locatiehouder item);

    public Locatiehouder deleteItem(Locatiehouder item);

    public void updateItem (Locatiehouder item);
}
