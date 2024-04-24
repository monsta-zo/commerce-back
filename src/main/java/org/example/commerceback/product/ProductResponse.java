package org.example.commerceback.product;

import java.util.List;

public class ProductResponse {

    public record AddProductDTO (
            Long id
    ) {}

    public record ProductListDTO (
            List<ProductDTO> products
    ) {
        public record ProductDTO(
                Long id,
                String name,
                String info,
                Long price

        ) {
            public ProductDTO(Product product) {
                this(product.getId(), product.getName(), product.getName(), product.getPrice());
            }
        }
    }

    public record ProductSellerDTO(
        Long id,
        String name,
        String info,
        Long price,
        Boolean isSeller
    ) {
        public ProductSellerDTO(Product product, Boolean isSeller) {
            this(product.getId(), product.getName(), product.getName(), product.getPrice(), isSeller);
        }
    }
}
