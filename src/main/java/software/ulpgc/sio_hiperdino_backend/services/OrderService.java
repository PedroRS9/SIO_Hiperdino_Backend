package software.ulpgc.sio_hiperdino_backend.services;

import software.ulpgc.sio_hiperdino_backend.entities.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();
    void createOrder(Integer storeId, Integer productId, Integer quantity);

    void markOrderAsDelivered(Integer orderId);
}
