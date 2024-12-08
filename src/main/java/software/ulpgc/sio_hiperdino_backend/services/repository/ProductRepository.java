package software.ulpgc.sio_hiperdino_backend.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.ulpgc.sio_hiperdino_backend.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
