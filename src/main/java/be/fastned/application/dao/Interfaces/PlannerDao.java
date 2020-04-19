package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.PersoonEntiteiten.Planner;

public interface PlannerDao {
    Planner createItem(Planner item);

    Planner deleteItem(Planner item);

    void updateItem (Planner item);
}
