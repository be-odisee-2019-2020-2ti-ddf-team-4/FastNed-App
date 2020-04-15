package be.fastned.application.dao.Base;

import be.fastned.application.domain.Base.EntiteitBaseImpl;

import java.util.ArrayList;

public interface BaseDao {
    EntiteitBaseImpl getItemById (String id);
    ArrayList<EntiteitBaseImpl> getAllItems ();
    String getLastItemId();
    Boolean isTableEmpty ();
}
