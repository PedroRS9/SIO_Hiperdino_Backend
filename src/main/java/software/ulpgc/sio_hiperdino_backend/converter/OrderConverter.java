package software.ulpgc.sio_hiperdino_backend.converter;

import org.springframework.stereotype.Component;
import software.ulpgc.sio_hiperdino_backend.dtos.OrderDto;
import software.ulpgc.sio_hiperdino_backend.entities.Order;

import java.util.List;

@Component
public class OrderConverter {
    public OrderDto fromOrder(Order order){
        return new OrderDto(
                order.getId(),
                order.getWarehouse().getName(),
                order.getStore().getName(),
                order.getProduct().getName(),
                order.getQuantity(),
                order.isDelivered()
        );
    }

    public List<OrderDto> fromOrders(List<Order> orders) {
        return orders.stream()
                .map(this::fromOrder)
                .toList();
    }
}
