package lk.ijse.dep.rcrmoto.business.custom.impl;

import lk.ijse.dep.rcrmoto.business.custom.CustomerBO;
import lk.ijse.dep.rcrmoto.dao.custom.CustomerDAO;
import lk.ijse.dep.rcrmoto.dto.CustomerDTO;
import lk.ijse.dep.rcrmoto.entity.Customer;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public void saveCustomer(CustomerDTO customer) throws Exception {
            customerDAO.save(new Customer(customer.getCustomerId(),customer.getName(),customer.getContact()));
    }

    @Override
    public void updateCustomer(CustomerDTO customer) throws Exception {
            customerDAO.update(new Customer(customer.getCustomerId(),customer.getName(),customer.getContact()));
    }

    @Override
    public void deleteCustomer(String id) throws Exception {
            customerDAO.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findAllCustomers() throws Exception {
            List<Customer> all = customerDAO.findAll();
            List<CustomerDTO> customerDTOS = new ArrayList<>();
            for (Customer customer : all) {
                customerDTOS.add(new CustomerDTO(customer.getCustomerId(),customer.getName(),customer.getContact()));
            }
            return customerDTOS;
    }

    @Transactional(readOnly = true)
    @Override
    public String getLastCustomerId() throws Exception {
             return customerDAO.getLastCustomerId();
    }

    @Transactional(readOnly = true)
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
