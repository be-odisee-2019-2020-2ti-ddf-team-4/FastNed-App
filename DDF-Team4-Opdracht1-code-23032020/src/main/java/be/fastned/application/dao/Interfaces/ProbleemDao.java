package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Probleem;

public interface ProbleemDao {
    Probleem createItem(Probleem item);

    Probleem deleteItem(Probleem item);

    void updateItem (Probleem item);
}
