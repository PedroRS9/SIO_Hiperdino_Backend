package software.ulpgc.sio_hiperdino_backend.dtos;

public record ProductDto (
    Integer id,
    String name,
    Double price,
    Integer minStock,
    Integer maxStock
) {
}
