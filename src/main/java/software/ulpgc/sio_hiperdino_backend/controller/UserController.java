package software.ulpgc.sio_hiperdino_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping("{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable int userId) {
        return ResponseEntity.internalServerError().body("Not yet implemented");
    }
}
