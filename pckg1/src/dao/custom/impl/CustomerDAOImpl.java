package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;
import org.hibernate.Session;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private Session session;

    @Override
    public List<Customer> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer");
        List<Customer> customers= new ArrayList<>();
        while(rst.next()){
            customers.add(new Customer(rst.getString(1),rst.getString(2),rst.getString(3)));
        }
        return customers;
    }

    @Override
    public Customer find(String s) throws Exception {
        ResultSet rst  = CrudUtil.execute("SELECT * from customer where customerId=?", s);

        if(rst.next()){
            return new Customer(rst.getString(1),rst.getString(2),rst.getString(3));
        }
        return null;
    }

    @Override
    public void save(Customer entity) throws Exception {
        session.save(entity);
    }

    @Override
    public void update(Customer entity) throws Exception {
        session.merge(entity);
//        return CrudUtil.execute("UPDATE customer SET name=?,contact=? WHERE customerId=?",entity.getName(),entity.getContact(),entity.getCustomerId());
    }

    @Override
    public void delete(String s) throws Exception {
        session.delete(session.load(s,Customer.class));
//        return CrudUtil.execute("DELETE FROM customer WHERE customerId=?",s);
    }

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

    public void setSession(Session session) {
        this.session = session;
    }
}
