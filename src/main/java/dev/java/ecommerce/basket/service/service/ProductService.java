package dev.java.ecommerce.basket.service.service;

import dev.java.ecommerce.basket.service.Client.PlatzStoreClient;
import dev.java.ecommerce.basket.service.Client.response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatzStoreClient platzStoreClient;

    @Cacheable(value = "products")
    public List<PlatziProductResponse> getAllProducts(){
        log.info("Getting all products");
        return platzStoreClient.getAllProducts();
    }

    @Cacheable(value = "product", key = "#productId")
    public PlatziProductResponse getProductById(Long productId){
        log.info("Getting product with id: {}", productId);
        return platzStoreClient.getProductsById(productId);
    }
}
