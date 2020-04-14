package be.fastned.application.dao.Base;

import be.fastned.application.domain.Base.AbsoluteBase;

import java.util.ArrayList;

public interface BaseDao {
    AbsoluteBase getItemById (String id);
    ArrayList<AbsoluteBase> getAllItems ();
    String getLastItemId();
    Boolean isTableEmpty ();
}
