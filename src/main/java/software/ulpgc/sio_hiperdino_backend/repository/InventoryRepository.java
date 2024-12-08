package software.ulpgc.sio_hiperdino_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.ulpgc.sio_hiperdino_backend.entities.Inventory;
import software.ulpgc.sio_hiperdino_backend.entities.Product;
import software.ulpgc.sio_hiperdino_backend.entities.Store;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Optional<Inventory> findByProductAndStore(Product product, Store store);
}
