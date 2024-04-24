package org.example.commerceback.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.commerceback.product.Product;

import java.util.List;

@Entity
@DiscriminatorValue("S")
@NoArgsConstructor
@SuperBuilder
public class Seller extends User{
    @OneToMany(mappedBy = "seller")
    private List<Product> products;

}
