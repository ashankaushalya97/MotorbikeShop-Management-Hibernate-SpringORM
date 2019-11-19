package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.AdminDAO;
import entity.Admin;
import org.hibernate.Session;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    
    private Session session;

    @Override
    public List<Admin> findAll() throws Exception {

        return session.createQuery("from Admin").list();
        
    }

    @Override
    public Admin find(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM admin WHERE username=?");
        if(rst.next()){
            return new Admin(rst.getString(1),rst.getString(2));
        }
        return null;
    }

    @Override
    public void save(Admin entity) throws Exception {
//        return CrudUtil.execute("INSERT INTO admin VALUES (?,?)",entity.getUsername(),entity.getPassword());
    }

    @Override
    public void update(Admin entity) throws Exception {
//        return CrudUtil.execute("UPDATE admin SET password=? WHERE username=?",entity.getPassword(),entity.getUsername());
    }

    @Override
    public void delete(String s) throws Exception {
//        return CrudUtil.execute("DELETE FROM admin WHERE username=?",s);
    }

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

    public void setSession(Session session) {
        this.session = session;
    }
}
