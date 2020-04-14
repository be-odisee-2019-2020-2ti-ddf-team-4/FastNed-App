package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Contract;

public interface ContractDao {
    public Contract createItem(Contract item);

    public Contract deleteItem(Contract item);

    public void updateItem (Contract item);
}
