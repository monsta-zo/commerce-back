package org.example.commerceback.order;

import lombok.AllArgsConstructor;
import org.example.commerceback._core.errors.CustomException;
import org.example.commerceback._core.errors.ExceptionCode;
import org.example.commerceback.cart.Cart;
import org.example.commerceback.cart.CartRepository;
import org.example.commerceback.cart.cart_product.CartProduct;
import org.example.commerceback.cart.cart_product.CartProductRepository;
import org.example.commerceback.order.order_product.OrderProduct;
import org.example.commerceback.order.order_product.OrderProductRepository;
import org.example.commerceback.order.order_product.OrderProductStatus;
import org.example.commerceback.product.Product;
import org.example.commerceback.product.ProductRepository;
import org.example.commerceback.product.ProductResponse;
import org.example.commerceback.user.Buyer;
import org.example.commerceback.user.Seller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    public void orderCart(Buyer buyer) {
        var buyerId = buyer.getId();
        Cart cart = cartRepository.findByBuyerId(buyerId);

        List<CartProduct> cartProducts = cartProductRepository.findAllByCartId(cart.getId());

        Order order = Order.builder()
                .buyer(buyer)
                .build();
        orderRepository.save(order);

        for(CartProduct cartProduct : cartProducts) {
            OrderProduct orderProduct = OrderProduct.builder()
                    .product(cartProduct.getProduct())
                    .order(order)
                    .status(OrderProductStatus.PENDING)
                    .build();

            orderProductRepository.save(orderProduct);
            cartProductRepository.deleteById(cartProduct.getId());
        }


    }

    public void orderProduct(Long productId, Buyer buyer) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException(ExceptionCode.PRODUCT_NOT_FOUND));

        Order order = Order.builder()
                .buyer(buyer)
                .build();

        orderRepository.save(order);

        OrderProduct orderProduct = OrderProduct.builder()
                .order(order)
                .product(product)
                .status(OrderProductStatus.PENDING)
                .build();

        orderProductRepository.save(orderProduct);
    }

    public OrderResponse.OrderListDTO viewOrdersBuyer(Buyer buyer) {
        List<Order> orders = orderRepository.findAllByBuyerId(buyer.getId());

        List<OrderResponse.OrderDTO> orderDTOs = new ArrayList<>();

        for(Order order: orders) {
            List<OrderProduct> products = orderProductRepository.findAllByOrderId(order.getId());

            List<OrderResponse.OrderedProductDTO> productDTOs = products.stream().map((p)->new OrderResponse.OrderedProductDTO(p.getProduct(), p.getStatus())).toList();

            orderDTOs.add(new OrderResponse.OrderDTO(order.getId(), productDTOs));
        }

        return new OrderResponse.OrderListDTO(orderDTOs);
    }

    public OrderResponse.OrderProductListDTO viewOrdersSeller(Seller seller) {
        List<Product> sellerProducts = productRepository.findAllBySellerId(seller.getId());

        List<OrderProduct> sellerOrderProducts = new ArrayList<>();

        for(Product product : sellerProducts) {
            List<OrderProduct> orderProducts = orderProductRepository.findAllByProductId(product.getId());
            sellerOrderProducts.addAll(orderProducts);
        }

        List<OrderResponse.OrderProductListDTO.OrderedProductWithBuyerDTO> products
                = new ArrayList<>();

        for(OrderProduct orderProduct : sellerOrderProducts) {
            Product product = orderProduct.getProduct();
            Buyer buyer = orderProduct.getOrder().getBuyer();

            products.add(new OrderResponse.OrderProductListDTO.OrderedProductWithBuyerDTO(product, orderProduct.getId(),orderProduct.getStatus(), buyer));
        }

        return new OrderResponse.OrderProductListDTO(products);
    }

    @Transactional
    public void changeOrderStatus(Long orderProductId, OrderRequest.updateOrderProductDTO requestDTO) {
        OrderProduct orderProduct = orderProductRepository.findById(orderProductId).orElseThrow(()->new CustomException(ExceptionCode.ORDER_PRODUCT_NOT_FOUND));

        orderProduct.updateStatus(requestDTO.status());
    }
}
