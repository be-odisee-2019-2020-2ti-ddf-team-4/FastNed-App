package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Contract;
import be.fastned.application.domain.Personen.Planner;

public interface PlannerDao {
    public Planner createItem(Planner item);

    public Planner deleteItem(Planner item);

    public void updateItem (Planner item);
}
