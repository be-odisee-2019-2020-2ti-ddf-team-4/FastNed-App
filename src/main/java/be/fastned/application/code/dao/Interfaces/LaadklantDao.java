package be.fastned.application.code.dao.Interfaces;

import be.fastned.application.code.domain.Personen.Laadklant;

public interface LaadklantDao {
    Laadklant createItem(Laadklant item);

    Laadklant deleteItem(Laadklant item);

    void updateItem(Laadklant item);
}
