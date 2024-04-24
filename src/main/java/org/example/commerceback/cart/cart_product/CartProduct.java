package org.example.commerceback.cart.cart_product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.commerceback.cart.Cart;
import org.example.commerceback.product.Product;

@Entity
@Table(name = "Cart_Product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartProduct {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
