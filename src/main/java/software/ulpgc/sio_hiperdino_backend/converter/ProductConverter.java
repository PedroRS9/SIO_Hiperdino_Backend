package software.ulpgc.sio_hiperdino_backend.converter;

import org.springframework.stereotype.Component;
import software.ulpgc.sio_hiperdino_backend.dtos.ProductDto;
import software.ulpgc.sio_hiperdino_backend.entities.Product;

@Component
public class ProductConverter {

    public ProductDto fromProduct(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getMinStock(),
                product.getMaxStock()
        );
    }
}
