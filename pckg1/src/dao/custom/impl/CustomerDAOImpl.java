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
        List<Customer> search= new ArrayList<>();
        ResultSet rst=CrudUtil.execute("SELECT * FROM customer WHERE customerId like ? or name like ? or contact like ?",text,text,text);
        while (rst.next()){
            search.add(new Customer(rst.getString(1),rst.getString(2),rst.getString(3)));
        }
        return search;
    }
}
