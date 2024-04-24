package org.example.commerceback.product;

import lombok.AllArgsConstructor;
import org.example.commerceback._core.utils.ApiUtils;
import org.example.commerceback.user.Seller;
import org.example.commerceback.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 상품 등록
    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest.AddProductDTO requestDTO, Errors errors, @AuthenticationPrincipal Seller seller) {
        return ResponseEntity.ok().body(ApiUtils.success("상품이 등록되었습니다.", productService.addProduct(requestDTO, seller)));
    }

    // 상품 조회
    @GetMapping("/products/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Long productId, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(ApiUtils.success("상품이 조회되었습니다.", productService.getProduct(productId,user)));
    }

    // 내가 등록한 상품 조회
    @GetMapping("store/{storeId}/products")
    public ResponseEntity<?> getStoreProducts(@PathVariable Long storeId) {
        return ResponseEntity.ok().body(ApiUtils.success("상품 목록이 조회되었습니다.", productService.getStoreProducts(storeId)));
    }

    // 모든 상품 조회
    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok().body(ApiUtils.success("상품 목록이 조회되었습니다.", productService.getProducts()));
    }

    // 상품 수정
    @PatchMapping("/products/{productId}")
    public ResponseEntity<?> patchProduct(@PathVariable Long productId, @RequestBody ProductRequest.AddProductDTO requestDTO) {
        productService.patchProduct(productId, requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success("상품이 수정되었습니다.", null ));
    }

    // 상품 삭제
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().body(ApiUtils.success("상품이 삭제되었습니다.", null ));
    }
}
