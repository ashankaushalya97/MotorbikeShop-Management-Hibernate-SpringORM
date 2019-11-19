package business.custom;

import business.SuperBO;
import dto.DeliveryDTO;
import entity.Delivery;

import java.util.List;

public interface DeliveryBO extends SuperBO {

    void saveDelivery(DeliveryDTO delivery)throws Exception;

    void updateDelivery(DeliveryDTO delivery)throws Exception;

    void deleteDelivery(String deliveryId,String orderId)throws Exception;

    List<DeliveryDTO> findAllDeliveries()throws Exception;

    String getLastDeliveryId()throws Exception;

    List<String> getOrderIds()throws Exception;

    List<DeliveryDTO> searchDelivery(String text)throws Exception;
}
