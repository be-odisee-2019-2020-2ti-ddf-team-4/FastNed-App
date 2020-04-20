package be.fastned.application.code.dao.Base;

import be.fastned.application.code.domain.Base.Entiteit;

import java.util.ArrayList;

public interface BaseDao {
    Entiteit getItemById(String id);
    ArrayList<Entiteit> getAllItems();
    String getLastItemId();
    Boolean isTableEmpty();
}
