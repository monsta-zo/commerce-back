package org.example.commerceback.order;


import lombok.AllArgsConstructor;
import org.example.commerceback._core.utils.ApiUtils;
import org.example.commerceback.user.Buyer;
import org.example.commerceback.user.Seller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    // 장바구니 상품 전체 주문
    @PostMapping("/orders/carts")
    public ResponseEntity<?> orderCart(@AuthenticationPrincipal Buyer buyer) {
        orderService.orderCart(buyer);

        return ResponseEntity.ok().body(ApiUtils.success("주문이 완료되었습니다.", null));
    }

    // 상품 바로 주문
    @PostMapping("/orders/{productId}")
    public ResponseEntity<?> orderProduct(@PathVariable Long productId, @AuthenticationPrincipal Buyer buyer) {
        orderService.orderProduct(productId, buyer);
        return ResponseEntity.ok().body(ApiUtils.success("주문이 완료되었습니다.", null));
    }

    // 주문 내역 확인하기 (구매자)
    @GetMapping("/orders/buyer")
    public ResponseEntity<?> viewOrdersBuyer(@AuthenticationPrincipal Buyer buyer) {
        return ResponseEntity.ok().body(ApiUtils.success("주문 목록이 조회되었습니다.", orderService.viewOrdersBuyer(buyer)));
    }

    // 주문 내역 확인하기 (판매자)
    @GetMapping("/orders/seller")
    public ResponseEntity<?> viewOrdersSeller(@AuthenticationPrincipal Seller seller) {
        return ResponseEntity.ok().body(ApiUtils.success("주문 목록이 조회되었습니다.", orderService.viewOrdersSeller(seller)));
    }

    // 주문 상태 변경하기
    @PatchMapping("/orders/products/{orderProductId}")
    public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderProductId, @RequestBody OrderRequest.updateOrderProductDTO requestDTO){
        orderService.changeOrderStatus(orderProductId, requestDTO);

        return ResponseEntity.ok().body(ApiUtils.success("주문 상태가 변경되었습니다.", null));
    }
}
