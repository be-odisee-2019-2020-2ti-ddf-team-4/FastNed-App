package be.fastned.application.code.dao.Interfaces;

import be.fastned.application.code.domain.Locatietoestemming;

public interface LocatietoestemmingDao {
    Locatietoestemming createItem(Locatietoestemming item);

    Locatietoestemming deleteItem(Locatietoestemming item);

    void updateItem(Locatietoestemming item);
}
