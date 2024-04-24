package org.example.commerceback.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.commerceback.cart.Cart;

@Entity
@DiscriminatorValue("B")
@NoArgsConstructor
@SuperBuilder
@Data
public class Buyer extends User {

    @OneToOne()
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
