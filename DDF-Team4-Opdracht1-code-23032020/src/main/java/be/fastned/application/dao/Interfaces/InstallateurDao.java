package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Contract;
import be.fastned.application.domain.Personen.Installateur;

import java.util.ArrayList;

public interface InstallateurDao {
    public Installateur createItem(Installateur item);

    public Installateur deleteItem(Installateur item);

    public void updateItem (Installateur item);
}
