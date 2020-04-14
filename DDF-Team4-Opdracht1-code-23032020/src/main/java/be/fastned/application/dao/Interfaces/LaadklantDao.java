package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Personen.Laadklant;

public interface LaadklantDao {
    public Laadklant addLaadklant(Laadklant laadklant);

    public Laadklant deleteLaadklant(Laadklant laadklant);

    public Laadklant getLaadklantById (long id);
}
