package lk.ijse.dep.rcrmoto.business.custom.impl;

import lk.ijse.dep.rcrmoto.DB.HibernateUtil;
import lk.ijse.dep.rcrmoto.business.custom.CustomerBO;
import lk.ijse.dep.rcrmoto.dao.DAOFactory;
import lk.ijse.dep.rcrmoto.dao.DAOTypes;
import lk.ijse.dep.rcrmoto.dao.custom.CustomerDAO;
import lk.ijse.dep.rcrmoto.dto.CustomerDTO;
import lk.ijse.dep.rcrmoto.entity.Customer;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public void saveCustomer(CustomerDTO customer) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            customerDAO.setSession(session);
            session.beginTransaction();

            customerDAO.save(new Customer(customer.getCustomerId(),customer.getName(),customer.getContact()));

            session.getTransaction().commit();
        }
    }

    @Override
    public void updateCustomer(CustomerDTO customer) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            customerDAO.setSession(session);
            session.beginTransaction();

            customerDAO.update(new Customer(customer.getCustomerId(),customer.getName(),customer.getContact()));

            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteCustomer(String id) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            customerDAO.setSession(session);
            session.beginTransaction();

            customerDAO.delete(id);
            session.getTransaction().commit();
        }
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            customerDAO.setSession(session);
            session.beginTransaction();
            List<Customer> search = customerDAO.searchCustomers(text);
            List<CustomerDTO> customers = new ArrayList<>();
            for (Customer customer : search) {
                customers.add(new CustomerDTO(customer.getCustomerId(),customer.getName(),customer.getContact()));
            }
            session.getTransaction().commit();
            return customers;
        }
    }
}
