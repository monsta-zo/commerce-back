package org.example.commerceback.order;

import org.example.commerceback.order.order_product.OrderProductStatus;

public class OrderRequest {
    public record updateOrderProductDTO (
            OrderProductStatus status
    ) { }
}
