package software.ulpgc.sio_hiperdino_backend.services.impl;

import org.springframework.stereotype.Service;
import software.ulpgc.sio_hiperdino_backend.entities.Inventory;
import software.ulpgc.sio_hiperdino_backend.entities.Order;
import software.ulpgc.sio_hiperdino_backend.entities.Store;
import software.ulpgc.sio_hiperdino_backend.entities.Warehouse;
import software.ulpgc.sio_hiperdino_backend.exceptions.EntityNotFoundException;
import software.ulpgc.sio_hiperdino_backend.repository.InventoryRepository;
import software.ulpgc.sio_hiperdino_backend.repository.OrderRepository;
import software.ulpgc.sio_hiperdino_backend.repository.WarehouseRepository;
import software.ulpgc.sio_hiperdino_backend.services.OrderService;
import software.ulpgc.sio_hiperdino_backend.services.repository.ProductRepository;
import software.ulpgc.sio_hiperdino_backend.services.repository.StoreRepository;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final InventoryRepository inventoryRepository;

    public OrderServiceImpl(OrderRepository orderRepository, StoreRepository storeRepository, ProductRepository productRepository, WarehouseRepository warehouseRepository, InventoryRepository inventoryRepository) {
        this.orderRepository = orderRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void createOrder(Integer storeId, Integer productId, Integer quantity) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new EntityNotFoundException("Store"));
        Warehouse warehouse = getClosestWarehouse(store);
        Order order = Order.builder()
                .warehouse(warehouse)
                .store(store)
                .product(productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product")))
                .quantity(quantity)
                .build();
        orderRepository.save(order);
    }

    @Override
    public void markOrderAsDelivered(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order"));
        order.setDelivered(true);
        orderRepository.save(order);
        updateInventoryQuantity(order);
    }

    private void updateInventoryQuantity(Order order) {
        Inventory inventory = inventoryRepository.findByProductAndStore(order.getProduct(), order.getStore()).orElse(null);
        if(inventory == null) return;
        inventory.setQuantity(inventory.getQuantity() + order.getQuantity());
        inventoryRepository.save(inventory);
    }

    private Warehouse getClosestWarehouse(Store store) {
        return warehouseRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("Warehouse"));
    }
}
