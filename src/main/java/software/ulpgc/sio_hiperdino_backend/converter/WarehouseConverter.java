package software.ulpgc.sio_hiperdino_backend.converter;

import org.springframework.stereotype.Component;
import software.ulpgc.sio_hiperdino_backend.dtos.WarehouseDto;
import software.ulpgc.sio_hiperdino_backend.entities.Warehouse;

@Component
public class WarehouseConverter {
    public WarehouseDto fromWarehouse(Warehouse warehouse) {
        return new WarehouseDto(
                warehouse.getId(),
                warehouse.getName(),
                warehouse.getLocation()
        );
    }
}
