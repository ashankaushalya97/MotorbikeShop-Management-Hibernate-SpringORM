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
        ResultSet rst = CrudUtil.execute("SELECT COUNT(username) FROM admin WHERE username=? AND password=? ",admin.getUsername(),admin.getPassword());
        if(rst.next()){
            if(rst.getDouble(1)==1){
                return true;
            }
        }
        return false;
    }
}
