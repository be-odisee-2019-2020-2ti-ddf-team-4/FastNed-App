package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Personen.Laadklant;

public interface LaadklantDao {
    Laadklant createItem(Laadklant item);

    Laadklant deleteItem(Laadklant item);

    void updateItem (Laadklant item);
}