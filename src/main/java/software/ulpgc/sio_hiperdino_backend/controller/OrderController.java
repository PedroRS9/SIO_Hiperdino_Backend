package software.ulpgc.sio_hiperdino_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.ulpgc.sio_hiperdino_backend.converter.OrderConverter;
import software.ulpgc.sio_hiperdino_backend.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    public OrderController(OrderService orderService, OrderConverter orderConverter) {
        this.orderService = orderService;
        this.orderConverter = orderConverter;
    }

    @GetMapping
    public ResponseEntity<Object> getOrders() {
        return ResponseEntity.ok().body(orderConverter.fromOrders(orderService.getOrders()));
    }

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestParam Integer storeId, @RequestParam Integer productId, @RequestParam Integer quantity) {
        try{
            orderService.createOrder(storeId, productId, quantity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating order: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Order created successfully");
    }

    @PutMapping("/{orderId}/delivered")
    public ResponseEntity<Object> markOrderAsDelivered(@PathVariable Integer orderId) {
        try {
            orderService.markOrderAsDelivered(orderId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error marking order as delivered: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Order marked as delivered successfully");
    }
}
