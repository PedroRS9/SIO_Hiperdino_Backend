package software.ulpgc.sio_hiperdino_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.ulpgc.sio_hiperdino_backend.converter.InventoryConverter;
import software.ulpgc.sio_hiperdino_backend.exceptions.EntityNotFoundException;
import software.ulpgc.sio_hiperdino_backend.services.InventoryService;
import software.ulpgc.sio_hiperdino_backend.services.impl.InventoryServiceImpl;

@RestController
@RequestMapping("inventories")
public class InventoryController {
    private final InventoryService inventoryService;
    private final InventoryConverter inventoryConverter;

    public InventoryController(InventoryServiceImpl inventoryService, InventoryConverter inventoryConverter) {
        this.inventoryService = inventoryService;
        this.inventoryConverter = inventoryConverter;
    }

    @GetMapping
    public ResponseEntity<Object> getInventories() {
        return ResponseEntity.ok(inventoryConverter.fromInventories(inventoryService.getInventories()));
    }

    @GetMapping("{inventoryId}")
    public ResponseEntity<Object> getInventoryById(@PathVariable int inventoryId) {
        return ResponseEntity.ok(inventoryConverter.fromInventory(inventoryService.getInventoryById(inventoryId)));
    }

    @PutMapping("{inventoryId}/decrease")
    public ResponseEntity<Object> decreaseQuantity(@PathVariable int inventoryId) {
        try {
            inventoryService.decreaseQuantity(inventoryId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(String.format("Inventory with id %d not found", inventoryId));
        }
    }

    @PutMapping("{inventoryId}/increase")
    public ResponseEntity<Object> increaseQuantity(@PathVariable int inventoryId) {
        try {
            inventoryService.increaseQuantity(inventoryId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(String.format("Inventory with id %d not found", inventoryId));
        }
    }
}
