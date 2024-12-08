package software.ulpgc.sio_hiperdino_backend.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.ulpgc.sio_hiperdino_backend.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
