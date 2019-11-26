package lk.ijse.dep.rcrmoto.business.custom.impl;

import lk.ijse.dep.rcrmoto.DB.HibernateUtil;
import lk.ijse.dep.rcrmoto.business.custom.DeliveryBO;
import lk.ijse.dep.rcrmoto.dao.DAOFactory;
import lk.ijse.dep.rcrmoto.dao.DAOTypes;
import lk.ijse.dep.rcrmoto.dao.custom.DeliveryDAO;
import lk.ijse.dep.rcrmoto.dto.DeliveryDTO;
import lk.ijse.dep.rcrmoto.entity.Delivery;
import lk.ijse.dep.rcrmoto.entity.DeliveryPK;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DeliveryBOImpl implements DeliveryBO {
    @Autowired
    DeliveryDAO deliveryDAO;
    @Override
    public void saveDelivery(DeliveryDTO delivery) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            deliveryDAO.setSession(session);
            session.beginTransaction();

            session.save(new Delivery(delivery.getDeliveryId(),delivery.getOrderId(),delivery.getAddress(),delivery.getStates()));
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateDelivery(DeliveryDTO delivery) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            deliveryDAO.setSession(session);
            session.beginTransaction();

            session.merge(new Delivery(delivery.getDeliveryId(),delivery.getOrderId(),delivery.getAddress(),delivery.getStates()));
            session.getTransaction().commit();
        }

    }

    @Override
    public void deleteDelivery(String deliveryId, String orderId) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            deliveryDAO.setSession(session);
            session.beginTransaction();
            deliveryDAO.delete(new DeliveryPK(deliveryId,orderId));
            session.getTransaction().commit();
        }

    }

    @Override
    public List<DeliveryDTO> findAllDeliveries() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            deliveryDAO.setSession(session);
            session.beginTransaction();

            List<Delivery> all = deliveryDAO.findAll();
            List<DeliveryDTO> deliveryDTOS = new ArrayList<>();
            for (Delivery delivery : all) {
                deliveryDTOS.add(new DeliveryDTO(delivery.getDeliveryPK().getDeliveryId(),delivery.getDeliveryPK().getOrderId(),delivery.getAddress(),delivery.getStates()));
            }
            session.getTransaction().commit();
            return deliveryDTOS;
        }
    }

    @Override
    public String getLastDeliveryId() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            deliveryDAO.setSession(session);
            session.beginTransaction();
            String id= deliveryDAO.getLastDeliveryId();
            session.getTransaction().commit();
            return id;
        }
    }

    @Override
    public List<String> getOrderIds() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            deliveryDAO.setSession(session);
            session.beginTransaction();
            List<Delivery> all = deliveryDAO.findAll();
            List<String> ids = new ArrayList<>();
            for (Delivery delivery : all) {
                ids.add(delivery.getDeliveryPK().getOrderId());
            }
            session.getTransaction().commit();
            return ids;
        }
    }

    @Override
    public List<DeliveryDTO> searchDelivery(String text) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            deliveryDAO.setSession(session);
            session.beginTransaction();
            List<Delivery> search = deliveryDAO.searchDelivery(text);
            List<DeliveryDTO> deliveries = new ArrayList<>();
            for (Delivery delivery : search) {
                deliveries.add(new DeliveryDTO(delivery.getDeliveryPK().getDeliveryId(),delivery.getDeliveryPK().getOrderId(),delivery.getAddress(),delivery.getStates()));
            }
            session.getTransaction().commit();
            return deliveries;
        }
    }
}
