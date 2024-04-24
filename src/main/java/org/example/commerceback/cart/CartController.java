package org.example.commerceback.cart;

import lombok.AllArgsConstructor;
import org.example.commerceback._core.utils.ApiUtils;
import org.example.commerceback.user.Buyer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    // 장바구니에 상품 추가
    @PostMapping("/carts/products/{productId}")
    public ResponseEntity<?> addProductToCart(@PathVariable Long productId, @AuthenticationPrincipal Buyer buyer) {
        cartService.addToCart(productId, buyer);

        return ResponseEntity.ok().body(ApiUtils.success("장바구니에 추가되었습니다.", null));
    }

    // 내 장바구니 확인
    @GetMapping("/carts")
    public ResponseEntity<?> getCart(@AuthenticationPrincipal Buyer buyer) {
        cartService.getCart(buyer);
        return ResponseEntity.ok().body(ApiUtils.success("장바구니가 조회되었습니다.", cartService.getCart(buyer)));
    }
}
