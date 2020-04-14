package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Contract;
import be.fastned.application.domain.Personen.Laadklant;

public interface LaadklantDao {
    public Laadklant createItem(Laadklant item);

    public Laadklant deleteItem(Laadklant item);

    public void updateItem (Laadklant item);
}
