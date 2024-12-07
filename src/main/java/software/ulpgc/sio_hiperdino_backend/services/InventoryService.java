package software.ulpgc.sio_hiperdino_backend.services;

import software.ulpgc.sio_hiperdino_backend.entities.Inventory;

import java.util.List;

public interface InventoryService {
    List<Inventory> getInventories();
    Inventory getInventoryById(int inventoryId);
}
