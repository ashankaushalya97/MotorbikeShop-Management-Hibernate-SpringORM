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
        ResultSet rst =CrudUtil.execute("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
