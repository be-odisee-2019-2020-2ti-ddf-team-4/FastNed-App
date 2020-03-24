package be.fastned.application.dao;

import be.fastned.application.domain.Afspraak;

public interface AfspraakDao {
    public Afspraak addAfspraak(Afspraak afspraak);

    public Afspraak deleteAfspraak(Afspraak afspraak);

    public Afspraak getAfspraakById (long id);

    public void updateAfspraak (Afspraak afspraak);
}
