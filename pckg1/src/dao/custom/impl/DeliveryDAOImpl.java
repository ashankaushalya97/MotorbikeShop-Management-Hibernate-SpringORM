package dao.custom.impl;

import dao.CrudDAOImpl;
import dao.CrudUtil;
import dao.custom.DeliveryDAO;
import entity.Delivery;
import entity.DeliveryPK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAOImpl extends CrudDAOImpl<Delivery,DeliveryPK> implements DeliveryDAO {

    @Override
    public String getLastDeliveryId() throws Exception {
        ResultSet rst =CrudUtil.execute("SELECT deliveryId FROM delivery ORDER BY deliveryId DESC LIMIT 1");
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public List<Delivery> searchDelivery(String text) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM delivery WHERE deliveryId LIKE ? OR " +
                "orderId LIKE ? OR address LIKE ? OR states LIKE ?",text,text,text,text);
        List<Delivery> search = new ArrayList<>();
        while (rst.next()){
            search.add(new Delivery(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }

        return search;
    }
}
