package be.fastned.application.dao.Interfaces;

import be.fastned.application.domain.AbsoluteBase;
import be.fastned.application.domain.Oplossing;
import be.fastned.application.service.AppConfig;
import be.fastned.application.service.AppRunner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public interface BaseDao {
    AbsoluteBase getItemById (String id);
    ArrayList<AbsoluteBase> getAllItems ();
    String getLastItemId();
    Boolean isTableEmpty ();
}
