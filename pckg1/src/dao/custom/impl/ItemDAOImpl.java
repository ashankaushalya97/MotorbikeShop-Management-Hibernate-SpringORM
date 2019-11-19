package dao.custom.impl;

import dao.CrudDAOImpl;
import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Customer;
import entity.Item;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl extends CrudDAOImpl<Item,String> implements ItemDAO {

    @Override
    public String getLastItemId() throws Exception {
        return (String) session.createNativeQuery("SELECT itemId FROM item ORDER BY itemId DESC LIMIT 1").uniqueResult();
    }

    @Override
    public List<Item> searchItems(String text) throws Exception {
        return session.createNativeQuery("SELECT * FROM item WHERE itemId LIKE ?1 OR categoryId LIKE ?2 OR brand LIKE ?3 OR description LIKE ?4 OR qtyOnHand LIKE ?5 OR buyPrice LIKE ?6 OR unitPrice LIKE ?7")
            .setParameter(1,text).setParameter(2,text).setParameter(3,text).setParameter(4,text).setParameter(5,text)
                .setParameter(6,text).setParameter(7,text).list();
    }
}
