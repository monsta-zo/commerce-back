package org.example.commerceback.order.order_product;

import org.example.commerceback.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    List<OrderProduct> findAllByOrderId(Long id);
    List<OrderProduct> findAllByProductId(Long id);
}
