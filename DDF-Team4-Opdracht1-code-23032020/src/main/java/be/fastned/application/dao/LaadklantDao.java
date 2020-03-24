package be.fastned.application.dao;

import be.fastned.application.domain.Laadklant;

public interface LaadklantDao {
    public Laadklant addLaadklant(Laadklant laadklant);

    public Laadklant deleteLaadklant(Laadklant laadklant);

    public Laadklant getLaadklantById (long id);
}
