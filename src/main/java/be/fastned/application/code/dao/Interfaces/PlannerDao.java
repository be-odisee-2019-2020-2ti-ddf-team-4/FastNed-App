package be.fastned.application.code.dao.Interfaces;

import be.fastned.application.code.domain.Personen.Planner;

public interface PlannerDao {
    Planner createItem(Planner item);

    Planner deleteItem(Planner item);

    void updateItem(Planner item);
}
