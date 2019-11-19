package business.custom.impl;

import DB.HibernateUtil;
import business.custom.ItemBO;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Category;
import entity.Item;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);

    @Override
    public void saveItem(ItemDTO item) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            itemDAO.setSession(session);
            session.beginTransaction();
            itemDAO.save(new Item(item.getItemId(),session.load(Category.class,item.getCategoryId()),item.getBrand(),item.getDescription(),item.getQtyOnHand(),item.getBuyPrice(),item.getUnitPrice()));

            session.getTransaction().commit();
       }

    }

    @Override
    public void updateItem(ItemDTO item) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            itemDAO.setSession(session);
            session.beginTransaction();
            itemDAO.update(new Item(item.getItemId(),session.load(Category.class,item.getCategoryId()),item.getBrand(),item.getDescription(),item.getQtyOnHand(),item.getBuyPrice(),item.getUnitPrice()));

            session.getTransaction().commit();
        }

    }

    @Override
    public void deleteItem(String id) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            itemDAO.setSession(session);
            session.beginTransaction();
            itemDAO.delete(id);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<ItemDTO> findAllItems() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            itemDAO.setSession(session);
            session.beginTransaction();
            List<Item> all = itemDAO.findAll();
            List<ItemDTO> itemDTOS = new ArrayList<>();
            for (Item item : all) {
            itemDTOS.add(new ItemDTO(item.getItemId(),item.getCategory().getCategoryId(),item.getBrand(),item.getDescription(),item.getQtyOnHand(),item.getBuyPrice(),item.getUnitPrice()));
            }
            session.getTransaction().commit();
            return itemDTOS;
        }
    }

    @Override
    public String getLastItemId() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            itemDAO.setSession(session);
            session.beginTransaction();
            String lastItemId = itemDAO.getLastItemId();
            session.getTransaction().commit();
            return lastItemId;
        }
    }

    @Override
    public List<ItemDTO> searchItems(String text) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            itemDAO.setSession(session);
            session.beginTransaction();
            List<Item> search = itemDAO.searchItems(text);
            List<ItemDTO> items = new ArrayList<>();
            for (Item item : search) {
                items.add(new ItemDTO(item.getItemId(),item.getCategory().getCategoryId(),item.getBrand(),item.getDescription(),item.getQtyOnHand(),item.getBuyPrice(),item.getUnitPrice()));
            }
            session.getTransaction().commit();
            return items;
        }
    }
}
