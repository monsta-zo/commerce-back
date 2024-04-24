package org.example.commerceback.order;

import org.example.commerceback.order.order_product.OrderProductStatus;
import org.example.commerceback.product.Product;
import org.example.commerceback.product.ProductResponse;
import org.example.commerceback.user.Buyer;

import java.util.List;

public class OrderResponse {
    public record OrderListDTO (
            List<OrderDTO> orders
    ) {}

    public record OrderDTO (
            Long orderId,
            List<OrderedProductDTO> products
    ) { }

    public record OrderedProductDTO (
            Long id,
            String name,
            String info,
            Long price,
            OrderProductStatus status
    ) {
        public OrderedProductDTO (Product product, OrderProductStatus status) {
            this(product.getId(), product.getName(), product.getInfo(), product.getPrice(), status);
        }
    }

    public record OrderProductListDTO (
            List<OrderedProductWithBuyerDTO> products
    ) {
        public record  OrderedProductWithBuyerDTO (
                Long id,
                String name,
                String info,
                Long price,
                Long orderProductId,
                OrderProductStatus status,
                String buyerName
        ) {
            public OrderedProductWithBuyerDTO (Product product, Long orderProductId,OrderProductStatus status, Buyer buyer) {
                this(product.getId(), product.getName(), product.getInfo(), product.getPrice(), orderProductId, status, buyer.getName());
            }
         }
    }
}
