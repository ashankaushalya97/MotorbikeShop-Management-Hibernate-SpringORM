package business.custom.impl;

import DB.HibernateUtil;
import business.custom.CustomerBO;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);

    @Override
    public void saveCustomer(CustomerDTO customer) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            customerDAO.setSession(session);
            session.beginTransaction();

            customerDAO.save(new Customer(customer.getCustomerId(),customer.getName(),customer.getContact()));

            session.getTransaction().commit();
        }
//        return customerDAO.save(new Customer(customer.getCustomerId(),customer.getName(),customer.getContact()));
    }

    @Override
    public void updateCustomer(CustomerDTO customer) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            customerDAO.setSession(session);
            session.beginTransaction();

            customerDAO.update(new Customer(customer.getCustomerId(),customer.getName(),customer.getContact()));

            session.getTransaction().commit();
        }
//        return customerDAO.update(new Customer(customer.getCustomerId(),customer.getName(),customer.getContact()));
    }

    @Override
    public void deleteCustomer(String id) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            customerDAO.setSession(session);
            session.beginTransaction();

            customerDAO.delete(id);
            session.getTransaction().commit();
        }
        //        return customerDAO.delete(id);
    }

    @Override
    public List<CustomerDTO> findAllCustomers() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            customerDAO.setSession(session);
            session.beginTransaction();

            List<Customer> all = customerDAO.findAll();
            List<CustomerDTO> customerDTOS = new ArrayList<>();
            for (Customer customer : all) {
                customerDTOS.add(new CustomerDTO(customer.getCustomerId(),customer.getName(),customer.getContact()));
            }

            session.getTransaction().commit();
            return customerDTOS;
        }
    }

    @Override
    public String getLastCustomerId() throws Exception {

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            customerDAO.setSession(session);
            session.beginTransaction();

            String lastCustomerId = customerDAO.getLastCustomerId();

            session.getTransaction().commit();
            return lastCustomerId;
        }
    }

    @Override
    public List<CustomerDTO> searchCustomer(String text) throws Exception {
        List<Customer> search = customerDAO.searchCustomers(text);
        List<CustomerDTO> customers = new ArrayList<>();
        for (Customer customer : search) {
            customers.add(new CustomerDTO(customer.getCustomerId(),customer.getName(),customer.getContact()));
        }
        return customers;
    }
}
