package software.ulpgc.sio_hiperdino_backend.dtos;

public record OrderDto(
    Integer id,
    String warehouseName,
    String storeName,
    String productName,
    Integer quantity,
    boolean delivered
) {
}
