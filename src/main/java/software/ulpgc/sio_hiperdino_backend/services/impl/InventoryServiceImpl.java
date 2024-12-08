package software.ulpgc.sio_hiperdino_backend.services.impl;

import org.springframework.stereotype.Service;
import software.ulpgc.sio_hiperdino_backend.entities.Inventory;
import software.ulpgc.sio_hiperdino_backend.entities.User;
import software.ulpgc.sio_hiperdino_backend.exceptions.EntityNotFoundException;
import software.ulpgc.sio_hiperdino_backend.repository.InventoryRepository;
import software.ulpgc.sio_hiperdino_backend.repository.OrderRepository;
import software.ulpgc.sio_hiperdino_backend.repository.UserRepository;
import software.ulpgc.sio_hiperdino_backend.services.EmailService;
import software.ulpgc.sio_hiperdino_backend.services.InventoryService;
import software.ulpgc.sio_hiperdino_backend.services.OrderService;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final EmailService emailService;
    private final OrderService orderService;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, UserRepository userRepository, OrderRepository orderRepository, EmailService emailService, OrderService orderService) {
        this.inventoryRepository = inventoryRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.emailService = emailService;
        this.orderService = orderService;
    }

    public List<Inventory> getInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(int inventoryId) {
        return inventoryRepository.findById(inventoryId).orElse(null);
    }

    @Override
    public void increaseQuantity(int inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new EntityNotFoundException("Inventory"));
        inventory.increaseQuantity();
        inventoryRepository.save(inventory);
    }

    @Override
    public void decreaseQuantity(int inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new EntityNotFoundException("Inventory"));
        inventory.decreaseQuantity();
        inventoryRepository.save(inventory);
    }

    @Override
    public void checkInventoryLevels() {
        inventoryRepository.findAll().stream()
                .filter(inventory -> inventory.getQuantity() < inventory.getProduct().getMinStock()
                        && noOrderExistsForStoreAndProduct(inventory)
                )
                .forEach(inventory -> {
                    int quantityToOrder = inventory.getProduct().getMinStock() - inventory.getQuantity();
                    orderService.createOrder(inventory.getStore().getId(), inventory.getProduct().getId(), quantityToOrder);
                    sendNotification(userRepository.findAll(), inventory);
                });
    }

    private boolean noOrderExistsForStoreAndProduct(Inventory inventory) {
        return orderRepository.findAll().stream().noneMatch(order -> order.getProduct().getId().equals(inventory.getProduct().getId())
                && order.getStore().getId().equals(inventory.getStore().getId())
                && !order.isDelivered()
        );
    }

    public void sendNotification(List<User> users, Inventory inventory) {
        List<String> recipients = users.stream()
                .map(User::getEmail)
                .toList();
        String subject = "Alerta de stock";
        String body = String.format("El producto %s de la tienda %s tiene un stock por debajo del mínimo (%d, y el mínimo es %d). Se ha realizado un pedido automáticamente.",
                inventory.getProduct().getName(), inventory.getStore().getName(), inventory.getQuantity(), inventory.getProduct().getMinStock());
        emailService.sendEmail(recipients, subject, body);
    }
}
