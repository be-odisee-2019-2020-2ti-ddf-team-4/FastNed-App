package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Probleem;

public interface ProbleemDao {
    public Probleem addItem(Probleem item);

    public Probleem deleteItem(Probleem item);

    public void updateItem (Probleem item);
}
