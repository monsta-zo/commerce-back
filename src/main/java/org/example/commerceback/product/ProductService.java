package org.example.commerceback.product;

import lombok.AllArgsConstructor;
import org.example.commerceback._core.errors.CustomException;
import org.example.commerceback._core.errors.ExceptionCode;
import org.example.commerceback.user.Seller;
import org.example.commerceback.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse.AddProductDTO addProduct(ProductRequest.AddProductDTO requestDTO, Seller seller) {
        Product product = Product.builder()
                .name(requestDTO.name())
                .info(requestDTO.info())
                .price(requestDTO.price())
                .seller(seller)
                .build();

        Long id = productRepository.save(product).getId();

        return new ProductResponse.AddProductDTO(id);
    }

    public ProductResponse.ProductSellerDTO getProduct(Long productId, User user) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException(ExceptionCode.PRODUCT_NOT_FOUND));



        return new ProductResponse.ProductSellerDTO(product, product.getSeller().getId().equals(user.getId()));
    }

    public Object getStoreProducts(Long storeId) {
        List<Product> products = productRepository.findAllBySellerId(storeId);

        List<ProductResponse.ProductListDTO.ProductDTO> productDTOs = products
                .stream().map(ProductResponse.ProductListDTO.ProductDTO::new).toList();
        return new ProductResponse.ProductListDTO(productDTOs);
    }

    public ProductResponse.ProductListDTO getProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductResponse.ProductListDTO.ProductDTO> productDTOs = products
                .stream().map(ProductResponse.ProductListDTO.ProductDTO::new).toList();
        return new ProductResponse.ProductListDTO(productDTOs);
    }

    @Transactional
    public void patchProduct(Long productId, ProductRequest.AddProductDTO requestDTO) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new CustomException(ExceptionCode.PRODUCT_NOT_FOUND));

        product.updateProduct(requestDTO);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
