package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.AndereEntiteiten.Contract;

public interface ContractDao {
    Contract createItem(Contract item);

    Contract deleteItem(Contract item);

    void updateItem (Contract item);
}
