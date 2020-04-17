package be.fastned.application.code.dao.Interfaces;

import be.fastned.application.code.domain.Contract;

public interface ContractDao {
    Contract createItem(Contract item);

    Contract deleteItem(Contract item);

    void updateItem(Contract item);
}
