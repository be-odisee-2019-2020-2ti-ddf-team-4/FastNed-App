package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.OtherImpl.Locatietoestemming;

public interface LocatietoestemmingDao {
    Locatietoestemming createItem(Locatietoestemming item);

    Locatietoestemming deleteItem(Locatietoestemming item);

    void updateItem(Locatietoestemming item);
}
