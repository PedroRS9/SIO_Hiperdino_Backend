package software.ulpgc.sio_hiperdino_backend.converter;

import org.springframework.stereotype.Component;
import software.ulpgc.sio_hiperdino_backend.dtos.OrderDto;
import software.ulpgc.sio_hiperdino_backend.entities.Order;

import java.util.List;

@Component
public class OrderConverter {
    private final StoreConverter storeConverter;
    private final WarehouseConverter warehouseConverter;

    public OrderConverter(StoreConverter storeConverter, WarehouseConverter warehouseConverter) {
        this.storeConverter = storeConverter;
        this.warehouseConverter = warehouseConverter;
    }

    public OrderDto fromOrder(Order order){
        return new OrderDto(
                order.getId(),
                warehouseConverter.fromWarehouse(order.getWarehouse()),
                storeConverter.fromStore(order.getStore()),
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
