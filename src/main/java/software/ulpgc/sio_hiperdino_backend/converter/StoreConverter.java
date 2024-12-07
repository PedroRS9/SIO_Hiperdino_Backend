package software.ulpgc.sio_hiperdino_backend.converter;

import org.springframework.stereotype.Component;
import software.ulpgc.sio_hiperdino_backend.dtos.StoreDto;
import software.ulpgc.sio_hiperdino_backend.entities.Store;

@Component
public class StoreConverter {
    public StoreDto fromStore(Store store) {
        return new StoreDto(
                store.getId(),
                store.getName(),
                store.getLocation()
        );
    }
}
