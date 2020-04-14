package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Personen.Planner;

public interface PlannerDao {
    public Planner addPlanner(Planner afspraak);

    public Planner deletePlanner(Planner afspraak);

    public Planner getPlannerById (long id);

}
