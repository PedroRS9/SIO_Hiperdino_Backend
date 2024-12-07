package software.ulpgc.sio_hiperdino_backend.services.impl;

import org.springframework.stereotype.Service;
import software.ulpgc.sio_hiperdino_backend.entities.Inventory;
import software.ulpgc.sio_hiperdino_backend.repository.InventoryRepository;
import software.ulpgc.sio_hiperdino_backend.services.InventoryService;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(int inventoryId) {
        return inventoryRepository.findById(inventoryId).orElse(null);
    }
}
