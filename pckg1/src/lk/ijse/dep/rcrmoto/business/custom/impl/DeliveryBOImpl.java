package lk.ijse.dep.rcrmoto.business.custom.impl;

import lk.ijse.dep.rcrmoto.business.custom.DeliveryBO;
import lk.ijse.dep.rcrmoto.dao.custom.DeliveryDAO;
import lk.ijse.dep.rcrmoto.dto.DeliveryDTO;
import lk.ijse.dep.rcrmoto.entity.Delivery;
import lk.ijse.dep.rcrmoto.entity.DeliveryPK;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class DeliveryBOImpl implements DeliveryBO {
    @Autowired
    DeliveryDAO deliveryDAO;
    @Override
    public void saveDelivery(DeliveryDTO delivery) throws Exception {
            deliveryDAO.save(new Delivery(delivery.getDeliveryId(),delivery.getOrderId(),delivery.getAddress(),delivery.getStates()));

    }

    @Override
    public void updateDelivery(DeliveryDTO delivery) throws Exception {
            deliveryDAO.update(new Delivery(delivery.getDeliveryId(),delivery.getOrderId(),delivery.getAddress(),delivery.getStates()));
    }

    @Override
    public void deleteDelivery(String deliveryId, String orderId) throws Exception {
            deliveryDAO.delete(new DeliveryPK(deliveryId,orderId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<DeliveryDTO> findAllDeliveries() throws Exception {
            List<Delivery> all = deliveryDAO.findAll();
            List<DeliveryDTO> deliveryDTOS = new ArrayList<>();
            for (Delivery delivery : all) {
                deliveryDTOS.add(new DeliveryDTO(delivery.getDeliveryPK().getDeliveryId(),delivery.getDeliveryPK().getOrderId(),delivery.getAddress(),delivery.getStates()));
            }
            return deliveryDTOS;
    }

    @Transactional(readOnly = true)
    @Override
    public String getLastDeliveryId() throws Exception {
        return deliveryDAO.getLastDeliveryId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getOrderIds() throws Exception {
            List<Delivery> all = deliveryDAO.findAll();
            List<String> ids = new ArrayList<>();
            for (Delivery delivery : all) {
                ids.add(delivery.getDeliveryPK().getOrderId());
            }
            return ids;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DeliveryDTO> searchDelivery(String text) throws Exception {
            List<Delivery> search = deliveryDAO.searchDelivery(text);
            List<DeliveryDTO> deliveries = new ArrayList<>();
            for (Delivery delivery : search) {
                deliveries.add(new DeliveryDTO(delivery.getDeliveryPK().getDeliveryId(),delivery.getDeliveryPK().getOrderId(),delivery.getAddress(),delivery.getStates()));
            }
            return deliveries;
    }
}
