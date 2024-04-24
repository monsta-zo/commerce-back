package org.example.commerceback.cart;

import lombok.AllArgsConstructor;
import org.example.commerceback._core.errors.CustomException;
import org.example.commerceback._core.errors.ExceptionCode;
import org.example.commerceback.cart.cart_product.CartProduct;
import org.example.commerceback.cart.cart_product.CartProductRepository;
import org.example.commerceback.product.Product;
import org.example.commerceback.product.ProductRepository;
import org.example.commerceback.product.ProductResponse;
import org.example.commerceback.user.Buyer;
import org.example.commerceback.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;
    private final UserRepository userRepository;


    @Transactional
    public void addToCart(Long productId, Buyer buyer) {
        Cart cart = cartRepository.findByBuyerId(buyer.getId());
        Product product = productRepository.findById(productId).orElseThrow(()-> new CustomException(ExceptionCode.PRODUCT_NOT_FOUND));

        if(cart == null) {
            cart = Cart.builder()
                    .buyer(buyer)
                    .build();

            buyer.setCart(cart);
            cartRepository.save(cart);
            userRepository.save(buyer);
        }

        CartProduct cartProduct = CartProduct.builder()
                .cart(cart)
                .product(product)
                .build();

        cartProductRepository.save(cartProduct);
    }

    public ProductResponse.ProductListDTO getCart(Buyer buyer) {
        var cartId = buyer.getCart().getId();

        List<CartProduct> cartProducts = cartProductRepository.findAllByCartId(cartId);

        List<Product> products = cartProducts.stream().map(CartProduct::getProduct).toList();

        List<ProductResponse.ProductListDTO.ProductDTO> productDTOs = products
                .stream().map(ProductResponse.ProductListDTO.ProductDTO::new).toList();

        return new ProductResponse.ProductListDTO(productDTOs);
    }
}
