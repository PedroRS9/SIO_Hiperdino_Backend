package software.ulpgc.sio_hiperdino_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.ulpgc.sio_hiperdino_backend.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
