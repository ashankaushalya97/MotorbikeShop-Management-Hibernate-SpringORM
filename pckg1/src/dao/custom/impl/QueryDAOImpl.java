package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QueryDAO;
import entity.CustomEntity;
import org.hibernate.Session;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    private Session session;

    @Override
    public List<CustomEntity> getOrderInfo() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT orders.orderId,orders.date,customer.customerId,customer.name,\n" +
                "sum((orderDetail.qty)*(orderDetail.unitPrice)) as total FROM ((orders \n" +
                "INNER JOIN orderDetail ON orders.orderId = orderDetail.orderId)\n" +
                "INNER JOIN customer ON orders.customerId = customer.customerId) group by orders.orderId");
        List<CustomEntity> orders = new ArrayList<>();
        while (rst.next()){
            orders.add(new CustomEntity(rst.getString(1),rst.getDate(2),rst.getString(3)
            ,rst.getString(4),rst.getDouble(5)));
        }
        return orders;
    }

    @Override
    public List<CustomEntity> searchOrder(String text) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT orders.orderId,orders.date,customer.customerId,customer.name,\n" +
                "sum((orderDetail.qty)*(orderDetail.unitPrice)) FROM ((orders \n" +
                "INNER JOIN orderDetail ON orders.orderId = orderDetail.orderId)\n" +
                "INNER JOIN customer ON orders.customerId = customer.customerId) group by orders.orderId having orders.orderId LIKE ? OR orders.date LIKE ? OR\n" +
                "customer.customerId LIKE ? OR customer.name LIKE ? OR\n" +
                "sum((orderDetail.qty)*(orderDetail.unitPrice)) LIKE ?",text,text,text,text,text);
        List<CustomEntity> orders = new ArrayList<>();
        while (rst.next()){
            orders.add(new CustomEntity(rst.getString(1),rst.getDate(2),rst.getString(3)
                    ,rst.getString(4),rst.getDouble(5)));
        }
        return orders;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
