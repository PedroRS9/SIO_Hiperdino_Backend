package software.ulpgc.sio_hiperdino_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.ulpgc.sio_hiperdino_backend.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}