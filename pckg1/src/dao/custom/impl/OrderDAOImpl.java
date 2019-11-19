package dao.custom.impl;

import dao.CrudDAOImpl;
import dao.CrudUtil;
import dao.custom.OrdersDAO;
import entity.Orders;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl extends CrudDAOImpl<Orders,String> implements OrdersDAO {

    @Override
    public String getLastOrderId() throws Exception {
        return (String) session.createNativeQuery("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1").uniqueResult();
    }
}
