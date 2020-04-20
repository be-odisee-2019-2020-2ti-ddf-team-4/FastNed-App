package be.fastned.application.code.dao.Interfaces;

import be.fastned.application.code.domain.Probleem;

public interface ProbleemDao {
    Probleem createItem(Probleem item);

    Probleem deleteItem(Probleem item);

    void updateItem(Probleem item);
}
