package dao.custom.impl;

import dao.CrudDAOImpl;
import dao.CrudUtil;
import dao.custom.AdminDAO;
import entity.Admin;
import org.hibernate.Session;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl extends CrudDAOImpl<Admin,String> implements AdminDAO {

    @Override
    public boolean authentication(Admin admin) throws Exception {
        int result = (int) session.createNativeQuery("SELECT COUNT(username) FROM admin WHERE username=?1 AND password=?2").setParameter(1, admin.getUsername()).setParameter(2, admin.getPassword()).uniqueResult();
        if(result==1){
            return true;
        }
        return false;
    }
}
