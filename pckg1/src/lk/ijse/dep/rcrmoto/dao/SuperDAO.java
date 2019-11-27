package lk.ijse.dep.rcrmoto.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface SuperDAO {

    Session getSession();
}
