package dao.custom.impl;

import dao.CrudDAOImpl;
import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;
import org.hibernate.Session;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl extends CrudDAOImpl<Customer,String> implements CustomerDAO {

    @Override
    public String getLastCustomerId() throws Exception {
       return (String) session.createNativeQuery("SELECT customer_id FROM Customer order by customer_id desc LIMIT 1").uniqueResult();
    }

    @Override
    public List<Customer> searchCustomers(String text) throws Exception {
        return session.createNativeQuery("SELECT * FROM Customer WHERE customer_id like ?1 or name like ?2 or contact like ?3").setParameter(1,text).setParameter(2,text).setParameter(3,text).list();

    }
}
