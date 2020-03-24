package be.fastned.application.dao;

import be.fastned.application.domain.Installateur;

public interface InstallateurDao {
    public Installateur addInstallateur(Installateur installateur);

    public Installateur deleteInstallateur(Installateur installateur);

    public Installateur getInstallateurById (long id);
}
