package business.custom.impl;

import DB.HibernateUtil;
import business.custom.OrderBO;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.ItemDAO;
import dao.custom.OrderDetailDAO;
import dao.custom.OrdersDAO;
import dao.custom.QueryDAO;
import dto.OrderDTO;
import dto.OrderDTO2;
import entity.Admin;
import entity.CustomEntity;
import entity.Orders;
import org.hibernate.Session;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrdersDAO ordersDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDERS);
    OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDERDETAIL);
    ItemDAO itemDAO =DAOFactory.getInstance().getDAO(DAOTypes.ITEM);
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);

    @Override
    public String getLastOrderId() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            ordersDAO.setSession(session);
            session.beginTransaction();
            String lastOrderId = ordersDAO.getLastOrderId();
            session.getTransaction().commit();
            return lastOrderId;
        }
    }

    @Override
    public boolean placeOrder(OrderDTO order) throws Exception {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        try {
//            connection.setAutoCommit(false);
//
////            boolean result = ordersDAO.save(new Orders(order.getOrderId(),order.getDate(),order.getCustomerId()));
//
//            if(!result){
//                connection.rollback();
//                System.out.println("saveOrder");
//                throw new RuntimeException("Something went wrong!");
//            }
//
//            for (OrderDetailDTO orderDetails : order.getOrderDetail() ) {
//
//                result = orderDetailDAO.save(new OrderDetail(order.getOrderId(),orderDetails.getItemId(),orderDetails.getQty(),orderDetails.getUnitPrice()));
//
//                if(!result){
//                    System.out.println("Order Details");
//                    connection.rollback();
//                    throw new RuntimeException("Something went wrong!");
//                }
//
//                Item item = itemDAO.find(orderDetails.getItemId());
//                int qty=item.getQtyOnHand()-orderDetails.getQty();
//                item.setQtyOnHand(qty);
//                result = itemDAO.update(item);
//
//                if(!result){
//                    connection.rollback();
//                    System.out.println("Item");
//                    throw new RuntimeException("Something went wrong!");
//                }
//            }
//            connection.commit();
//            return true;
//
//        }catch (Throwable e){
//            connection.rollback();
//            return false;
//        }finally {
//            connection.setAutoCommit(true);
//        }
        return Boolean.parseBoolean(null);
    }

    @Override
    public List<String> getAllOrderIDs() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            ordersDAO.setSession(session);
            session.beginTransaction();
            List<Orders> allOrders= ordersDAO.findAll();
            List<String> ids = new ArrayList<>();
            for (Orders allOrder : allOrders) {
                ids.add(allOrder.getOrderId());
            }
            session.getTransaction().commit();
            return ids;
        }
    }

    @Override
    public List<OrderDTO2> getOrderInfo() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            ordersDAO.setSession(session);
            session.beginTransaction();
            List<CustomEntity> orders = queryDAO.getOrderInfo();
            List<OrderDTO2> all = new ArrayList<>();
            for (CustomEntity order : orders) {
                all.add(new OrderDTO2(order.getOrderId(),order.getDate(),order.getCustomerId(),order.getName(),order.getTotal()));
            }
            session.getTransaction().commit();
            return all;
        }
    }

    @Override
    public List<OrderDTO2> searchOrder(String text) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            ordersDAO.setSession(session);
            session.beginTransaction();
            List<CustomEntity> orders = queryDAO.searchOrder(text);
            List<OrderDTO2> all = new ArrayList<>();
            for (CustomEntity order : orders) {
                all.add(new OrderDTO2(order.getOrderId(),order.getDate(),order.getCustomerId(),order.getName(),order.getTotal()));
            }
            session.getTransaction().commit();
            return all;
        }
    }


}
