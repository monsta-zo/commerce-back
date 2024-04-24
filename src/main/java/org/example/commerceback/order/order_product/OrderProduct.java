package org.example.commerceback.order.order_product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.commerceback.order.Order;
import org.example.commerceback.product.Product;

@Entity
@Table(name = "order_product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProduct {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private OrderProductStatus status;

    public void updateStatus(OrderProductStatus status) {
        this.status = status;
    }
}
