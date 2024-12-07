package software.ulpgc.sio_hiperdino_backend.converter;

import org.springframework.stereotype.Component;
import software.ulpgc.sio_hiperdino_backend.dtos.InventoryDto;
import software.ulpgc.sio_hiperdino_backend.entities.Inventory;

import java.util.List;

@Component
public class InventoryConverter {
    private final ProductConverter productConverter;
    private final StoreConverter storeConverter;

    public InventoryConverter(ProductConverter productConverter, StoreConverter storeConverter) {
        this.productConverter = productConverter;
        this.storeConverter = storeConverter;
    }

    public InventoryDto fromInventory(Inventory inventory) {
        return new InventoryDto(
                inventory.getId(),
                productConverter.fromProduct(inventory.getProduct()),
                storeConverter.fromStore(inventory.getStore()),
                inventory.getQuantity()
        );
    }

    public List<InventoryDto> fromInventories(List<Inventory> inventories) {
        return inventories.stream()
                .map(this::fromInventory)
                .toList();
    }
}
