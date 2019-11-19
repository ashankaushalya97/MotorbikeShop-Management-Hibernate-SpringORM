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
        ResultSet rst = CrudUtil.execute("SELECT itemId FROM item ORDER BY itemId DESC LIMIT 1");
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public List<Item> searchItems(String text) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM item\n" +
                "WHERE itemId LIKE ? OR categoryId LIKE ? OR\n" +
                "brand LIKE ? OR description LIKE ? OR qtyOnHand LIKE ? OR buyPrice LIKE ? OR unitPrice LIKE ?",text,text,text,text,text,text,text);
        List<Item> items = new ArrayList<>();
//        while(rst.next()){
//            items.add(new Item(rst.getString(1),rst.getString(2),rst.getString(3),
//                    rst.getString(4),rst.getInt(5),rst.getDouble(6),rst.getDouble(7)
//            ));
//        }
        return items;
    }
}
