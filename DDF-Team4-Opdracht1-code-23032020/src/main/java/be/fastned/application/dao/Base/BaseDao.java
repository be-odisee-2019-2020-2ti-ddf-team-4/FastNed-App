package be.fastned.application.dao.Base;

import be.fastned.application.domain.Base.Entiteit;
import be.fastned.application.domain.Base.EntiteitBaseImpl;

import java.util.ArrayList;

public interface BaseDao {
    Entiteit getItemById (String id);
    ArrayList<Entiteit> getAllItems ();
    String getLastItemId();
    Boolean isTableEmpty ();
}
