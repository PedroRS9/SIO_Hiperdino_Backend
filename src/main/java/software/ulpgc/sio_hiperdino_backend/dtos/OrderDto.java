package software.ulpgc.sio_hiperdino_backend.dtos;

public record OrderDto(
    Integer id,
    WarehouseDto warehouse,
    StoreDto store,
    String productName,
    Integer quantity,
    boolean delivered
) {
}
