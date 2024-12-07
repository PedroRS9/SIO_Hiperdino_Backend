package software.ulpgc.sio_hiperdino_backend.dtos;

public record InventoryDto(
    Integer id,
    ProductDto product,
    StoreDto store,
    Integer quantity
) {
}
