package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.Personen.Installateur;

import java.util.ArrayList;

public interface InstallateurDao {
    // Create
    Installateur createInstallateur(Installateur installateur);

    // Read
    Installateur getInstallateurById (String id);
    ArrayList<Installateur> getAllInstallateurs ();
    String getLastItemId();
    Boolean isTableEmpty ();

    // Update
    Installateur updateInstallateur (Installateur installateur);

    // Delete
    Installateur deleteInstallateur(Installateur installateur);
}
