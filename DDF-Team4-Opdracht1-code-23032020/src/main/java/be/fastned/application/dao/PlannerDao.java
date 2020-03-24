package be.fastned.application.dao;

import be.fastned.application.domain.Planner;

public interface PlannerDao {
    public Planner addPlanner(Planner afspraak);

    public Planner deletePlanner(Planner afspraak);

    public Planner getPlannerById (long id);

}
