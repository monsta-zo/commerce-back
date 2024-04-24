package org.example.commerceback.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.commerceback.cart.Cart;
import org.example.commerceback.user.User;

import java.util.List;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String info;
    @Column(nullable = false)
    private Long price;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    public void updateProduct(ProductRequest.AddProductDTO productDTO) {
        this.name = productDTO.name();
        this.info = productDTO.info();
        this.price = productDTO.price();
    }

}
