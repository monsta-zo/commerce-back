package org.example.commerceback.product;

public class ProductRequest {
    public record AddProductDTO (
            String name,
            String info,
            Long price
    ) { }
}
