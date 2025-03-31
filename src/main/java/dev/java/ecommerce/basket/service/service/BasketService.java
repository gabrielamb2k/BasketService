package dev.java.ecommerce.basket.service.service;

import dev.java.ecommerce.basket.service.Client.response.PlatziProductResponse;
import dev.java.ecommerce.basket.service.controller.request.BasketRequest;
import dev.java.ecommerce.basket.service.controller.request.PaymentRequest;
import dev.java.ecommerce.basket.service.entity.Basket;
import dev.java.ecommerce.basket.service.entity.Product;
import dev.java.ecommerce.basket.service.entity.Status;
import dev.java.ecommerce.basket.service.exception.BusinessException;
import dev.java.ecommerce.basket.service.exception.DataNotFoundException;
import dev.java.ecommerce.basket.service.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public Basket getBasketById(String id){
        return basketRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Basket not found"));
    }

    public Basket createBasket(BasketRequest basketRequest){

        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent(basket -> {
                    throw new BusinessException("There is already an open basket for this client");
                });

        List<Product> products = getProducts(basketRequest);

        Basket basket = Basket.builder()
                .client(basketRequest.clientId())
                .status(Status.OPEN)
                .products(products)
                .build();

        basket.calculateTotalPrice();
        return basketRepository.save((basket));
    }

    public Basket updateBasket(String id, BasketRequest basketRequest){
        Basket basket = getBasketById(id);

        List<Product> products = getProducts(basketRequest);

        basket.setProducts(products);

        basket.calculateTotalPrice();
        return basketRepository.save(basket);
    }
    public Basket payBasket(String id, PaymentRequest paymentRequest){
        Basket savedBasket = getBasketById(id);
        savedBasket.setPaymentMethod(paymentRequest.getPaymentMethod());
        savedBasket.setStatus(Status.SOLD);
        return basketRepository.save(savedBasket);
    }

    public void deleteBasket(String id){
        Basket basket = getBasketById(id);
        basketRepository.delete(basket);
    }


    private List<Product> getProducts(BasketRequest basketRequest) {

        List<Product> products = basketRequest.products().stream()
                .map(productRequest -> {
                    PlatziProductResponse platziProductResponse = productService.getProductById(productRequest.id());
                    return Product.builder()
                            .id(platziProductResponse.id())
                            .title(platziProductResponse.title())
                            .price(platziProductResponse.price())
                            .quantity(productRequest.quantity())
                            .build();
                })
                .collect(Collectors.toList());
        return products;
    }
}