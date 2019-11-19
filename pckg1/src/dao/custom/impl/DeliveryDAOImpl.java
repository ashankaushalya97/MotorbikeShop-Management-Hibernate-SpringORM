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
       return (String) session.createNativeQuery("SELECT deliveryId FROM delivery ORDER BY deliveryId DESC LIMIT 1").uniqueResult();

    }

    @Override
    public List<Delivery> searchDelivery(String text) throws Exception {
       return session.createNativeQuery("SELECT * FROM delivery WHERE deliveryId LIKE ?1 OR orderId LIKE ?2 OR address LIKE ?3 OR states LIKE ?")
                .setParameter(1,text).setParameter(2,text).setParameter(3,text).list();
    }
}
